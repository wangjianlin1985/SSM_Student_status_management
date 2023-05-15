package com.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java.dao.StudentDao;
import com.java.entity.PageBean;
import com.java.entity.Student;
import com.java.service.StudentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("StudentService")
public class StudentServiceImpl implements StudentService{

	
	@Resource
    private StudentDao studentDao;
	
	@Override
	public JSONArray getStudentList(Student student, PageBean pageBean) {
		// TODO Auto-generated method stub
		if(student==null||pageBean==null){
			System.out.println("StudentServiceImpl输出为NULL");
		}
		JSONArray jsonArray = new JSONArray();
		List<Student> list = studentDao.getStudentList(student, pageBean);
		for(int i =0;i<list.size();i++){
			JSONObject jsonObject = new JSONObject();
			Student s = new Student();
			s=list.get(i);
			jsonObject.put("id", s.getId());
			jsonObject.put("studentNo", s.getStudentNo());
			jsonObject.put("studentName", s.getStudentName());
			jsonObject.put("sex", s.getSex());
			jsonObject.put("deptName", s.getDept().getDeptName());
			jsonObject.put("deptNo", s.getDeptNo());
			jsonObject.put("IdCard", s.getIdCard());
			jsonObject.put("address", s.getAddress());
			jsonObject.put("postcode", s.getPostcode());
			jsonArray.add(jsonObject);			
			
		}
		System.out.println("studentList:"+jsonArray);
		return jsonArray;
	}

	
	
	@Override
	public int getStudentCount(Student student) {
		// TODO Auto-generated method stub
		return studentDao.getStudentCount(student);
	}

	@Override
	public Integer studentAdd(Student student) {
		// TODO Auto-generated method stub
		System.out.println("aaaa");
		return studentDao.studentAdd(student);
	}

	@Override
	public Integer studentUpdate(Student student) {
		// TODO Auto-generated method stub
		return studentDao.studentUpdate(student);
	}

	@Override
	public boolean existStudentWithStudentNo(String studentNo) {
		// TODO Auto-generated method stub
//		return studentDao.;
		Integer code=studentDao.existStudentWithStudentNo(studentNo);
		if(code != null){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Integer studentDelete(String stuNo) {
		// TODO Auto-generated method stub
		return studentDao.studentDelete(stuNo);
	}
	
	public Integer studentUpdateState(Student student){
		
		System.out.println(student.getState()+student.getStudentNo());
		return studentDao.studentUpdateState(student);
		
	}



	@Override
	public JSONArray getStudentListByState(String state, PageBean pageBean) {
		// TODO Auto-generated method stub
		JSONArray jsonArray = new JSONArray();
		List<Student> list = studentDao.getStudentListByState(state, pageBean);
		for(int i =0;i<list.size();i++){
			JSONObject jsonObject = new JSONObject();
			Student s = new Student();
			s=list.get(i);
			jsonObject.put("id", s.getId());
			jsonObject.put("studentNo", s.getStudentNo());
			jsonObject.put("studentName", s.getStudentName());
			jsonObject.put("sex", s.getSex());
			jsonObject.put("deptName", s.getDept().getDeptName());
			
			jsonArray.add(jsonObject);			
			
		}
		System.out.println("studentList:"+jsonArray);
		return jsonArray;
	}



	@Override
	public int getStudentListByStateCount(String state) {
		// TODO Auto-generated method stub
		return studentDao.getStudentListByStateCount(state);
	}

}
