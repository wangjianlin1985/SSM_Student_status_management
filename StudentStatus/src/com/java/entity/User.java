package com.java.entity;


/*
 * 
 * 普通用户表
 */

public class User {
	
	private Integer id ;		//主键ID
	
	private String userName;		//用户名
							
	private String password;	//密码
	
	private Integer roleId;		//角色ID
	
	private Role role;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	
	
	
}
