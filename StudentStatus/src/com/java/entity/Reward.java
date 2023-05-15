package com.java.entity;


/*
 * 
 * 奖励惩罚表
 */


public class Reward {

	
	private int id; 	//主键ID
	
	private String studentNo;	//学号
	
	private String deptNo;		//系别号
	
	private String rewardInfo;	//奖惩描述
	
	private Student student;
	
	private Dept dept;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getRewardInfo() {
		return rewardInfo;
	}

	public void setRewardInfo(String rewardInfo) {
		this.rewardInfo = rewardInfo;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	
}
