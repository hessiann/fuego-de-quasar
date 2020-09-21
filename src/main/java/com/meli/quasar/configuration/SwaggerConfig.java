package com.meli.quasar.configuration;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.base.Predicate;

import lombok.RequiredArgsConstructor;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig {

	final List<ResponseMessage> globalResponses = Arrays
			.asList(new ResponseMessageBuilder().code(200).message("OK").build(), new ResponseMessageBuilder().code(500)
					.message("Internal Error").responseModel(new ModelRef("String")).build());

	@Bean
	public Docket mainApi() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, globalResponses)
				.globalResponseMessage(RequestMethod.POST, globalResponses)
				.globalResponseMessage(RequestMethod.PUT, globalResponses)
				.globalResponseMessage(RequestMethod.DELETE, globalResponses).groupName("main").select()
				.paths(endpoints()).build();
	}

	private Predicate<String> endpoints() {
		return or(regex(".*quasar-api.*"), regex(".*actuator.*"));
	}
}
