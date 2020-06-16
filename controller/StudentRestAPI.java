package com.bway.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bway.springproject.dao.StudentDao;
import com.bway.springproject.models.Student;

@RestController
public class StudentRestAPI {
	
	@Autowired
	private StudentDao sdao;
	
	@RequestMapping(value="/api/student/list", method=RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllData(){
		ResponseEntity<List<Student>> sList =new ResponseEntity<List<Student>>(sdao.getAllStudent(),HttpStatus.OK);
		
	return sList;
		
	}

}
