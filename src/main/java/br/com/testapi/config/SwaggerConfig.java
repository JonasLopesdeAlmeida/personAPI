package br.com.testapi.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		//selecting controllers and paths 
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.testapi"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(ApiInfo());

	}

	private ApiInfo ApiInfo() {
		
		return new ApiInfo("RESTful API WITH SPRING BOOT",
				"API TEST", 
				"V1", 
				"IN PROGRESS",
				new Contact("Jonas Lopes de Almeida", "https://github.com/JonasLopesdeAlmeida", "euprogramadorads@gmail.com"), 
				"IN PROGRESS", 
				"IN PROGRESS", 
				Collections.emptyList());
	}

}
