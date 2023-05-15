package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.RewardDao;
import com.java.entity.PageBean;
import com.java.entity.Reward;
import com.java.service.RewardService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("rewardService")
public class RewardServiceImpl implements RewardService{

	@Resource
	private RewardDao rewardDao;
	
	@Override
	public JSONArray getRewardList(PageBean pageBean,Reward reward) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		List<Reward> rewardList=rewardDao.getRewardList(pageBean,reward);
		for(int i =0;i<rewardList.size();i++){
			JSONObject jsonObject = new JSONObject();
			Reward r = new Reward();
			r=rewardList.get(i);
			jsonObject.put("id", r.getId());
			jsonObject.put("studentName", r.getStudent().getStudentName());
			jsonObject.put("deptName", r.getDept().getDeptName());
			jsonObject.put("rewardInfo", r.getRewardInfo());
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	@Override
	public Integer rewardAdd(Reward reward) {
		// TODO Auto-generated method stub
		return rewardDao.rewardAdd(reward);
	}

	@Override
	public Integer rewardDelete(String studentNo) {
		// TODO Auto-generated method stub
		return rewardDao.rewardDelete(studentNo);
	}

	public int getRewardListCount(){
		return rewardDao.getRewardListCount();
	}
	
	public Integer rewardUpdate(int id,String rewardInfo){
		return rewardDao.rewardUpdate(id, rewardInfo);
	}
	
}
