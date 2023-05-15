package com.java.entity;

/*
 * 
 * 系别表
 */

public class Dept {

	private int id;				//主键id
	
	private String deptNo;		//系别编号
	
	private String deptName;	//系别名称

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
	
}
