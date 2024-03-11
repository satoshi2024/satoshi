package com.baizhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.dao.original.StudentMapper;
import com.baizhi.entity.original.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

	private StudentMapper studentMapper;
	
	@Autowired
	public StudentServiceImpl(StudentMapper studentMapper){
		this.studentMapper=studentMapper;
	}
	
	



	@Override
	public List<Student> lists() {
		// TODO 自動生成されたメソッド・スタブ
		return studentMapper.selectByExample(null);
	}





	@Override
	public void delete(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		studentMapper.deleteByPrimaryKey(id);
	}





	@Override
	public void insert(Student student) {
		// TODO 自動生成されたメソッド・スタブ
		studentMapper.insertSelective(student);
	}





	@Override
	public Student selectId(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		
		return studentMapper.selectByPrimaryKey(id);
	}





	@Override
	public void update(Student student) {
		// TODO 自動生成されたメソッド・スタブ
		studentMapper.updateByPrimaryKeySelective(student);
	}






	

}
