package com.portfolio.jjoony.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.jjoony.model.Project;
import com.portfolio.jjoony.service.ProjectService;
import com.portfolio.jjoony.service.S3Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectService service;
	private final S3Service s3service;
	
	
	@PostMapping(value="/insert_project", consumes = "multipart/form-data")
	public ResponseEntity<Integer> insert_project(@RequestPart(value="image", required=false) MultipartFile image,@RequestPart("project") Project project) {
	    int result = 0;
		if(image ==null) {
	    	result = -3;
	    	return new ResponseEntity<>(result,HttpStatus.OK);
	    }else {
	    	project.setImage(s3service.upload(image));
	    	result = service.insert(project);
	    	return new ResponseEntity<>(result, HttpStatus.OK);
	    	
	    }
	}
	
	
	
}
