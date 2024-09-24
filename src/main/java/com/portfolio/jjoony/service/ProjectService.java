package com.portfolio.jjoony.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.jjoony.model.Project;
import com.portfolio.jjoony.repository.ProjectDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	private final ProjectDAO dao;

	public int insert(Project project) {
		int result = dao.insert(project);
		return result;
	}

	public List<Project> getProjects(String id) {
		return dao.getProjects(id);
	}
	public Project getProject(int no) {
		return dao.getProject(no);
	}

	public int deleteProject(int no) {
		return dao.deleteProject(no);
	}

	public int updateProject(Project project) {
		System.out.println("hihi");
		return dao.updateProject(project);
	}
	
	
	

}
