package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.entity.PageBean;
import com.java.entity.User;


public interface UserDao {
	
	public User login(@Param("user")User user);
	
	public List<User> getUserList(@Param("user")User user,@Param("pageBean")PageBean pageBean);
	
	public int getUserCount(@Param("user")User user);
	
	public Integer modifyPassword(@Param("user")User user);
	
	/**
	 * 查询是否存在此用户
	 * @param name
	 * @return
	 */
	public Integer existUserWithuserName(String name);
	
	public Integer updateUser(@Param("user")User user);
	
	public Integer addUser(@Param("user")User user);
	
	public Integer deleteUser(@Param("userIds")int[] userIds);
	
	public Integer existUserWithRoleId(@Param("roleId")int roleId);
}
