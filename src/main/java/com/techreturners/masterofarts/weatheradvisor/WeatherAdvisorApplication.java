package com.techreturners.masterofarts.weatheradvisor;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

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

	/**
	 * Add hard-coded info as the application.properties version does not seem to be picked up
	 * ## Configuring info endpoint
	 * info.application.name=Weather Advice Application
	 * info.application.description=B-Team (a.k.a. Masters Of Arts) team exercise
	 * info.application.version=1.0.1
	 * info.application.author.name=Marcus Callum, Matt DaSilva, Mark Ingamells
	 *
	 * @return restful response of information
	 */
	@Bean
	InfoContributor getInfoContributor() {
		Map<String, Object> marcus = new HashMap<>();
		marcus.put("name", "Marcus Callum");
		marcus.put("bio", "A man of diverse and creative talents");
		marcus.put("website", "https://www.marcuscallum.com/");
		Map<String, Object> matt = new HashMap<>();
		matt.put("name", "Matt DaSilva");
		matt.put("bio", "Will very shortly be tying the knot");
		Map<String, Object> mark = new HashMap<>();
		mark.put("name", "Mark Ingamells");
		mark.put("bio", "A 'man for all seasons'");
		mark.put("website", "https://www.tinkabell.co.uk/home");
		Map<String, Object>[] authors = new Map[]{marcus, matt, mark};
		Map<String, Object> details = new HashMap<>();
		details.put("name", "Weather Advice Application");
		details.put("description", "B-Team (a.k.a. Masters Of Arts) team exercise");
		details.put("version", "1.0.1");
		details.put("author", authors);
		Map<String, Object> wrapper = new HashMap<>();
		wrapper.put("app", details);
		return new MapInfoContributor(wrapper);
	}

	public static void main(String[] args) {

		SpringApplication.run(WeatherAdvisorApplication.class, args);

	}

}
