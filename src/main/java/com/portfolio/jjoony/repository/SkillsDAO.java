package com.portfolio.jjoony.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.portfolio.jjoony.model.Project;
import com.portfolio.jjoony.model.Skills;

@Mapper
public interface SkillsDAO {

	int insert(Skills skills);

	List<Skills> getSkills(String id);

	Skills getSkill(String skill);

	int deleteSkills(String skill);

}
