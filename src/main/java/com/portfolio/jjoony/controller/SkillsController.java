package com.portfolio.jjoony.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.jjoony.model.Project;
import com.portfolio.jjoony.model.Skills;
import com.portfolio.jjoony.service.S3Service;
import com.portfolio.jjoony.service.SkillsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SkillsController {
	private final SkillsService service;
	private final S3Service s3service;
	
	@PostMapping(value="/insert_skills", consumes = "multipart/form-data")
	public ResponseEntity<Integer> insert_skills(@RequestPart(value="image", required=false) MultipartFile image,
													@RequestPart("skills") Skills skills) {
	    int result = 0;
		if(image ==null) {
	    	result = -3;
	    	return new ResponseEntity<>(result,HttpStatus.OK);
	    }else {
	    	skills.setImage(s3service.upload(image));
	    	result = service.insert(skills);
	    	return new ResponseEntity<>(result, HttpStatus.OK);
	    	
	    }
	}
	
	@GetMapping("/list_skills/{id}")
	public ResponseEntity<List<Skills>> list_skills(@PathVariable("id") String id){
		List<Skills> skillsList = service.getSkills(id);
		return new ResponseEntity<>(skillsList,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete_skills/{skill}")
	public ResponseEntity<Integer> delete_skills(@PathVariable("skill") String skill){
		Skills skills = service.getSkill(skill);
		String image = skills.getImage();
		s3service.deleteImageFromS3(image);
		System.out.println("이미지삭제 완료");
		int result = service.deleteSkills(skill);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
