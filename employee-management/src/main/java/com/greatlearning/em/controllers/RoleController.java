package com.greatlearning.em.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.em.entities.Role;
import com.greatlearning.em.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public List<Role> findAll() {
		return roleService.findAll();
	}

	@PostMapping
	public void addRole(@RequestBody Role role) throws Exception {
		roleService.addRole(role);
	}
}
