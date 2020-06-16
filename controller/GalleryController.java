package com.bway.springproject.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GalleryController {

	@RequestMapping(value="/gallery",method=RequestMethod.GET)
	 public String getGallery(Model model){
		 
		List<String>   imglist = Arrays.asList("03.jpg","02.jpg","01.jpg","005.jpg","004.jpg","003.jpg","002.jpg");
		model.addAttribute("imglist",imglist);
		
		 return "gallery";
	 }
	
}
