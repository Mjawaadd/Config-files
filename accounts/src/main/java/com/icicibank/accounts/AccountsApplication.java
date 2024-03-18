package com.icicibank.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		
		info = @Info(
				title = "Accounts microservice REST API documentation",
				description = "icici Accounts microservice REST API documentation",
				version = "version1",
				contact = @Contact(
						name = "Mohammed Jawad",
						email = "mdjawad678@gmail.com",
						url = "https://www.jawaad.com"						
						),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.google.com"
						)			
				),
		
		externalDocs = @ExternalDocumentation(
				description = "icici Accounts microservice REST API documentation",
				url = "https://www.google.com"
				)		
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
