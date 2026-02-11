package com.jayanth.servicce_reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServicceRegApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicceRegApplication.class, args);
	}

}
