package com.ecommerce.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.okta.spring.boot.oauth.Okta;

@Configuration
@ComponentScan("com.ecommerce.app")
@PropertySource("classpath:application.properties")
public class AppConfig{

	@Value("${spring.datasource.url}")
	private String URL;

	@Value("${spring.datasource.username}")
	private String USER;

	@Value("${spring.datasource.driver-class-name}")
	private String DRIVER;

	@Value("${spring.datasource.password}")
	private String PASSWORD;
	
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(URL);
		driverManagerDataSource.setUsername(USER);
		driverManagerDataSource.setPassword(PASSWORD);
		driverManagerDataSource.setDriverClassName(DRIVER);
		return driverManagerDataSource;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(configurer -> 
			configurer
			.antMatchers("order-history/**")
			.authenticated()
		)
		.oauth2ResourceServer()
		.jwt();
		
		http.cors();
		
		http.setSharedObject(ContentNegotiationStrategy.class, new HeaderContentNegotiationStrategy());
		
		Okta.configureResourceServer401ResponseBody(http);
		
		http.csrf().disable();
		
		return http.build();
	}
	
	
}
