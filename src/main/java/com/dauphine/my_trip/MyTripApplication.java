package com.dauphine.my_trip;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "MyTrip",
				description = "MyTrip - Microservices Project - Dauphine",
				contact = @Contact(name = "Daryl", email = "daryl.martin-dipp@dauphine.eu"),
				version = "1.0"
		)
)
public class MyTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTripApplication.class, args);
	}

}
