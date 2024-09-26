package com.portfolio.jjoony.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("skills")
public class Skills {
	private String skill;
	private Date regist;
	private String id;
	private String image;
	private String category;
}
