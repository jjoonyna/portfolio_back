package com.portfolio.jjoony.model;



import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("user")
public class User {
	
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
