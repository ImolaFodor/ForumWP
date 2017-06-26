package com.example.wp17;

import com.example.wp17.model.Comment;
import com.example.wp17.model.SubForum;
import com.example.wp17.model.Topic;
import com.example.wp17.model.User;
import com.example.wp17.service.SubForumService;
import com.example.wp17.service.TopicService;
import com.example.wp17.service.UserService;
import com.example.wp17.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class Wp17Application {

	public static void main(String[] args) {
		SpringApplication.run(Wp17Application.class, args);
		System.out.println("APP STARTED");
	}

	@Bean
	CommandLineRunner init(final CommentService commentService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {

				/*System.out.println("READING USERS FILE");
				ArrayList<SubForum> subForums = subForumService.readSubForums();
				SubForum sf = new SubForum();
				sf.setName("IMOLA RET");
				subForums.add(sf);
				System.out.println(subForums);
				subForumService.writeSubForums(subForums);
				subForums = subForumService.readSubForums();
				System.out.println("SFS FROM FILE");
				System.out.println(subForums);*/

				/*System.out.println("READING USERS FILE");
				ArrayList<User> users = userService.readUsers();
				User u = new User();
				u.setUsername("IMOLA");
				users.add(u);
				System.out.println(users);
				userService.writeUsers(users);
				users = userService.readUsers();
				System.out.println("SFS FROM FILE");
				System.out.println(users);*/

				/*System.out.println("READING COMMENTS FILE");
				ArrayList<Comment> comments = commentService.readComments("IMOLA topic");
				Comment c = new Comment();
				c.setTopic("IMOLA topic");
				c.setContent("This is Imolas second comment");
				Date date=new Date();
				Format formatter = new SimpleDateFormat("yyyy-MM-dd");
				String s = formatter.format(date);
				c.setDate(s);
				User u= new User();
				u.setUsername("Imike");
				c.setAuthor(u);
				comments.add(c);
				System.out.println(comments);
				commentService.writeComments(comments);
				comments = commentService.readComments("IMOLA TOPIC");
				System.out.println("SFS FROM FILE");
				System.out.println(comments);*/

				/*System.out.println("READING TOPICS FILE");
				ArrayList<Topic> topics = topicService.readTopics("IMOLA RET");
				Topic t = new Topic();
				t.setName("IMOLA topic");
				t.setContent("Imola [ˈiːmola] (Emilian: Iommla, Romagnol: Jômla/Jemula) is a town and comune in the Metropolitan City of Bologna, located on the river Santerno, in the Emilia-Romagna region of northern Italy. The town is traditionally considered the western entrance to the historical region Romagna.\n" +
						"\n" +
						"The city is most noted as the home of the Autodromo Enzo e Dino Ferrari which formerly hosted the Formula One San Marino Grand Prix (the race was named after the nearby independent republic of San Marino, as Monza already hosted the Italian Grand Prix), and the deaths of Formula One drivers Ayrton Senna (Brazilian) and Roland Ratzenberger (Austrian) at the circuit during the 1994 San Marino Grand Prix. The death of Senna (three-times world champion) was an event that shocked the sporting world and changed Formula One safety standards for good.");
				t.setSubForum("IMOLA RET");
				topics.add(t);
				//System.out.println(subForums);
				topicService.writeTopics(topics);
				topics = topicService.readTopics("IMOLA RET");
				//System.out.println("SFS FROM FILE");
				System.out.println(topics);*/
			}
		};
	}


}
