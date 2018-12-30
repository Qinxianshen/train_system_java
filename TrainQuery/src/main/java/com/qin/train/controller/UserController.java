package com.qin.train.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qin.train.pojo.User;
import com.qin.train.service.UserService;

@Controller
@RequestMapping("/index")
public class UserController {
	@Resource
    private UserService userService;
	@RequestMapping("/Login")
    public ModelAndView login(User user) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        //µ÷ÓÃService
        User existUser = userService.checkLogin(user);
        System.out.println(existUser);
        if(existUser != null){
            List<User> userList =new ArrayList<User>();
            userList.add(existUser);
            modelAndView.addObject("existUser",userList);
            System.out.println(userList.get(0).getUserName() + userList.get(0).getPassword());
            if (existUser.getAdmin() == 0){
            	modelAndView.addObject("username", userList.get(0).getUserName());
                modelAndView.setViewName("success");
            } else {
                modelAndView.setViewName("redirect:/admin/showStation");
            }
        } else {
            modelAndView.setViewName("no");
        }
        return modelAndView;
	}
	
}
