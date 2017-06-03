package com.example.wp17;

import com.example.wp17.model.SubForum;
import com.example.wp17.model.Topic;
import com.example.wp17.model.User;
import com.example.wp17.service.SubForumService;
import com.example.wp17.service.TopicService;
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
	CommandLineRunner init(final TopicService topicService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

				/*System.out.println("READING USERS FILE");
				ArrayList<SubForum> subForums = subForumService.readSubForums();
				SubForum sf = new SubForum();
				sf.setName("IMOLA RET");
				sf.setId(1);
				subForums.add(sf);
				System.out.println(subForums);
				subForumService.writeSubForums(subForums);
				subForums = subForumService.readSubForums();
				System.out.println("SFS FROM FILE");
				System.out.println(subForums);*/

				System.out.println("READING TOPICS FILE");
				ArrayList<Topic> topics = topicService.readTopics(1);
				Topic t = new Topic();
				t.setName("IMOLA RET");
				t.setSubForum(1);
				topics.add(t);
				//System.out.println(subForums);
				topicService.writeTopics(topics);
				topics = topicService.readTopics(1);
				//System.out.println("SFS FROM FILE");
				System.out.println(topics);

			}
		};
	}


}
