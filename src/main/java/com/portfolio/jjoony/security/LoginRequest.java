package com.portfolio.jjoony.security;

import lombok.Data;

@Data
public class LoginRequest {
	private String id;
	private String pwd;
	private String name;
	private String addr;
	private String birth;
	private String email;
	private String university;
	private String license;
	private String role;
}
