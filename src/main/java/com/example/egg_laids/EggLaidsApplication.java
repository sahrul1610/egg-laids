package com.example.egg_laids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages = { "com.example.egg_laids"})
public class EggLaidsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EggLaidsApplication.class, args);
	}

}
