package com.baizhi.service;

import java.util.List;

import com.baizhi.entity.original.Student;

public interface StudentService {
		//查询所有学生
		List<Student> lists();
		//删除学生
		void delete(Integer id);
		//增加学生
		void insert(Student student);
		//id查询学生
		Student selectId(Integer id);
		//更新学生
		void update(Student student);
		//分页查询
		//搜索功能
		List<Student> getSearch(String string);
		
		
}
