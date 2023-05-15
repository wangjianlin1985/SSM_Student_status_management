package com.java.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.PageBean;
import com.java.entity.Role;
import com.java.service.RoleService;
import com.java.service.UserService;
import com.java.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/rolelist.do")
	public String getRoleList(HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageBean pageBean = null;
		if (page!=null&&rows!=null) {			
			pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		}
		
		String s_roleName=request.getParameter("s_roleName");
		Role role = null;
		if(StringUtil.isNotEmpty(s_roleName)){
			role =new Role();
			role.setRoleName(s_roleName);
		}
		JSONArray jsonArray = roleService.getRoleList(role, pageBean);
		int total = roleService.getRoleListCount(role);
		JSONObject result=new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", total);
		System.out.println("role/roleList.do:"+result);
		return result.toString();
	}
	
	@ResponseBody
	@RequestMapping("/comBoList.do")
	public String getComBoList(HttpServletRequest request){
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("roleId", "");
		jsonObject.put("roleName", "请选择...");
		jsonArray.add(jsonObject);
		jsonArray.addAll(roleService.getRoleList(null, null));
		System.out.println("role/comBoList.do:"+jsonArray);
		return jsonArray.toString();
	}
	
	@ResponseBody
	@RequestMapping("/save.do")
	public String saveRole(HttpServletRequest request){
		String roleName=request.getParameter("roleName");
		String roleDescription=request.getParameter("roleDescription");
		String roleId=request.getParameter("roleId");
//		Role role=new Role(roleName, roleDescription);
		Role role = new Role();
		role.setRoleName(roleName);
		role.setRoleDescription(roleDescription);
		if (StringUtil.isNotEmpty(roleId)) {
			role.setRoleId(Integer.parseInt(roleId));
		}

		JSONObject result = new JSONObject();
		int saveNums = 0;
		if (StringUtil.isNotEmpty(roleId)) {
			saveNums = roleService.roleUpdate(role);
		} else {
			saveNums = roleService.roleAdd(role);
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
	@RequestMapping("/roleDelete.do")
	public String roleDelete(HttpServletRequest request){
		String delIds = request.getParameter("delIds");
		JSONObject result = new JSONObject();
		String str[] = delIds.split(",");
		for (int i = 0; i < str.length; i++) {
			boolean f = userService.existUserWithRoleId(str[i]);
			if (!f) {
				result.put("errorIndex", i);
				result.put("errorMsg", "角色下面有用户，不能删除！");
				return result.toString();
			}
		}
		int delNums = roleService.roleDelete(delIds);

		if (delNums > 0) {
			result.put("success", true);
			result.put("delNums", delNums);
		} else {
			result.put("errorMsg", "删除失败");
		}
		return result.toString();
	}

	
	@ResponseBody
	@RequestMapping("/roleAuth.do")
	public String roleAuth(HttpServletRequest request){
		String roleId=request.getParameter("roleId");
		String authIds=request.getParameter("authIds");
		Role role = new Role();
		role.setRoleId(Integer.parseInt(roleId));
		role.setAuthIds(authIds);
		JSONObject result = new JSONObject();
		int updateNums = roleService.roleAuthIdsUpdate(role);
		if (updateNums > 0) {
			result.put("success", true);
		} else {
			result.put("errorMsg", "授权失败");
		}
		return result.toString();
	}
	
}
