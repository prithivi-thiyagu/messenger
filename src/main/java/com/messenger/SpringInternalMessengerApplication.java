package com.messenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringInternalMessengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringInternalMessengerApplication.class, args);
	}

}
