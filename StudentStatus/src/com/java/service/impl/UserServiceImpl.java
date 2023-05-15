package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.UserDao;
import com.java.entity.PageBean;
import com.java.entity.User;
import com.java.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
    private UserDao userDao;

	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		User u=userDao.login(user);
		if(u!=null){
			return true;
		}
		return false;
	}

	
	public JSONArray getUserList(User user,PageBean pageBean){
		if(user==null||pageBean==null){
			System.out.println("输出为NULL");
		}
		JSONArray jsonArray = new JSONArray();
		List<User> list =  userDao.getUserList(user, pageBean);
		for(int i =0;i<list.size();i++){
			JSONObject jsonObject = new JSONObject();
			User u = new User();
			u=list.get(i);
			jsonObject.put("id", u.getId());
			jsonObject.put("name", u.getUserName());
			jsonObject.put("password", u.getPassword());
			jsonObject.put("roleId", u.getRoleId());
			jsonObject.put("roleName", u.getRole().getRoleName());
			jsonArray.add(jsonObject);			
			
		}
		return jsonArray;
	}
	
	public int getUserCount(User user){
		return userDao.getUserCount(user);
	}
	
	public Integer testExitUser(String name){
		return userDao.existUserWithuserName(name);
	}
	
	public Integer updateUser(User user){
		return userDao.updateUser(user);
	}
	
	public Integer addUser(User user){
		return userDao.addUser(user);
	}
	
	public Integer deleteUser(String userIds){
		if(userIds!=null||(userIds!=" ")){
			
			String[] userIdArrayStr=userIds.split(",");
			int[] userIdArray = new int[userIdArrayStr.length];
			for(int i =0;i<userIdArrayStr.length;i++){
				userIdArray[i] = Integer.parseInt(userIdArrayStr[i]);
			}
			return userDao.deleteUser(userIdArray);
		}
		return -1;
	}


	@Override
	public boolean existUserWithRoleId(String roleId) {
		// TODO Auto-generated method stub
		Integer code=userDao.existUserWithRoleId(Integer.parseInt(roleId));
		System.out.println(code);
		if(code<=0){
			return true;
		}else {
			return false;
		}
	}


	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}


	@Override
	public Integer modifyPassword(User user) {
		// TODO Auto-generated method stub
		return userDao.modifyPassword(user);
	}
}
