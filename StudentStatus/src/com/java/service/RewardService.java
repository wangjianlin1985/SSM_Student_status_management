package com.java.service;

import com.java.entity.PageBean;
import com.java.entity.Reward;

import net.sf.json.JSONArray;

public interface RewardService {
	
	public JSONArray getRewardList(PageBean pageBean,Reward reward);
	
	public int getRewardListCount();
	
	public Integer rewardAdd(Reward reward);
					
	public Integer rewardDelete(String studentNo);
	
	public Integer rewardUpdate(int id,String rewardInfo);
}
