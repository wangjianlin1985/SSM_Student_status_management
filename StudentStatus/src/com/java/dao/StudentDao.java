package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.entity.PageBean;
import com.java.entity.Student;

public interface StudentDao {
	//学号模糊查询
	public List<Student> getStudentList(@Param("student")Student student,@Param("pageBean")PageBean pageBean);
	
	public Student getStudentByStuNo(@Param("studentNo")String studentNo);
	
	public int getStudentCount(@Param("student")Student student);
	
	public List<Student> getStudentListByState(@Param("state")String state,@Param("pageBean")PageBean pageBean);
	 
	public int getStudentListByStateCount(@Param("state")String state);
	
	public Integer studentAdd(@Param("student")Student student);

	public Integer studentUpdate(@Param("student")Student student);
	
	public Integer studentUpdateState(@Param("student")Student student);
	
	public Integer existStudentWithStudentNo(@Param("studentNo")String studentNo);
	/*
	 * 因为是学生信息 没有批量删除  老老实实一个个删除
	 */
	public Integer studentDelete(@Param("studentNo")String studentNo);
	
	
	
}
