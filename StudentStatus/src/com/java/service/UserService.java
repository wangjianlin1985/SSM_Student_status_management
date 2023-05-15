package com.java.service;

import com.java.entity.PageBean;
import com.java.entity.User;

import net.sf.json.JSONArray;


public interface UserService {
	
	public boolean login(User user);
	
	public User getUser(User user);
	
	public Integer modifyPassword(User user);
	
	public JSONArray getUserList(User user,PageBean pageBean);
	
	public int getUserCount(User user);
	
	public Integer testExitUser(String name);
	
	public Integer updateUser(User user);
	
	public Integer addUser(User user);
	
	public Integer deleteUser(String userIds);
	
	public boolean existUserWithRoleId(String roleId);
}
