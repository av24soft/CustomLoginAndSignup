package com.avsoft.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.avsoft.demo.entity.User;
import com.avsoft.demo.repo.UserRepository;
@Service
public class UserDetaillServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository repository;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = repository.findByUsername(username);

		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword()).roles(user.getRole().split(",")).build();
	}
	
	
	public void registerUser(User user) {
		if (user == null) {
			throw new RuntimeException("invalid user name password");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		repository.save(user);
	}

}
