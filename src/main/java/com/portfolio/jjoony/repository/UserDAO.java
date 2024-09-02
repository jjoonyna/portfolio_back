package com.portfolio.jjoony.repository;

import org.apache.ibatis.annotations.Mapper;

import com.portfolio.jjoony.model.User;


@Mapper
public interface UserDAO {

	User selectUser(String id);

	int insertTest(User user);

		

}
