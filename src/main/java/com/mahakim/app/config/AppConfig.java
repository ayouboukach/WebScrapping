package com.mahakim.app.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class AppConfig {

	@Value("${based.path}")
	private String basedUrl;
	
    @Bean
	  public WebClient webClient() {
    	HttpClient client = HttpClient.create().responseTimeout(Duration.ofSeconds(10)); 	
	    WebClient webClient = WebClient.builder()
	      .baseUrl(basedUrl)
	      .defaultCookie("cookie-name", "cookie-value")
	      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	      .clientConnector(new ReactorClientHttpConnector(client))
	      .build();
		return webClient;
	  }
}