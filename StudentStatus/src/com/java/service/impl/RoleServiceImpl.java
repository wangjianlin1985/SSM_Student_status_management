package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.RoleDao;
import com.java.entity.PageBean;
import com.java.entity.Role;
import com.java.service.RoleService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao;
	
	@Override
	public String getAuthIds(int id) {
		// TODO Auto-generated method stub
		String a= roleDao.getAuthIds(id);
		return  a;
	}

	@Override
	public JSONArray getRoleList(Role role, PageBean pageBean) {
		List<Role> roleList= roleDao.getRoleList(role, pageBean);
		JSONArray jsonArray = new JSONArray();
		for(int i =0 ;i<roleList.size();i++){
			JSONObject jsonObject = new JSONObject();
			Role r = new Role();
			r = roleList.get(i);
			jsonObject.put("roleId", r.getRoleId());
			jsonObject.put("roleName", r.getRoleName());
			jsonObject.put("authIds", r.getAuthIds());
			jsonObject.put("roleDescription", r.getRoleDescription());
			jsonArray.add(jsonObject);
		}
		// TODO Auto-generated method stub
		return jsonArray;
	}

	@Override
	public int getRoleListCount(Role role) {
		// TODO Auto-generated method stub
		return roleDao.getRoleListCount(role);
	}

	@Override
	public int roleAdd(Role role) {
		// TODO Auto-generated method stub
		return roleDao.roleAdd(role);
	}

	@Override
	public int roleUpdate(Role role) {
		// TODO Auto-generated method stub
		return roleDao.roleUpdate(role);
	}
	
	public int roleAuthIdsUpdate(Role role){
		return roleDao.roleAuthIdsUpdate(role);
	}

	@Override
	public int roleDelete(String roleIds) {
		// TODO Auto-generated method stub
		String[] strArray = roleIds.split(",");
		int[] array = new int[strArray.length];
		for(int i=0;i<strArray.length;i++){
			array[i]=Integer.parseInt(strArray[i]);
		}	
		return roleDao.roleDelete(array);
	}
	
	
		
}
