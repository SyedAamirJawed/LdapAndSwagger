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
						       .addSecuritySchemes("MySecurityScheme", new SecurityScheme()
						                                                           .name("MySecurityScheme")
						                                                           .type(SecurityScheme.Type.HTTP)
						                                                           .scheme("bearer").bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement().addList("MYSecurityScheme"))
				.info(swaggerInfo());
	}
	
	
     private Info swaggerInfo() {
    	 
    	 return new Info()
    			 .title("My All Api")
    			 .description("This API provides endpoints for managing application")
    			 .version("1.0")
    			 .contact(new Contact()
    					  .name("Aamir Jawed")
    					  .email("syedaamirjawed@gmail.com")
    					  .url("https://github.com/SyedAamirJawed"))
    			 .license(new License().name("My Application"));       
     }

}
