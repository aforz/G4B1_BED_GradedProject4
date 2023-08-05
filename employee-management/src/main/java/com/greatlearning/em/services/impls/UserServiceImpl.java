package com.greatlearning.em.services.impls;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.em.entities.Role;
import com.greatlearning.em.entities.User;
import com.greatlearning.em.repositories.RoleRepository;
import com.greatlearning.em.repositories.UserRepository;
import com.greatlearning.em.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void addUser(User user) throws Exception {
		String username = user.getName();
		Optional<User> existingUserOptional = userRepository.findByName(username);
		if (existingUserOptional.isPresent()) {
			throw new Exception("User already exists with username: " + username);
		}
		if (!user.getRoles().isEmpty()) {
			User newUser = new User();
			newUser.setName(username);
			newUser.setPassword(passwordEncoder.encode(user.getPassword()));

			for (Role role : user.getRoles()) {
				Role fetchedRole = roleRepository.findById(role.getId()).orElse(null);
				if (fetchedRole != null) {
					newUser.addRole(fetchedRole);
				} else {
					throw new Exception("Invalid role Id: " + role.getId());
				}
			}
			userRepository.save(newUser);
		} else {
			throw new Exception("Role is mandatory");
		}
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
