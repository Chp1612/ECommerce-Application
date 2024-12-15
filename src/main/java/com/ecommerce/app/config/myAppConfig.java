package com.ecommerce.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class myAppConfig  implements WebMvcConfigurer{

	@Value("${allowed.origins}")
	private String[] allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println(allowedOrigins[0]);
		registry.addMapping("/**").allowedOrigins(allowedOrigins);
		
	}

}
