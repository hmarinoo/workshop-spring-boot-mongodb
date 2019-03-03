package com.hmarinoo.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hmarinoo.workshopmongo.domain.User;
import com.hmarinoo.workshopmongo.dto.UserDTO;
import com.hmarinoo.workshopmongo.repository.UserRepository;
import com.hmarinoo.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	public List<User> findAll() {

		return repo.findAll();

	}

	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found!"));

	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public void deleteById(String id) {
		repo.findById(id).get();
		repo.deleteById(id);
		
	}
	public User fromDTO(UserDTO userDTO) {
		User user = new User(userDTO.getId(),userDTO.getName(),userDTO.getEmail());
		return user;
		
	}
	
	public User updateUser(User user) {
		User newUser = findById(user.getId());
		updateData(user,newUser);
		return repo.save(user);
	}

	private void updateData(User user, User newUser) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
	}
	
}
