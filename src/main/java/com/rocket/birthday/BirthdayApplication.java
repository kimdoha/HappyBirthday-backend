package com.rocket.birthday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@ConfigurationPropertiesScan
public class BirthdayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BirthdayApplication.class, args);
	}
}
