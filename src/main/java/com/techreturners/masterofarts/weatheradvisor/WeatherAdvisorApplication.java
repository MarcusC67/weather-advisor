package com.techreturners.masterofarts.weatheradvisor;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherAdvisorApplication {

	// You are using the builder pattern here to construct the GroupedOpenApi instance.
	// It is a way of choosing how to build (construct) the GroupedOpenApi object by specifying some construction steps.
	// The Special Builder object helps with the process of constructing.

	@Bean
	public GroupedOpenApi swaggerConfiguration() {
		return GroupedOpenApi.builder()
				.group("weather-advisor-api")
				.pathsToMatch("/api/v1/**")
				.build();
	}

	@Bean
	public OpenAPI bookManagerInfoApi() {
		return new OpenAPI()
				.info(
						new Info()
								.title("Simple Weather Advice API")
								.description("Want to be prepared, before you leave your home? This is the API for you!")
				.version("v1")
				.license(new License()
						.name("MIT License")
						.url("http://springdoc.org")));
	}

	public static void main(String[] args) {

		SpringApplication.run(WeatherAdvisorApplication.class, args);

	}

}
