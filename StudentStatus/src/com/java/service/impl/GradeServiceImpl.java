package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.java.dao.GradeDao;
import com.java.entity.Grade;
import com.java.service.GradeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service("gradeService")
public class GradeServiceImpl implements GradeService{

	
	@Resource
	private GradeDao gradeDao;
	
	@Override
	public JSONArray getGradeList(String studentNo) {
		// TODO Auto-generated method stub
		List<Grade> list=gradeDao.getGradeList(studentNo);
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			JSONObject jsonObject = new JSONObject();
			Grade grade = list.get(i);
			jsonObject.put("id", grade.getSubject().getId());
			jsonObject.put("studentName", grade.getStudent().getStudentName());
			jsonObject.put("studentNo", grade.getStudent().getStudentNo());
			jsonObject.put("subjectName", grade.getSubject().getSubjectName());
			jsonObject.put("score", grade.getScore());
			jsonArray.add(jsonObject);
		}		
		return jsonArray;
	}
	
	
	

	@Override
	public Integer gradeUpdate(Grade grade) {
		// TODO Auto-generated method stub
		return gradeDao.updateGrade(grade);
	}

	public boolean exitGrade(String studentNo){
		Integer code = gradeDao.exitGrade(studentNo);
		if(code>0){
			return true;
		}else{
			return false;
		}
	}

	public Integer addGrade(Grade grade){
		return gradeDao.addGrade(grade);
	}

	@Override
	public Integer gradeDelete(String studentNo) {
		// TODO Auto-generated method stub
		return gradeDao.deleteGrade(studentNo);
	}




	@Override
	public List<Grade> getGradeListByScore(String studentNo) {
		// TODO Auto-generated method stub
		return gradeDao.getGradeState(studentNo);
	}
}
