package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.entity.Dept;
import com.java.entity.PageBean;

public interface DeptDao {
	public List<Dept> getDeptList(@Param("pageBean")PageBean pageBean);
	
	public int getDeptCount();
}
