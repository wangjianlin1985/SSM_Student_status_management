package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.entity.Grade;

public interface GradeDao {
	
	public List<Grade> getGradeList(@Param("studentNo")String studentNo);
	
	public List<Grade> getGradeState(@Param("studentNo")String studentNo);
	
	public Integer	updateGrade(@Param("grade")Grade grade);
	
	public Integer exitGrade(@Param("studentNo")String studentNo);
	
	public Integer deleteGrade(@Param("studentNo")String studentNo);
	
	public Integer addGrade(@Param("grade")Grade grade);
}
