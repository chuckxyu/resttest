package com.example.articulos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ArticulosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticulosApplication.class, args);
	}
}
