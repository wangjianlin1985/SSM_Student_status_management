package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.DeptDao;
import com.java.entity.Dept;
import com.java.entity.PageBean;
import com.java.service.DeptService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("deptService")
public class DeptServiceImpl implements DeptService{

	@Resource
	private DeptDao deptDao;
	
	@Override
	public JSONArray getDeptList(PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Dept> list = deptDao.getDeptList(pageBean);
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			Dept dept = new Dept();
			dept = list.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", dept.getId());
			jsonObject.put("deptNo", dept.getDeptNo());
			jsonObject.put("deptName", dept.getDeptName());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public int getDeptCount() {
		// TODO Auto-generated method stub
		return deptDao.getDeptCount();
	}

}
