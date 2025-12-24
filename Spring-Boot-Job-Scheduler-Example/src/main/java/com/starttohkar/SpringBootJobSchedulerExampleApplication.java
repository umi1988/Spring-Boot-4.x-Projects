package com.starttohkar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootJobSchedulerExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJobSchedulerExampleApplication.class, args);
	}

}
