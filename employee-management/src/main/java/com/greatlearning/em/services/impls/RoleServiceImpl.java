package com.greatlearning.em.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.em.entities.Role;
import com.greatlearning.em.repositories.RoleRepository;
import com.greatlearning.em.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void addRole(Role role) throws Exception {
		Role roleDb = roleRepository.findByName(role.getName());
		if (roleDb == null)
			roleRepository.saveAndFlush(role);
		else
			throw new Exception("Role [ " + role.getName() + " ] is already available");
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

}
