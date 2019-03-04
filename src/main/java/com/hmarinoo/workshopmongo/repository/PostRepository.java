package com.hmarinoo.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hmarinoo.workshopmongo.domain.Post;
import java.lang.String;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	@Query("{ 'title':{ $regex: ?0,$options:'i' }}")
	List<Post> findByTitle(String text);
	
}
