package com.portfolio.jjoony.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.portfolio.jjoony.model.Project;
import com.portfolio.jjoony.model.Skills;
import com.portfolio.jjoony.repository.SkillsDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillsService {
	
	private final SkillsDAO dao;
	
	
	public int insert(Skills skills) {
		return dao.insert(skills);
	}

	public List<Skills> getSkills(String id) {
		return dao.getSkills(id);
	}

	public Skills getSkill(String skill) {
		return dao.getSkill(skill);
	}

	public int deleteSkills(String skill) {
		return dao.deleteSkills(skill);
	}

}
