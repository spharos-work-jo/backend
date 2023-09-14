package com.workjo.pointapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableFeignClients
@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
public class PointappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointappApplication.class, args);
	}

}
