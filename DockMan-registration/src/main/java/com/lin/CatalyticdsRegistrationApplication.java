package com.lin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CatalyticdsRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalyticdsRegistrationApplication.class, args);
	}
}
