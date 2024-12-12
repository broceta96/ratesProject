package com.rates.ratesproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// FIXME: can you use flyway or liquibase for versioning DB and creating changelogs
@SpringBootApplication
public class RatesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatesProjectApplication.class, args);
	}

}
