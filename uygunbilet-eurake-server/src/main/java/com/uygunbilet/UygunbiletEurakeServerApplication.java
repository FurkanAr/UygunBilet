package com.uygunbilet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UygunbiletEurakeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UygunbiletEurakeServerApplication.class, args);

	}

}
