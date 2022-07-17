package com.drpicox.game.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Service
public class FrontendSimulator {

    private final MockMvc mockMvc;

    private final Gson gson;

    private List<FrontendBackendApiCall> apiCalls = new ArrayList<>();

    public FrontendSimulator(MockMvc mockMvc, Gson gson) {
        this.mockMvc = mockMvc;
        this.gson = gson;
    }

    public <T> T get(String url, Class<T> type) {
        var request = MockMvcRequestBuilders.get(url);
        var result = performApiCall(url, request, type);
        apiCalls.add(new FrontendBackendApiCall("GET", url, null, result));

        return result;
    }

    public <T> T post(String url, Object requestBody, Class<T> type) {
        var requestBodyJson = gson.toJson(requestBody);
        var request = MockMvcRequestBuilders
            .post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBodyJson)
            .characterEncoding("utf-8");

        var result = performApiCall(url, request, type);
        apiCalls.add(
            new FrontendBackendApiCall("POST", url, requestBody, result)
        );

        return result;
    }

    private <T> T performApiCall(
        String url,
        MockHttpServletRequestBuilder requestBuilder,
        Class<T> type
    ) {
        String resultJson = null;
        MvcResult result = null;
        try {
            result = mockMvc.perform(requestBuilder).andReturn();
            resultJson = result.getResponse().getContentAsString();

            var exception = result.getResolvedException();
            if (exception != null) throw exception;

            if (
                result.getResponse().getStatus() != 200
            ) throw new RuntimeException(
                "The api call did not returned status 200."
            );
        } catch (Exception e) {
            if (result == null) throw new RuntimeException(
                "Error while executing the API REST Call: POST " + url,
                e
            );

            var message = new StringBuilder();
            var request = result.getRequest();
            var response = result.getResponse();

            message
                .append("The api call ")
                .append(request.getMethod())
                .append(" ")
                .append(url)
                .append(" has failed.");

            var status = response.getStatus();
            if (status == 404) message
                .append("\nIt looks like that there is no @RestController ")
                .append("for the api call ")
                .append(request.getMethod())
                .append(" ")
                .append(url)
                .append(
                    ". Make sure that you have an api controller with @RestController "
                )
                .append(
                    "with the corresponding annotations of @RequestMapping, @GetMapping, @PostMapping "
                )
                .append("or verify the URL is correct.");

            if (status == 500) message
                .append("\nIt looks like the @RestController ")
                .append("" + result.getHandler())
                .append("has failed to fulfill the request. ")
                .append("Please, check the stacktrace.");

            try {
                message
                    .append("\n- method   : ")
                    .append(request.getMethod())
                    .append("\n- url      : ")
                    .append(url)
                    .append("\n- body     : ")
                    .append(request.getContentAsString())
                    .append("\n- status   : ")
                    .append(response.getStatus())
                    .append("\n- handler  : ")
                    .append(result.getHandler())
                    .append("\n- exception: ")
                    .append(result.getResolvedException());
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            }

            throw new RuntimeException(message.toString(), e);
        }

        var resultObject = gson.fromJson(resultJson, type);
        return resultObject;
    }

    public void clear(String postId) {
        apiCalls.clear();
        try {
            var file = new File(getApiCallsUri(postId));
            file.delete();
        } catch (Exception reason) {
            System.err.println(reason);
        }
    }

    public void save(String postId) {
        var output = gson.toJson(apiCalls);
        try {
            var uri = getApiCallsUri(postId);
            try (var fw = new FileWriter(new File(uri))) {
                fw.append(output);
                fw.flush();
            }
        } catch (Exception reason) {
            throw new RuntimeException(
                "Cannot write post frontend backend apiCalls for " + postId,
                reason
            );
        }
    }

    private URI getApiCallsUri(String postId)
        throws MalformedURLException, URISyntaxException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        var directoryUrl = loader.getResource("apiCalls");
        var apiCallsUrl = new URL(directoryUrl, "apiCalls/" + postId + ".json");
        return apiCallsUrl.toURI();
    }
}
