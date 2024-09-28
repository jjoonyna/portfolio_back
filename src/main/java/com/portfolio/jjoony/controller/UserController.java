package com.portfolio.jjoony.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.jjoony.model.User;
import com.portfolio.jjoony.security.CustomUserDetails;
import com.portfolio.jjoony.security.LoginRequest;
import com.portfolio.jjoony.security.LoginResponse;
import com.portfolio.jjoony.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
//	private final PasswordEncoder passwordEncoder;
	private final UserService service;

	
//	about의 정보 찾아오기	
	@GetMapping("/find_about/{id}")
	public ResponseEntity<LoginResponse> find_about(@PathVariable("id") String id){
		return new ResponseEntity<>(service.findabout(id),HttpStatus.OK);
	}
	
//	about 주소, 자격증, email 정보 수정
	@PutMapping("/update_about")
	public ResponseEntity<LoginResponse> update_about(@RequestBody LoginRequest request){
		return new ResponseEntity<>(service.updatae(request),HttpStatus.OK);
	}
	
	
//  개인 포트폴리오 사이트라 관리계정, 테스트계정 제외 회원가입 없음	
//	@PostMapping("/insert_user")
//	public ResponseEntity<Integer> insert_test() {
//		User user=new User();
//		int result = 0; 
//		user.setId("test");
//		user.setPwd("test1234");
//		user.setName("테스트유저");
//		user.setAddr("테스트주소");
//		user.setBirth("테스트생일");
//		user.setEmail("테스트이메일");
//		user.setUniversity("테스트학교");
//		user.setLicense("없음");
//		user.setRole("ROLE_TEST");
//		String encpassword = passwordEncoder.encode(user.getPwd());
//		user.setPwd(encpassword);
//		result = service.insertTest(user);
//		
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
//	
}
