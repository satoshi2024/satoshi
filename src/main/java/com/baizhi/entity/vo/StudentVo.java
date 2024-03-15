package com.baizhi.entity.vo;

import java.util.List;

import com.baizhi.entity.original.Student;

import lombok.Data;

@Data
public class StudentVo {
	
	private Student obj;
	private List<String> selectedItems;
	private List<Student> lst;
	public StudentVo() {
	}

}
