package com.qin.train.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/hello")
public class HelloController{

	@RequestMapping("/Login")
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");

      return "hello";
   }
	
	@RequestMapping("/qin")
    public ModelAndView login(HttpServletRequest req,HttpServletResponse resp) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "ÇØ×Ó¾´");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

}