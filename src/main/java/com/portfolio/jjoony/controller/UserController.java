package com.portfolio.jjoony.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.jjoony.model.User;
import com.portfolio.jjoony.service.CustomUserDetialService;
import com.portfolio.jjoony.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final CustomUserDetialService userDetailService;
	private final UserService service;
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping("/insert_test")
	public ResponseEntity<Integer> insert_test() {
		User user=new User();
		int result = 0; 
		user.setId("test");
		user.setPwd("test1234");
		user.setRole("TEST");
		String encpassword = passwordEncoder.encode(user.getPwd());
		user.setPwd(encpassword);
		result = service.insertTest(user);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
