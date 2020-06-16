package com.bway.springproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bway.springproject.dao.StudentDao;
import com.bway.springproject.models.Student;

@Controller
public class StudentController {
	
	@Autowired
	private StudentDao sdao;
	
	@RequestMapping(value="/student", method=RequestMethod.GET)
	public String getStudentForm(Model model, HttpSession session){
		
		 if(StringUtils.isEmpty(session.getAttribute("activeUser"))){
			 
			 return "login";
		 }
		
		model.addAttribute("smodel", new Student());
		return "studentForm";
	}

	@RequestMapping(value="/student", method = RequestMethod.POST)
	public String saveStudentData(@ModelAttribute Student stud,Model model, HttpSession session){
		
		if(StringUtils.isEmpty(session.getAttribute("activeUser"))){
			 
			 return "login";
		 }
		
		
		sdao.addStudent(stud);
		model.addAttribute("slist",sdao.getAllStudent());
		
		return "home";
	}
	
	
	@RequestMapping(value="/{id}/editStudent",method=RequestMethod.GET)
	public String edit(@PathVariable("id") int id, Model model,HttpSession session){
		
		if(StringUtils.isEmpty(session.getAttribute("activeUser"))){
			 
			 return "login";
		 }
		
		model.addAttribute("mstudent",sdao.getById(id));
		
		return "editForm";
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute Student s, Model model,HttpSession session){
		if(StringUtils.isEmpty(session.getAttribute("activeUser"))){
			 
			 return "login";
		 }
		
		sdao.updateStudent(s);
		model.addAttribute("slist",sdao.getAllStudent());
		
		return "home";
	}
	
	@RequestMapping(value="/{id}/deleteStudent", method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id, Model model,HttpSession session){
		
		if(StringUtils.isEmpty(session.getAttribute("activeUser"))){
			 
			 return "login";
		 }
		
		sdao.deleteStudent(id);
		model.addAttribute("slist",sdao.getAllStudent());
		
		return "home";
	}
	
	

}
