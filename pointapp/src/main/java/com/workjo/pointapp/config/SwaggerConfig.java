package com.workjo.pointapp.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi publicApi() {

		String[] paths = { "/api/v1/**" };

		return GroupedOpenApi.builder()
			.group("public-api")
			.pathsToMatch(paths)
			.build();
	}


	@Bean
	public OpenAPI openAPI() {

		Info info = new Info()
			.version("v1.0.0")
			.title("SSG POINT APP API")
			.description("ssg point app - 일시켜조");

		return new OpenAPI()
			.info(info);
	}

}