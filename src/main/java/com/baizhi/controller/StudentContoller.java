package com.baizhi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baizhi.constant.MessageConstant;
import com.baizhi.entity.original.Student;
import com.baizhi.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("student")
@Slf4j
public class StudentContoller {
	
	private StudentService studentService;
	
	public StudentContoller(StudentService studentService) {
		this.studentService=studentService;
	}
	
	
	//根据id查询学生
	@RequestMapping("selectId")
	public String selectId(Integer id,Model model) {
		
		Student selectId = studentService.selectId(id);
		String queryStudentSuccess = MessageConstant.QUERY_STUDENT_SUCCESS;
		log.debug(queryStudentSuccess);
		model.addAttribute("obj", selectId);
		System.out.println(selectId);
		return "emp/updateStud";
		
	}
	//更改学生信息
	@RequestMapping("update")
	public String update(Student student,Model model) {
		studentService.update(student);
		String editCheckitemSuccess = MessageConstant.EDIT_CHECKITEM_SUCCESS;
		log.debug(editCheckitemSuccess);
		return "redirect:/student/lists";
		
	}
	//添加学生
	@RequestMapping("insert")
	public String insert(Student student,Model model) throws IOException {
		if (student.getStudentName().length()==0) {
			String addStudentFail = MessageConstant.ADD_STUDENT_FAIL;
			log.debug(addStudentFail);
			
			return "emp/addEmp";
		}
		studentService.insert(student);
		String addStudentSuccess = MessageConstant.ADD_STUDENT_SUCCESS;
		log.debug(addStudentSuccess);
		return "redirect:/student/lists";
		
	}
	//根据id删除学生
	@RequestMapping("delete")
	public String delete(Integer id) {
		studentService.delete(id);
		String deleteCheckitemSuccess = MessageConstant.DELETE_CHECKITEM_SUCCESS;
		log.debug(deleteCheckitemSuccess,id);
		
		
		return "redirect:/student/lists";
		
	}
	//查询所有学生
	@RequestMapping("lists")
	public String lists(Model model) {
		String addCheckitemSuccess = MessageConstant.QUERY_STUDENT_SUCCESS;
		log.debug(addCheckitemSuccess);
		List<Student> list = studentService.lists();
		model.addAttribute("objLst",list);
		return "emp/stulist";
		
	}
}
