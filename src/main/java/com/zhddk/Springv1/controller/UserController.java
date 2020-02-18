package com.zhddk.Springv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhddk.Springv1.entity.UserBasicInfo;
import com.zhddk.Springv1.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.html")
	public String login() {
		return "login.html";
	}
	
	@RequestMapping("/login")
	public String login(UserBasicInfo userbasicinfo,Model model) {
		boolean success = userService.searchUserByusername(userbasicinfo);
		if(success) {
			return "index.html";
		}else {
			model.addAttribute("error","用户名或密码错误！");
			return "error.html";
		}
		
		
	}
	
	
	@RequestMapping("/register")
	public String register(UserBasicInfo userbasicinfo,String reuserpwd,Model model) {

		//检验userpwd和reuserpwd是否一致
		if(userbasicinfo.getUserpwd().equals(reuserpwd)) {
			//一致
			boolean success = userService.addUser(userbasicinfo);
			if(success) {
				model.addAttribute("error","注册成功！");
				return "error.html";
			}else {
				model.addAttribute("error","注册失败！");
				return "error.html";
			}
			
		}else {
			model.addAttribute("error","两次输入密码不一致");
			//不一致
			return "error.html";
		}
		
		
	}
}
