package com.portfolio.jjoony.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portfolio.jjoony.model.Project;

@Mapper
public interface ProjectDAO {

	int insert(Project project);

	List<Project> getProjects(String id);

	int deleteProject(int no);

	Project getProject(int no);

	int updateProject(Project project);

}
