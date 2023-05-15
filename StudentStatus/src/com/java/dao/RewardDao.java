package com.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.java.entity.PageBean;
import com.java.entity.Reward;

public interface RewardDao {
	
	public List<Reward> getRewardList(@Param("pageBean")PageBean pageBean,@Param("reward")Reward reward);
	
	public int getRewardListCount();
	 
	public Integer rewardAdd(@Param("reward")Reward reward);
	
	public Integer rewardDelete(@Param("studentNo")String studentNo);
	
	public Integer rewardUpdate(@Param("id")int id,@Param("rewardInfo")String rewardInfo);
}
