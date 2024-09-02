package com.portfolio.jjoony.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.jjoony.model.CustomUserDetails;
import com.portfolio.jjoony.model.User;
import com.portfolio.jjoony.repository.UserDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetialService implements UserDetailsService{
	
	private final UserDAO dao;
	
	//사용자 이름으로 찾은 UserDetails 인스턴스를 반환 이름을 찾지못하면 UsernameNotFoundException반환
	 @Override
	    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
	        User user = dao.selectUser(id);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with id: " + id);
	        }
	        return new CustomUserDetails(user);
	    }
}
