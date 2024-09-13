package com.portfolio.jjoony.security;

import com.portfolio.jjoony.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class LoginResponse {
	private String id;
	private String pwd;
	private String name;
	private String addr;
	private String birth;
	private String email;
	private String university;
	private String license;
	private String role;
	private String message;
	
	public LoginResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.addr = user.getAddr();
		this.birth = user.getBirth();
		this.email = user.getEmail();
		this.university = user.getUniversity();
		this.license = user.getLicense();
		this.role = user.getRole();
	}
}
