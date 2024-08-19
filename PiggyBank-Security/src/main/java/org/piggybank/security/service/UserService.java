package org.piggybank.security.service;

import org.piggybank.security.entiry.User;
import org.piggybank.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	public String saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(user);
		return "User created successfully";
	}

	public String generateToken(String email) {
		return jwtService.generateToken(email);
	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
}
