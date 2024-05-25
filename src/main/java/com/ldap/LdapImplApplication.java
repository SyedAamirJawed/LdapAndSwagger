package com.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LdapImplApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdapImplApplication.class, args);
		System.out.println("Program Start");
	}

}
