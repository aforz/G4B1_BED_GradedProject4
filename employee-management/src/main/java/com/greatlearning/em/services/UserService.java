package com.greatlearning.em.services;

import java.util.List;

import com.greatlearning.em.entities.User;

public interface UserService {
	
	public void addUser(User user) throws Exception;
	List<User> findAll();

}
