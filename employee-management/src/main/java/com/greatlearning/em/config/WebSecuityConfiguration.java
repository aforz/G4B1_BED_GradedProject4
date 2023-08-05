package com.greatlearning.em.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.em.security.DomainUserDetailsService;

@Configuration
public class WebSecuityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DomainUserDetailsService domainUserDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(domainUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();

		http.authorizeRequests()
				.antMatchers("/h2-console/**", "/h2-console**").permitAll()
//				.antMatchers(HttpMethod.GET, "/api/employees**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/employees**").hasRole("ADMIN")
//				.antMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
				.anyRequest().fullyAuthenticated()
				.and().formLogin().and().httpBasic();
	}
}
