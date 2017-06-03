package com.example.wp17;

import com.example.wp17.model.User;
import com.example.wp17.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class Wp17Application {

	public static void main(String[] args) {
		SpringApplication.run(Wp17Application.class, args);
		System.out.println("APP STARTED");
	}

	@Bean
	CommandLineRunner init(final UserService userService){
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

				/*System.out.println("READING USERS FILE");
				ArrayList<User> users = userService.readUsers();
				User u = new User(); u.setName("IMOLA RET");
				users.add(u);
				System.out.println(users);
				userService.writeUsers(users);
				users = userService.readUsers();
				System.out.println("USERS FROM FILE");
				System.out.println(users);*/


			}
		};
	}


}
