package com.java.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.PageBean;
import com.java.entity.Reward;
import com.java.entity.User;
import com.java.service.RewardService;
import com.java.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller  
@RequestMapping("/reward") 
public class RewardController {

	
	@Resource
	private RewardService rewardService;
	
	@ResponseBody 
	@RequestMapping("/rewardList.do")
	public String getRewardList(HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String studentNo = request.getParameter("s_studentNo");
		PageBean pageBean = null;
		if (page!=null&&rows!=null) {			
			pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		}
		
		Reward reward = null;
	
		if(StringUtil.isNotEmpty(studentNo)){
			reward = new Reward();
			reward.setStudentNo(studentNo);
		}
		JSONArray jsonArray = rewardService.getRewardList(pageBean,reward);
		int total = rewardService.getRewardListCount();
		JSONObject result=new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", total);
		return result.toString();	
	}
	
	@ResponseBody 
	@RequestMapping("/save.do")
	public String save(HttpServletRequest request){
		String id = request.getParameter("id");
		String rewardInfo = request.getParameter("rewardInfo");
		int code=rewardService.rewardUpdate(Integer.parseInt(id), rewardInfo);
		System.out.println(code);
		JSONObject result=new JSONObject();
		if(code>0){
			result.put("success", true);
		}else{
			result.put("success", true);
			result.put("errorMsg", "保存失败");
		}
		return result.toString();
	}
}
