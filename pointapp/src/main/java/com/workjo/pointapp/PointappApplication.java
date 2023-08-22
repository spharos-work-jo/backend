package com.workjo.pointapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class PointappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointappApplication.class, args);
	}

}
