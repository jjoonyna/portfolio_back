package com.portfolio.jjoony.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.jjoony.model.LoginRequest;
import com.portfolio.jjoony.model.LoginResponse;
import com.portfolio.jjoony.model.User;
import com.portfolio.jjoony.service.CustomUserDetialService;
import com.portfolio.jjoony.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;

	
	
//	회원 로그인	
	@PostMapping("/login_user")
	public ResponseEntity<LoginResponse> login_ok(@RequestBody LoginRequest request){
		return new ResponseEntity<>(service.login(request),HttpStatus.OK);

	}
	
	
	
//  개인 포트폴리오 사이트라 관리계정, 테스트계정 제외 회원가입 없음	
//	@PostMapping("/insert_user")
//	public ResponseEntity<Integer> insert_test() {
//		User user=new User();
//		int result = 0; 
//		user.setId("test1");
//		user.setPwd("test1234");
//		user.setRole("TEST");
//		String encpassword = passwordEncoder.encode(user.getPwd());
//		user.setPwd(encpassword);
//		result = service.insertTest(user);
//		
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
	
}
