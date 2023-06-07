package com.routes.routesystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class RouteSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteSystemApplication.class, args);
	}

}
