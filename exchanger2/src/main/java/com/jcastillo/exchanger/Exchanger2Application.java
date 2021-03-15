package com.jcastillo.exchanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.jcastillo.exchanger.model"} )
@EnableJpaRepositories(basePackages = {"com.jcastillo.exchanger.repository"})
public class Exchanger2Application {

	public static void main(String[] args) {
		SpringApplication.run(Exchanger2Application.class, args);
	}

}
