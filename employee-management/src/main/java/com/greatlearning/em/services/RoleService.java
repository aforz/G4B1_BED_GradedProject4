package com.greatlearning.em.services;

import java.util.List;

import com.greatlearning.em.entities.Role;

public interface RoleService {

	public void addRole(Role role) throws Exception;

	List<Role> findAll();
}
