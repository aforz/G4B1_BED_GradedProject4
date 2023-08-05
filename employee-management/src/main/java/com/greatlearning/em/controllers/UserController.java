package com.greatlearning.em.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.em.entities.User;
import com.greatlearning.em.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public void addUser(@RequestBody User user) throws Exception {
		userService.addUser(user);
	}

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}
}
