package com.java.service;

import com.java.entity.PageBean;
import com.java.entity.Role;

import net.sf.json.JSONArray;

public interface RoleService {
	
	public String getAuthIds(int id);
	
	public JSONArray getRoleList(Role role,PageBean pageBean);
	
	public int getRoleListCount(Role role);
	
	public int roleAdd(Role role);
	
	public int roleUpdate(Role role);
	
	public int roleAuthIdsUpdate(Role role);
	
	public int roleDelete(String roleIds);
	

}
