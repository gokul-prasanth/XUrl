package com.xurl.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api2() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.xurl"))
				.build()
				.apiInfo(metaInfo())
				.globalRequestParameters(globalImplicitParam());

		return docket;
	}

	private List<RequestParameter> globalImplicitParam() {
		var userParam = new RequestParameterBuilder().name("USER")
				.description("User's Name")
				.query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)).defaultValue("SYSTEM"))
				.in(ParameterType.HEADER).required(true).build();
		return List.of(userParam);
	}

	private ApiInfo metaInfo() {
		return new ApiInfoBuilder()
				.title("Xurl Apis")
				.description("This is a API documentation page for XUrl Application APIs")
				.version("1.0.0")
				.build();
	}

}
