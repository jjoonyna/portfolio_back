package com.portfolio.jjoony.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Integer> insert_project(@RequestPart(value="image", required=false) MultipartFile image,
													@RequestPart("project") Project project) {
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
	
	@GetMapping("/list_project/{id}")
	public ResponseEntity<List<Project>> list_project(@PathVariable("id") String id){
		List<Project> projectList = service.getProjects(id);
		return new ResponseEntity<>(projectList,HttpStatus.OK);
	}
	
	@PutMapping(value="/update_project", consumes = "multipart/form-data")
	public ResponseEntity<Integer> update_project(@RequestPart(value="image", required=false) MultipartFile image,
													@RequestPart("project") Project project){
		int result = 0;
		if(image ==null) {
	    	result = -3;
	    	return new ResponseEntity<>(result,HttpStatus.OK);
	    }else {
	    	Project beforeProject = service.getProject(project.getNo());
	    	s3service.deleteImageFromS3(beforeProject.getImage());
	    	System.out.println("기존 이미지삭제 완료");
	    	project.setImage(s3service.upload(image));
	    	result = service.updateProject(project);
	    	return new ResponseEntity<>(result,HttpStatus.OK);
	    }
	}
	
	@DeleteMapping("/delete_project/{no}")
	public ResponseEntity<Integer> delete_project(@PathVariable("no") int no){
		Project project = service.getProject(no);
		String image = project.getImage();
		s3service.deleteImageFromS3(image);
		System.out.println("이미지삭제 완료");
		int result = service.deleteProject(no);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
