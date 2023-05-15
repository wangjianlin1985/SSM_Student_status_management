package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.entity.Auth;


public interface AuthDao {
	
	public List<Auth> getAuthByParentId(@Param("parentId")int parentId,@Param("authIds")int[] authIds);
	
	public List<Auth> getTreeGridAuthByParentId(int parentId);
	
	public List<Auth> getCheckedAuth(@Param("parentId")int parentId);
	
	public Integer authAdd(@Param("auth")Auth auth);
	
	public Integer authUpdate(@Param("auth")Auth auth);
	
	public Integer authDelete(@Param("authId")int authId);
	
	//查询是否有子节点
	public Integer isLeaf(@Param("authId")int authId);
	
	public Integer updateStateByAuthId(@Param("state")String state,@Param("authId")int authId);
	
	public int getAuthCountByParentId(@Param("parentId")int parentId);
}
