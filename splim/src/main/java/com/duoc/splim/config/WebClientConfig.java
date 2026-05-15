package com.duoc.splim.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${steam.api.base-url}")
    private String steamApiBaseUrl;

    @Value("${steam.api.key}")
    private String steamApiKey;

    @Bean
    public WebClient steamWebClient() {
        return WebClient.builder()
                .baseurl(steamApiBaseUrl)
                .
    }

}
