package com.portfolio.jjoony.service;

import org.springframework.stereotype.Service;

import com.portfolio.jjoony.model.User;
import com.portfolio.jjoony.repository.UserDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserDAO dao;
	
	public int insertTest(User user) {
		return dao.insertTest(user);
	}

}
