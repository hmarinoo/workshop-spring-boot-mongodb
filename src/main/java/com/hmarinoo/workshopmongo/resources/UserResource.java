package com.hmarinoo.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hmarinoo.workshopmongo.domain.User;
import com.hmarinoo.workshopmongo.dto.UserDTO;
import com.hmarinoo.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> listUser = userService.findAll();
		List<UserDTO> listUserDTO = listUser.stream().map(x-> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listUserDTO);
	}

}
