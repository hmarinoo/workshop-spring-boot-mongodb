package com.hmarinoo.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hmarinoo.workshopmongo.domain.Post;
import com.hmarinoo.workshopmongo.services.PostService;


@RestController
@RequestMapping(value="posts")
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
	Post post =	postService.findById(id);
		return ResponseEntity.ok().body(post);
	}
}