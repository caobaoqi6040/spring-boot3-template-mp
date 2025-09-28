package dev.caobaoqi6040.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// https://docs.spring.io/spring-boot/reference/using/devtools.html#using.devtools.restart.disable
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(BackendApplication.class, args);
	}

}
