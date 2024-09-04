package com.portfolio.jjoony.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.jjoony.model.LoginRequest;
import com.portfolio.jjoony.model.LoginResponse;
import com.portfolio.jjoony.model.User;
import com.portfolio.jjoony.repository.UserDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserDAO dao;
	private final PasswordEncoder passwordEncoder;
	
	public int insertTest(User user) {
		return dao.insertTest(user);
	}

	public LoginResponse login(LoginRequest request) {
		User user = dao.selectUser(request.getId()); // 회원이 있는지 없는지 확인
		int result = 0;
		if (!passwordEncoder.matches(request.getPwd(), user.getPwd())) { // 암호화된 비밀번호와 회원이 입력한 값을 인코딩하여 비교
			throw new BadCredentialsException("잘못 입력하셨습니다.");
		}
		return LoginResponse.builder()
				.id(user.getId())
				.name(user.getName())
				.addr(user.getAddr())
				.birth(user.getBirth())
				.email(user.getEmail())
				.university(user.getUniversity())
				.license(user.getLicense())
				.role(user.getRole())
				.build();
	}
}
