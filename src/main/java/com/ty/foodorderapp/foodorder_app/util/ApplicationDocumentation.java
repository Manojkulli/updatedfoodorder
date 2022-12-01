package com.ty.foodorderapp.foodorder_app.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationDocumentation {
	
	@Bean
	public Docket getDocket() {
		Contact contact=new Contact("Testyantra", "Testyantra.com", "ty@mail.com");
		List<VendorExtension> extensions=new ArrayList<VendorExtension>();
		ApiInfo apiInfo=new ApiInfo("Food App", "Boot app for food", "1.0", "testyantra.com", contact, "apache tomcat", "www.apache.com", extensions);
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.ty.foodorderapp.foodorder_app")).build();
	}

}
