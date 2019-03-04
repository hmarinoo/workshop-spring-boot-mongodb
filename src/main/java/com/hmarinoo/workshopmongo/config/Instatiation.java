package com.hmarinoo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.hmarinoo.workshopmongo.domain.Post;
import com.hmarinoo.workshopmongo.domain.User;
import com.hmarinoo.workshopmongo.dto.AuthorDTO;
import com.hmarinoo.workshopmongo.dto.CommentDTO;
import com.hmarinoo.workshopmongo.repository.PostRepository;
import com.hmarinoo.workshopmongo.repository.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post morning = new Post(null, sdf.parse("12/01/2019"), "Hello", "Hello people good morning",
				new AuthorDTO(maria));
		Post afternoon = new Post(null, sdf.parse("10/02/2019"), "Good afternoon", "Hello friends good afternoon",
				new AuthorDTO(alex));
		Post night = new Post(null, sdf.parse("10/10/2018"), "Nice nigth", "Good night mi friends",
				new AuthorDTO(maria));
		postRepository.saveAll(Arrays.asList(morning, afternoon, night));

		maria.getPosts().addAll(Arrays.asList(morning, night));
		alex.getPosts().add(afternoon);
		userRepository.saveAll(Arrays.asList(maria, alex));

		CommentDTO comment1 = new CommentDTO("Hello have a nice day", sdf.parse("12/01/2019"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Good night mi friend", sdf.parse("10/10/2018"), new AuthorDTO(bob));
		CommentDTO comment3 = new CommentDTO("Have a nice afternoon in the pool", sdf.parse("10/02/2019"),
				new AuthorDTO(maria));
		morning.getComments().add(comment1);
		afternoon.getComments().add(comment3);
		night.getComments().add(comment2);
		postRepository.saveAll(Arrays.asList(morning, afternoon, night));
	}

}
