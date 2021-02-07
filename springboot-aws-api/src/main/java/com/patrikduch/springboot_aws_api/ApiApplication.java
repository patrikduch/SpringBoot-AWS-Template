package com.patrikduch.springboot_aws_api;

import com.patrikduch.springboot_aws_api.configs.ServerProductionConfig;
import com.patrikduch.springboot_aws_api_localhost.config.ServerConfigDevelopment;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableMongoRepositories("com.patrikduch")
@OpenAPIDefinition
@Import({ServerProductionConfig.class, ServerConfigDevelopment.class})
@ComponentScan(basePackages = {"com.patrikduch.springboot_aws_api", "com.patrikduch", "com.patrikduch.springboot_aws_api_localhost"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
