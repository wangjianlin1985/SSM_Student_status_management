package com.java.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.PageBean;
import com.java.service.DeptService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/dept")
public class DeptController {

	@Resource
	private DeptService deptService;
	
	@ResponseBody
	@RequestMapping("/getDeptList.do")
	public String getDeptList(HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageBean pageBean = null;
		if (page!=null&&rows!=null) {			
			pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		}
		JSONArray jsonArray = deptService.getDeptList(pageBean);
		
		return jsonArray.toString();
	}
}
