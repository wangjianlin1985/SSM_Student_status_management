package com.java.service;


import java.util.List;

import com.java.entity.Grade;

import net.sf.json.JSONArray;

public interface GradeService {
	
	public JSONArray getGradeList(String studentNo);
	
	public List<Grade> getGradeListByScore(String studentNo);
	
	public Integer addGrade(Grade grade);
	
	public Integer gradeUpdate(Grade grade);
	
	public Integer gradeDelete(String studentNo);
	
	public boolean exitGrade(String studentNo);
}
