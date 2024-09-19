package com.portfolio.jjoony.service;



import org.springframework.stereotype.Service;

import com.portfolio.jjoony.repository.ProjectDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

	private final ProjectDAO project;
	

}
