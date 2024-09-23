package com.portfolio.jjoony.service;



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
	
	
	

}
