package com.java.service;

import com.java.entity.PageBean;

import net.sf.json.JSONArray;

public interface DeptService {
	public JSONArray getDeptList(PageBean pageBean);
	
	public int getDeptCount();
}
