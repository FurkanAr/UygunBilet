package com.uygunbilet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UygunbiletGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(UygunbiletGatewayApplication.class, args);
	}

}
