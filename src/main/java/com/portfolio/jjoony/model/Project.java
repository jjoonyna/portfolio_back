package com.portfolio.jjoony.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("project")
public class Project {

	private int no;
	private String subject;
	private String summary;
	private Date startDate;
	private Date endDate;
	private int person;
	private String content;
	private String link;
	private String problem;
	private String solution;
	private String image;
	private String id;
}
