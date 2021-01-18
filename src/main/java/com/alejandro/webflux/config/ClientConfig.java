package com.alejandro.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {
	
	public static final String BASE_URL = "localhost:8090/api/v2/product";
	
	@Bean
	public WebClient webClient() {
		return WebClient.create(BASE_URL);
	}

}
