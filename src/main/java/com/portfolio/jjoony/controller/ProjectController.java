package com.portfolio.jjoony.controller;

import org.springframework.web.bind.annotation.RestController;

import com.portfolio.jjoony.service.ProjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectService service;
	
	
}
