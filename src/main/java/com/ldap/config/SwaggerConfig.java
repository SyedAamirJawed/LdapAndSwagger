package com.ldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI()
				.components(new Components()
						       .addSecuritySchemes("VaultSecurityScheme", new SecurityScheme()
						                                                           .name("VaultSecurityScheme")
						                                                           .type(SecurityScheme.Type.HTTP)
						                                                           .scheme("bearer").bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement().addList("VaultSecurityScheme"))
				.info(swaggerInfo());
	}
	
	
     private Info swaggerInfo() {
    	 
    	 return new Info()
    			 .title("Vault Guardian All Api")
    			 .description("This API provides endpoints for managing Vault Guardian application")
    			 .version("1.0")
    			 .contact(new Contact()
    					  .name("Aamir Jawed")
    					  .email("syedaamirjawed@gmail.com")
    					  .url("http://52.66.213.239/login"))
    			 .license(new License().name("Vault Guardian"));       
     }

}
