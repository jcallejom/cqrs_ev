package com.prueba.reservas.cmd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
	     return new OpenAPI()
	          .components(new Components())
	          .info(new Info()
	          .title("Command API")
	          .version("1.0.0"));
	}
	
//	@Bean
//	public OpenAPI apiInfo() {
//	
//	        return new OpenAPI()
//	                .info(new Info().title("Administraccion")
//	                .description("modelo de maec-da-api-admin")
//	                .version("v1.0.0")).components(new Components()
//	          	          .addSecuritySchemes("bearer-key", 
//	          	  	          new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))) 
//	                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
//	}
	
	
}
