package com.revature.reservationtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import utils.WebClientCustomizer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.revature.repos"})
@ComponentScan(basePackages = {"com.revature"})
@EntityScan(basePackages = {"com.revature.entities"})

public class ReservationtrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationtrackerApplication.class, args);
	}
	WebClientCustomizer rwc = new WebClientCustomizer();
}
