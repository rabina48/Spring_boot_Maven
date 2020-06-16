package com.bway.springproject.dao;

import java.util.List;

import com.bway.springproject.models.Student;

public interface StudentDao {
	
	void addStudent(Student student);
	void deleteStudent(int id);
	void updateStudent(Student student);
	Student getById(int id);
	List<Student> getAllStudent();
	
}
