package com.java.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.PageBean;
import com.java.entity.User;
import com.java.service.UserService;
import com.java.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

	

@Controller  
@RequestMapping("/user")   
public class UserController {

	@Resource
	private UserService userService;
	
	@ResponseBody 
	@RequestMapping("/userlist.do")
	public String userList(HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		User user = null;
		String s_userName=request.getParameter("s_userName");
		String s_roleId=request.getParameter("s_roleId");
		if(StringUtil.isNotEmpty(s_userName)||StringUtil.isNotEmpty(s_roleId)){
			user = new User();
		}
		
		if(StringUtil.isNotEmpty(s_userName)){
			user.setUserName(s_userName);
		}
		
		if(StringUtil.isNotEmpty(s_roleId)){
			user.setRoleId(Integer.parseInt(s_roleId));
		}
		
		PageBean pageBean = null;
		
		if (page!=null&&rows!=null) {			
			pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		}
		
		JSONArray jsonArray = userService.getUserList(user, pageBean);
		int total = userService.getUserCount(user);
		JSONObject result=new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", total);
		return result.toString();		
	}
	
	@ResponseBody 
	@RequestMapping("/save.do")
	public String saveUser(HttpServletRequest request){
		String userName=request.getParameter("name");
		String password=request.getParameter("password");
		String roleId=request.getParameter("roleId");
		String userId=request.getParameter("userId");
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setRoleId(Integer.parseInt(roleId));
		if(StringUtil.isNotEmpty(userId)){
			user.setId(Integer.parseInt(userId));
		}
		
		JSONObject result=new JSONObject();
	
		int saveNums=0;
		if(StringUtil.isNotEmpty(userId)){
			saveNums=userService.updateUser(user);
		}else{
			if(userService.testExitUser(userName)!=null){
				saveNums=-1;
			}else{
				saveNums=userService.addUser(user);					
			}
		}
		if(saveNums==-1){
			result.put("success", true);
			result.put("errorMsg", "此用户名已经存在");
		}else if(saveNums==0){
			result.put("success", true);
			result.put("errorMsg", "保存失败");
		}else{
			result.put("success", true);
		}
		return result.toString();
	}

	
	@ResponseBody 
	@RequestMapping("/modifyPassword.do")
	public String modifyPassword(HttpServletRequest request){
		String id =request.getParameter("userId");
		String newPassword=request.getParameter("newPassword");
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setPassword(newPassword);
		int updateNum=userService.modifyPassword(user);
		
		JSONObject result=new JSONObject();
		if(updateNum>0){
			result.put("success", "true");
			request.getSession().invalidate();
		}else{
			result.put("success", "true");
			result.put("errorMsg", "修改密码失败！");
		}
		return result.toString();
		
	}
	
	@ResponseBody 
	@RequestMapping("/deleteUser.do")
	public String deleteUser(HttpServletRequest request){
		String delIds=request.getParameter("delIds");
		System.out.println(delIds);
		Integer code =userService.deleteUser(delIds);
		JSONObject result=new JSONObject();

		if(code>0){
			result.put("success", true);
			result.put("delNums", code);
		}else{
			result.put("errorMsg", "删除失败");
		}
		return result.toString();
	}
	
	

}
