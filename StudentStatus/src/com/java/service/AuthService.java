package com.java.service;


import com.java.entity.Auth;

import net.sf.json.JSONArray;

public interface AuthService {
	public JSONArray getAuthByParentId(int parentId,String authIds);
	
	public JSONArray getAuthMenu(int parentId,String authIds);
		
	public boolean hasChildren(int parentId,String authIds);
	
	public JSONArray getTreeGridAuthByParentId(int parentId);
	
	public JSONArray getTreeGridAuthMenu(int parentId);
	
	public boolean isLeaf(int authId);
	
	public Integer authAdd(Auth auth);
	
	public Integer authUpdate(Auth auth);
	
	public Integer authDelete(int authId);
	
	public Integer updateStateByAuthId(String state,int authId);
	
	public int getAuthCountByParentId(int parentId);

	public JSONArray getCheckedAuth(int parentId,String authIds);
	
	public JSONArray getCheckedAuthMenus(int parentId,String authIds);
}
