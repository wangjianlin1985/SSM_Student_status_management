package com.java.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.PageBean;
import com.java.entity.Reward;
import com.java.entity.Student;
import com.java.service.GradeService;
import com.java.service.RewardService;
import com.java.service.StudentService;
import com.java.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller  
@RequestMapping("/student") 
public class StudentController {

	@Resource
	private StudentService studentService;
	
	@Resource
	private RewardService rewardService;
	
	@Resource
	private GradeService gradeService;
	
	@ResponseBody 
	@RequestMapping("/studentList.do")
	public String studentList(HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Student stu = null;
		String s_stuNo=request.getParameter("s_stuNo");
		if(StringUtil.isNotEmpty(s_stuNo)){
			stu = new Student();
			stu.setStudentNo(s_stuNo);
		}
		PageBean pageBean = null;
		if (page!=null&&rows!=null) {			
			pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		}
		
		JSONArray jsonArray = studentService.getStudentList(stu, pageBean);
		int total = studentService.getStudentCount(stu);
		JSONObject result=new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", total);
		System.out.println("list.do result:"+result);
		return result.toString();	
	}
	
	@ResponseBody
	@RequestMapping("/getStudentListByState.do")
	public String getStudentListByState(HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String state = request.getParameter("state");
		if(StringUtil.isEmpty(state)){
			System.out.println("state is NUll");
			state = "2";
		}
		PageBean pageBean = null;
		if (page!=null&&rows!=null) {			
			pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		}
		JSONArray jsonArray=studentService.getStudentListByState(state, pageBean);
		int total = studentService.getStudentListByStateCount(state);
		JSONObject result=new JSONObject();
		result.put("rows", jsonArray);
		result.put("total", total);
		System.out.println("list.do result:"+result);
		return result.toString();	
	}
	
	
	@ResponseBody 
	@RequestMapping("/save.do")
	public String save(HttpServletRequest request){
		String id = request.getParameter("id");
		String studentName = request.getParameter("studentName");
		String studentNo = request.getParameter("studentNo");
		String sex = request.getParameter("sex");
		String deptNo = request.getParameter("deptNo");
		String deptName = request.getParameter("deptName");
		String IdCard = request.getParameter("IdCard");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		Student stu = new Student();
		
		stu.setStudentName(studentName);
		stu.setStudentNo(studentNo);
		stu.setSex(sex);
		stu.setDeptNo(deptNo);
		stu.setIdCard(IdCard);
		stu.setAddress(address);
		stu.setPostcode(postcode);
		int saveNums = 0;
		JSONObject result=new JSONObject();
		if(StringUtil.isEmpty(id)){
			if(studentService.existStudentWithStudentNo(studentNo)){
				saveNums = -1;
			}else{
				//后期可以使用回滚防止添加一半出现错误
				saveNums=studentService.studentAdd(stu);
				Reward r = new Reward();
				r.setStudentNo(studentNo);
				r.setDeptNo(deptNo);
				rewardService.rewardAdd(r);
			}
		}else{
			stu.setId(id);
			saveNums = studentService.studentUpdate(stu);
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
	@RequestMapping("/delete.do")
	public String delete(HttpServletRequest request){
		String studentNo = request.getParameter("studentNo");
		//使用回滚删除
		Integer code =studentService.studentDelete(studentNo);
		rewardService.rewardDelete(studentNo);
		gradeService.gradeDelete(studentNo);
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
