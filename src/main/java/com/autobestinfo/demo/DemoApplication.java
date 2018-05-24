package com.autobestinfo.demo;

import com.autobestinfo.demo.user.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsersRepository users ) {

		return args -> {
//			User user = new User();
//			user.setFirstName("Jon");
//			users.insert(user);
		};

	}


}

