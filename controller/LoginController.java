package com.bway.springproject.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bway.springproject.HomeController;
import com.bway.springproject.dao.StudentDao;
import com.bway.springproject.dao.UserDao;
import com.bway.springproject.models.User;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserDao udao;
	
	@Autowired
	private StudentDao sdao;
	
	@RequestMapping(value="/userlogin", method=RequestMethod.GET)
	public String getLoginForm(){
		
		  logger.info("inside getLoginForm method");
		
		return "login";
	}
	
	@RequestMapping(value="/userlogin", method=RequestMethod.POST)
	public String loginUser(@ModelAttribute User user, Model model, HttpSession session,HttpServletRequest req) throws IOException{
		
		   user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		  String input = req.getParameter("g-recaptcha-response");
		  boolean resp = VerifyRecaptcha.verify(input);
		  
		  System.out.println("======result==="+resp);
		 
 if(resp){
		
		if(udao.login(user.getUsername(), user.getPassword())){
			
			 logger.info("login success");
			
			session.setAttribute("activeUser", user.getUsername());
			session.setMaxInactiveInterval(5*60);
			
			model.addAttribute("user", user.getUsername());
			model.addAttribute("slist",sdao.getAllStudent());
			
			return "home";
		}else{
			logger.info("login failed");
			model.addAttribute("error","invalid user!!");
			return "login";
		}
		
  }	
		
		logger.info("login failed");
		model.addAttribute("error","you are not human!!");
		return "login";
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session){
		
		logger.info("logout success");
		session.invalidate();
		
		return "login";
	}
	
	

}
