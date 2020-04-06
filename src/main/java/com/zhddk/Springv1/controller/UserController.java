package com.zhddk.Springv1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhddk.Springv1.entity.UserBasicInfo;
import com.zhddk.Springv1.entity.schinfo;
import com.zhddk.Springv1.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login.html")
	public String login() {
		return "login.html";
	}
	
/*	//测试cookie
	@ResponseBody
	@RequestMapping("/loginsuccess")
	public Map<String, String> loginsuccess() {
		Map<String, String> @ResponseBody = new HashMap<String, String>();
		a.put("userName", "zzh");
		a.put("passWord", "123456789");
		return a;
	}*/
	
	@ResponseBody
	@RequestMapping("/login")
	public int login(UserBasicInfo userbasicinfo,Model model) {
		boolean success = userService.searchUserByusername(userbasicinfo);
		if(success) {
			return 0;
		}else {
			//model.addAttribute("error","用户名或密码错误！");
			return 1;
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping("/register")
	public int register(UserBasicInfo userbasicinfo,String reuserpwd,Model model) {

		//检验userpwd和reuserpwd是否一致
		if(userbasicinfo.getUserpwd().equals(reuserpwd)) {
			//一致
			boolean success = userService.addUser(userbasicinfo);
			if(success) {
				//model.addAttribute("error","注册成功！");
				return 0;
			}else {
				//model.addAttribute("error","注册失败！");
				return 1;
			}
			
		}else {
			//model.addAttribute("error","两次输入密码不一致");
			//不一致
			return 1;
		}
		
		
	}
}
