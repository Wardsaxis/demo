package com.revature.food4delivery.controller;



import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import  com.revature.food4delivery.model.ERole;
import  com.revature.food4delivery.model.Role;
import  com.revature.food4delivery.model.User;
import com.revature.food4delivery.payload.request.SignupRequest;
import com.revature.food4delivery.payload.response.MessageResponse;
import com.revature.food4delivery.repository.RoleRepository;
import com.revature.food4delivery.repository.UserRepository;
@RestController
public class AuthController {@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;


@PostMapping("api/signup")

public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	if (userRepository.existsByUsername(signUpRequest.getUsername())) {
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Username is already taken!"));
	}
	if (userRepository.existsByEmail(signUpRequest.getEmail())) {
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: Email is already in use!"));
	}
	// Create new user's account
	User user = new User(signUpRequest.getUsername(), 
						 signUpRequest.getEmail(),
						 encoder.encode(signUpRequest.getPassword()));
	Set<String> strRoles = signUpRequest.getRole();
	Set<Role> roles = new HashSet<>();
	if (strRoles == null) {
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
	} else {
		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);
				break;
			case "mod":
				Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(modRole);
				break;
			default:
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			}
		});
	}
	user.setRoles(roles);
	userRepository.save(user);
	return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
}
}
