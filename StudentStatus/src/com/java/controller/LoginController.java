package com.java.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.Auth;
import com.java.entity.User;
import com.java.service.AuthService;
import com.java.service.RoleService;
import com.java.service.UserService;
import com.java.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller  
@RequestMapping("/login")   
public class LoginController {  
	
	@Resource
	private UserService userService;
	
	@Resource
	private AuthService authService;
    
	@Resource
	private RoleService roleService;
	
    @RequestMapping("/main.do")
    public String main(HttpServletRequest request){
    	HttpSession session=request.getSession();
    	if(session.getAttribute("user")!=null){
    		return "main";
    	}else{
    		String userName = request.getParameter("userName");
        	String password = request.getParameter("password");
        	User user = new User();
        	user.setUserName(userName);
        	user.setPassword(password);
        	User u=userService.getUser(user);
        	if(u!=null){
    			session.setAttribute("user", u);
    			return "main";
        	}
    	}
    	request.setAttribute("error", "用户名或密码错误！");
    	return "/login";
    }
    
    
    @RequestMapping("/login.do")
    public String login(){
//    	return "main";
    	return "/login";
    }
    
    
    @ResponseBody  
    @RequestMapping("/getMenu.do")
    public String  getMenu(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	String parentId=request.getParameter("parentId");
    	User user =(User) session.getAttribute("user");
    	User u = userService.getUser(user);
    	String authIds = roleService.getAuthIds(u.getRoleId());
//    	String authIds = roleService.getAuthIds(1);
    	JSONArray jsonArray=authService.getAuthMenu(Integer.parseInt(parentId), authIds);
    	return jsonArray.toString();
    }
    
    
    @ResponseBody  
    @RequestMapping("/getAuthMenu.do")
    public String  getAuthMenu(HttpServletRequest request){
    	String parentId=request.getParameter("parentId");
		String roleId=request.getParameter("roleId");
    	String str ="";
    	String authIds = roleService.getAuthIds(Integer.parseInt(roleId));
    	JSONArray jsonArray=authService.getAuthMenu(Integer.parseInt(parentId), authIds);
    	str=jsonArray.toString();
    	return str;
    }
    
    @ResponseBody 
    @RequestMapping("/getTreeGridAuthMenu.do")
    public String getTreeGridAuthMenu(HttpServletRequest request){
    	String parentId = request.getParameter("parentId");
    	System.out.println(parentId);
     	JSONArray jsonArray=authService.getTreeGridAuthMenu(Integer.parseInt(parentId));
     	return jsonArray.toString();
    }
    
    @ResponseBody
    @RequestMapping("/getCheckedAuthMenu.do")
    public String getCheckedsAuthMenu(HttpServletRequest request){
    	String parentId=request.getParameter("parentId");
		String roleId=request.getParameter("roleId");
		HttpSession session = request.getSession();
		User user =(User) session.getAttribute("user");
		Integer id = null;
		if(user!=null){
			id = user.getRoleId();
		}
		String authIds = "";
		if(id==1){
			Integer roleIntValue = Integer.parseInt(roleId);
//			if(id !=roleIntValue){
				authIds = roleService.getAuthIds(roleIntValue);
//			}
		}else{
			authIds = null;
		}
		JSONArray jsonArray  = new JSONArray();
    	try {
        	jsonArray = authService.getCheckedAuthMenus(Integer.parseInt(parentId), authIds);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return null;
		}
    	return jsonArray.toString();
    }
    
    
    @ResponseBody 
    @RequestMapping("/authsave.do")
    public String authSave(HttpServletRequest request){
		String authId = request.getParameter("authId");
		String authName = request.getParameter("authName");
		String authPath = request.getParameter("authPath");
		String parentId = request.getParameter("parentId");
		String authDescription = request.getParameter("authDescription");
		String iconCls = request.getParameter("iconCls");
		Auth auth = new Auth();
		auth.setAuthName(authName);
		auth.setAuthPath(authPath);
		auth.setAuthDescription(authDescription);
		auth.setIconCls(iconCls);
		// Auth auth=new Auth(authName, authPath, authDescription, iconCls);

		if (StringUtil.isNotEmpty(authId)) {
			auth.setAuthId(Integer.parseInt(authId));
		} else {
			auth.setParentId(Integer.parseInt(parentId));
		}
		boolean isLeaf = false;
		JSONObject result = new JSONObject();
		int saveNums = 0;
		if (StringUtil.isNotEmpty(authId)) {
			saveNums = authService.authUpdate(auth);
		} else {
			isLeaf = authService.isLeaf(Integer.parseInt(parentId));
			if (!isLeaf) {
				saveNums = authService.authAdd(auth);
				saveNums = authService.updateStateByAuthId("closed",Integer.parseInt(parentId));
			} else {
				saveNums = authService.authAdd(auth);
			}
		}
		if (saveNums > 0) {
			result.put("success", true);
		} else {
			result.put("success", true);
			result.put("errorMsg", "保存失败");
		}

		return result.toString();
    }
    
    @ResponseBody
    @RequestMapping("/authdelete.do")
    public String authDelete(HttpServletRequest request){
    	String authId=request.getParameter("authId");
		String parentId = request.getParameter("parentId");
		int sonNum = -1;

		JSONObject result = new JSONObject();
		if (authService.isLeaf(Integer.parseInt(authId))) {
			result.put("errorMsg", "该菜单节点有子节点，不能删除！");
		} else {
			int delNums = 0;
			sonNum = authService.getAuthCountByParentId(Integer.parseInt(parentId));
			if (sonNum == 1) {
				delNums = authService.authDelete(Integer.parseInt(authId));
				authService.updateStateByAuthId("open", Integer.parseInt(parentId));
			} else {
				delNums = authService.authDelete(Integer.parseInt(authId));
			}
			if (delNums > 0) {
				result.put("success", true);
			} else {
				result.put("errorMsg", "删除失败");
			}
		}
		return result.toString();
    }
    
    @RequestMapping("/logout.do")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "/login";
	}

}  