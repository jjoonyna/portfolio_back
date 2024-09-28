package com.portfolio.jjoony.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.jjoony.security.LoginRequest;
import com.portfolio.jjoony.security.LoginResponse;
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


	//about정보 찾아오기
	public LoginResponse findabout(String id) {
		User user = dao.selectUser(id);
		if(user == null) {
			System.out.println("정보를 찾을수 없습니다");
		}
		return new LoginResponse(user);
	}
	//about 주소, 자격증, email 정보 수정
	public LoginResponse updatae(LoginRequest request) {
		User user = dao.selectUser(request.getId());
		user.setAddr(request.getAddr());
		user.setLicense(request.getLicense());
		user.setEmail(request.getEmail());
		dao.update(user);
		return new LoginResponse(user);
	}
}
