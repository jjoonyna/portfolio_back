package com.portfolio.jjoony.repository;

import org.apache.ibatis.annotations.Mapper;

import com.portfolio.jjoony.model.Project;

@Mapper
public interface ProjectDAO {

	int insert(Project project);

}
