package com.java.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.entity.Grade;
import com.java.entity.Student;
import com.java.service.GradeService;
import com.java.service.StudentService;
import com.java.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/grade")
public class GradeController {
	@Resource
	private GradeService gradeService;
	
	@Resource
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping("/getGrade.do")
	public String getGrade(HttpServletRequest request){
		String studentNo = request.getParameter("studentNo");
		if(StringUtil.isNotEmpty(studentNo)){
			JSONArray json=gradeService.getGradeList(studentNo);
			return json.toString();
		}else{
			return null;
		}
	}
	

	@ResponseBody
	@RequestMapping("/save.do")
	public String save(HttpServletRequest request){
		String studentNo = request.getParameter("studentNo");
		String score = request.getParameter("score");
		String id = request.getParameter("id");
		
		String math = request.getParameter("math");
		String english = request.getParameter("english");
		String sport = request.getParameter("sport");
		String political = request.getParameter("political");
		
		JSONObject result = new JSONObject();
		
		if(StringUtil.isNotEmpty(id)){
			Grade g = new Grade();
			g.setSubjectId(Integer.parseInt(id));
			g.setScore(Integer.parseInt(score));
			g.setStudentNo(studentNo);
			gradeService.gradeUpdate(g);
			
			List<Grade> gradeList=gradeService.getGradeListByScore(studentNo);
			boolean isTrue = true;
			for(Grade gr:gradeList){
				if(gr.getScore()<60){
					isTrue = false;
				}
			}
			Student s = new Student();
			if(isTrue){
				s.setStudentNo(studentNo);
				s.setState("2");
				studentService.studentUpdateState(s);
			}else{
				s.setStudentNo(studentNo);
				s.setState("1");
				studentService.studentUpdateState(s);
			}
			
			result.put("success", true);
			return result.toString();
		}else{
			if(!studentService.existStudentWithStudentNo(studentNo)){
				result.put("success", true);
				result.put("errorMsg", "无此学生，添加成绩失败");
				return result.toString();
			}else{
				if(gradeService.exitGrade(studentNo)){
					result.put("success", true);
					result.put("errorMsg", "此学生已有成绩，无法添加，请选择修改");
					return result.toString();
				}else{
					if(StringUtil.isNotEmpty(math)){
						Grade g = new Grade();
						g.setSubjectId(1);
						g.setStudentNo(studentNo);
						g.setScore(Integer.parseInt(math));
						gradeService.addGrade(g);
					}
					if(StringUtil.isNotEmpty(english)){
						Grade g = new Grade();
						g.setSubjectId(2);
						g.setStudentNo(studentNo);
						g.setScore(Integer.parseInt(english));
						gradeService.addGrade(g);
					}
					if(StringUtil.isNotEmpty(sport)){
						Grade g = new Grade();
						g.setSubjectId(3);
						g.setStudentNo(studentNo);
						g.setScore(Integer.parseInt(sport));
						gradeService.addGrade(g);
					}
					if(StringUtil.isNotEmpty(political)){
						Grade g = new Grade();
						g.setSubjectId(4);
						g.setStudentNo(studentNo);
						g.setScore(Integer.parseInt(political));
						gradeService.addGrade(g);
					}
					int mathScore = Integer.parseInt(math);
					int englishScore = Integer.parseInt(english);
					int sportScore = Integer.parseInt(sport);
					int politicalScore = Integer.parseInt(political);
					Student s = new Student();
					if(mathScore<60||englishScore<60||sportScore<60||politicalScore<60){
						s.setStudentNo(studentNo);
						s.setState("1");
						studentService.studentUpdateState(s);
					}else{
						s.setStudentNo(studentNo);
						s.setState("2");
						studentService.studentUpdateState(s);
					}
					result.put("success", true);
					return result.toString();
				}
				
			}
		}
	}
	
	@RequestMapping("/update.do")
	public String gradeUpdate(HttpServletRequest request){
		String id = request.getParameter("id");
		String studentNo = request.getParameter("studentNo");
		String score = request.getParameter("score");
		
		return null;
	}
}
