package com.greatlearning.em.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.em.entities.User;
import com.greatlearning.em.repositories.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByName(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			return new DomainUserDetails(user);
		}
		throw new UsernameNotFoundException("Invalid username passed");
	}
}
