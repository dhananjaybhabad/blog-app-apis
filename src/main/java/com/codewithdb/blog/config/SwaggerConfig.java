package com.codewithdb.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDoc() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo())

				.select()
				.apis(RequestHandlerSelectors.basePackage("com.codewithdb.blog.controllers"))
				.paths(PathSelectors
						.any())
				.build();
	}

	@SuppressWarnings("deprecation")
	private ApiInfo getInfo() {

		return new ApiInfo("SHRUTEEKATECH PRIVATE LIMITED,HINJEWADI(PUNE)",
				"This project is concept by Mr.Dhananjay Bhabad(Senior JAVA Backend Devlopper)", "version 1.0", "Pune",
				"AMIDA IT Team", "Call & Whatsapp +918888913272 / email : bhabaddhananjay24@gmail.com",
				"NextPage Name");
	}
}
