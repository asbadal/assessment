package com.filmland.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FilmlandApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmlandApplication.class, args);
	}

	@Bean
	@Primary
	public BCryptPasswordEncoder getpce() {
		return new BCryptPasswordEncoder();
	}

	public static String hash(String password, int row) {
		return BCrypt.hashpw(password, BCrypt.gensalt(row));
	}

}
