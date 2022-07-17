package com.drpicox.game.util;

public class FrontendBackendApiCall {

    private final Request request;
    private final Response response;

    public FrontendBackendApiCall(String method, String url, Object requestBody, Object responseBody) {
        this.request = new Request(method, url, requestBody);
        this.response = new Response(responseBody);
    }

    static class Request {
        private String method;
        private String url;
        private Object body;

        public Request(String method, String url, Object body) {
            this.method = method;
            this.url = url;
            this.body = body;
        }
    }

    static class Response {
        private Object body;

        public Response(Object body) {
            this.body = body;
        }
    }
}
