package com.cap.fatrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestHibernateApplication.class, args);
	}

}
