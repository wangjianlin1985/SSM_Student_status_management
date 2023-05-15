package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.entity.PageBean;
import com.java.entity.Role;

public interface RoleDao {
	
	
	public String getAuthIds(int id);
	
	public List<Role> getRoleList(@Param("role")Role role,@Param("pageBean")PageBean pageBean);
	
	public int getRoleListCount(@Param("role")Role role);
	
	public Integer roleAdd(@Param("role")Role role);
	
	public Integer roleUpdate(@Param("role")Role role);
	
	public Integer roleAuthIdsUpdate(@Param("role")Role role);
	
	public Integer roleDelete(@Param("roleIds")int[] roleIds);
} 
