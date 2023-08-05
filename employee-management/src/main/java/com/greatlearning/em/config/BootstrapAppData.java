package com.greatlearning.em.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.greatlearning.em.entities.Employee;
import com.greatlearning.em.entities.Role;
import com.greatlearning.em.entities.User;
import com.greatlearning.em.repositories.EmployeeRepository;
import com.greatlearning.em.repositories.UserRepository;

@Configuration
public class BootstrapAppData {

	@Bean
	public RestTemplate resTemplate() {
		return new RestTemplate();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private Faker faker = new Faker();

	@EventListener(ApplicationReadyEvent.class)
	public void addEmployees(ApplicationReadyEvent event) {
		for (int i = 0; i < 5; i++) {
			Employee employee = new Employee();
			employee.setFirstName(faker.name().firstName());
			employee.setLastName(faker.name().lastName());
			employee.setEmail(faker.internet().emailAddress());
			employeeRepository.saveAndFlush(employee);
		}
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void addUserAndRoles(ApplicationReadyEvent event) {

		User afroz = new User();
		User alam = new User();

		afroz.setName("afroz");
		afroz.setPassword(this.passwordEncoder.encode("admin"));

		alam.setName("alam");
		alam.setPassword(this.passwordEncoder.encode("user"));

		Role userRole = new Role();
		Role adminRole = new Role();

		userRole.setName("ROLE_USER");
		adminRole.setName("ROLE_ADMIN");

		afroz.addRole(userRole);
		afroz.addRole(adminRole);

		alam.addRole(userRole);

		this.userRepository.save(afroz);
		this.userRepository.save(alam);
	}
}
