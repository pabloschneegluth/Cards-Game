package com.drpicox.game.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class RandomPickerServiceConfiguration {

    private RandomPickerServiceMock randomPickerServiceMock;

    public RandomPickerServiceConfiguration(RandomPickerServiceMock randomPickerServiceMock) {
        this.randomPickerServiceMock = randomPickerServiceMock;
    }

    @Bean
    @Primary
    public RandomPickerService randomPickerService() {
        return randomPickerServiceMock;
    }
}
