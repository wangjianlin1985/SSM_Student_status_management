package com.java.service;
	
import org.apache.ibatis.annotations.Param;

import com.java.entity.PageBean;
import com.java.entity.Student;

import net.sf.json.JSONArray;

public interface StudentService {
	
	
	public JSONArray getStudentList(Student student,PageBean pageBean);
	
	public JSONArray getStudentListByState(String state,PageBean pageBean);
	
	public int getStudentCount(Student student);
	
	public int getStudentListByStateCount(String state);
	
	public Integer studentAdd(Student student);
	
	public Integer studentUpdate(Student student);
	
	public Integer studentUpdateState(Student student);
	
	public boolean existStudentWithStudentNo(String studentNo);
	/*
	 * 因为是学生信息 没有批量删除  老老实实一个个删除  删除还要修改 删除学生,系别,成绩表，奖励惩罚表
	 */
	public Integer studentDelete(String stuNo);


}
