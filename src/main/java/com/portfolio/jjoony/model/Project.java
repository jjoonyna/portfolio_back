package com.portfolio.jjoony.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("project")
public class Project {

	private int pro_no;
	private String pro_subject;
	private String pro_summary;
	private Date pro_startDate;
	private Date pro_endDate;
	private String pro_comment;
	private String pro_role;
	private String pro_problem;
	private String pro_solution;
	private String pro_result;
	private String pro_image;
	private String id;
}
