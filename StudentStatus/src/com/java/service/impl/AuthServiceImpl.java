package com.java.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.java.dao.AuthDao;
import com.java.entity.Auth;
import com.java.service.AuthService;
import com.java.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("authService")
public class AuthServiceImpl implements AuthService{
	
	@Resource
	private AuthDao authDao;
	
	@Override
	public JSONArray getAuthByParentId(int parentId,String authIds) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		String[] authIdsStringArray=authIds.split(",");
		int[] authIdIntArray = new int[authIdsStringArray.length];
    	for (int i =0;i<authIdsStringArray.length;i++) {
    		authIdIntArray[i] = Integer.parseInt(authIdsStringArray[i]);
		}
		List<Auth> authList=authDao.getAuthByParentId(parentId,authIdIntArray);
		
		for(Auth auth :authList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", auth.getAuthId());
			jsonObject.put("text", auth.getAuthName());
			if(!hasChildren( auth.getAuthId(), authIds)){
				jsonObject.put("state", "open");
			}else{
				jsonObject.put("state", auth.getState());				
			}
			jsonObject.put("iconCls", auth.getIconCls());
			JSONObject attributeObject=new JSONObject();
			attributeObject.put("authPath", auth.getAuthPath());
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getAuthMenu(int parentId,String authIds) {
		// TODO Auto-generated method stub

		JSONArray jsonArray=this.getAuthByParentId(parentId,authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getAuthMenu(Integer.parseInt(jsonObject.getString("id")),authIds));
			}
		}
		return jsonArray;
	}

	public boolean hasChildren(int parentId,String authIds){
		String[] authIdsStringArray=authIds.split(",");
		int[] authIdIntArray = new int[authIdsStringArray.length];
    	for (int i =0;i<authIdsStringArray.length;i++) {
    		authIdIntArray[i] = Integer.parseInt(authIdsStringArray[i]);
		}
		List<Auth> authList=authDao.getAuthByParentId(parentId,authIdIntArray);
		if(authList == null){
			return false;
		}
		return true;
	}

	@Override
	public JSONArray getTreeGridAuthByParentId(int parentId) {
		JSONArray jsonArray = new JSONArray();
		List<Auth> authList	= authDao.getTreeGridAuthByParentId(parentId);
		for(Auth auth:authList){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id",auth.getAuthId());
			jsonObject.put("text",auth.getAuthName());
			jsonObject.put("state", auth.getState());
			jsonObject.put("iconCls", auth.getIconCls());
			jsonObject.put("authPath", auth.getAuthPath());
			jsonObject.put("authDescription", auth.getAuthDescription());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public JSONArray getTreeGridAuthMenu(int parentId) {
		// TODO Auto-generated method stub
		JSONArray jsonArray=this.getTreeGridAuthByParentId(parentId);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getTreeGridAuthMenu(Integer.parseInt(jsonObject.getString("id"))));
			}
		}
		return jsonArray;
	}

	@Override
	public boolean isLeaf(int authId) {
		// TODO Auto-generated method stub
		Integer code = authDao.isLeaf(authId);
		System.out.println(code);
		if(code==null){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Integer authAdd(Auth auth) {
		// TODO Auto-generated method stub
		return authDao.authAdd(auth);
	}

	@Override
	public Integer authUpdate(Auth auth) {
		// TODO Auto-generated method stub
		return authDao.authUpdate(auth);
	}

	@Override
	public Integer authDelete(int authId) {
		// TODO Auto-generated method stub
		return authDao.authDelete(authId);
	}

	
	public Integer updateStateByAuthId(String state,int authId){
		return authDao.updateStateByAuthId(state,authId);
	}

	@Override
	public int getAuthCountByParentId(int parentId) {
		// TODO Auto-generated method stub
		return authDao.getAuthCountByParentId(parentId);
	}

	@Override
	public JSONArray getCheckedAuth(int parentId , String authIds) {
		JSONArray jsonArray = new JSONArray();
		List<Auth> authList=authDao.getCheckedAuth(parentId);
		for(Auth auth:authList){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", auth.getAuthId());
			jsonObject.put("text", auth.getAuthName());
			jsonObject.put("state", auth.getState());
			jsonObject.put("iconCls", auth.getIconCls());
	
			try {
				if(StringUtil.existStrArr(auth.getAuthId()+"", authIds.split(","))){
					jsonObject.put("checked", true);
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
				return null;
			}
			JSONObject attributeObject=new JSONObject();
			attributeObject.put("authPath", auth.getAuthPath());
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
				
	}

	@Override
	public JSONArray getCheckedAuthMenus(int parentId,String authIds) {
		// TODO Auto-generated method stub
		JSONArray jsonArray=this.getCheckedAuth(parentId,authIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("open".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getCheckedAuthMenus(jsonObject.getInt("id"),authIds));
			}
		}
		return jsonArray;
	}
}
