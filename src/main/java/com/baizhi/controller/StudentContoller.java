package com.baizhi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baizhi.constant.MessageConstant;
import com.baizhi.entity.original.Student;
import com.baizhi.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
		return "redirect:/student/lists2";
		
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
		return "redirect:/student/lists2";
		
	}
	//根据id删除学生
	@RequestMapping("delete")
	public String delete(Integer id) {
		studentService.delete(id);
		String deleteCheckitemSuccess = MessageConstant.DELETE_CHECKITEM_SUCCESS;
		log.debug(deleteCheckitemSuccess,id);
		
		
		return "redirect:/student/lists2";
		
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
	//分页查询
	@RequestMapping("lists2")
	public String getList(Model model,@RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
			@RequestParam(defaultValue="5",value="pageSize")Integer pageSize) {
		//为了程序的严谨性，判断非空：
	    if(pageNum == null){
	        pageNum = 1;   //设置默认当前页
	    }
	    if(pageNum <= 0){
	        pageNum = 1;
	    }
	    if(pageSize == null){
	        pageSize = 5;    //设置默认每页显示的数据数
	    }
	    	System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);			    
	    	PageHelper.startPage(pageNum, pageSize);
	    	try {
		    	List<Student> lists = studentService.lists();
		        //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>        
		        PageInfo<Student> pageInfo = new PageInfo<>(lists,pageSize); 
		        //4.使用model/map/modelandview等带回前端
		        model.addAttribute("pageInfo",pageInfo);
		    	 }finally {
		    	        PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
		    	    }
	    	    
		return "emp/stulist2";
		
	}
	
	//搜索功能
	@RequestMapping("lists3")
	public String getSearch(Model model,String string) {
		
		List<Student> search = studentService.getSearch(string);
		String queryStudentSuccess = MessageConstant.QUERY_STUDENT_SUCCESS;
		log.debug(queryStudentSuccess);
		System.out.println(search);
		for (Student string1:search) {
			model.addAttribute("pageInfo",search);
			return "redirect:/student/lists2";
		}
		
		
		
		
		return "redirect:/student/lists2";
		
		
	}
	
}
