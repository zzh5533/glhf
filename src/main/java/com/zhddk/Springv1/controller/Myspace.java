package com.zhddk.Springv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Myspace {
	
	//志愿填报主界面
		@RequestMapping("/myapply.html")
		public String myspace() {
			return "myapply.html";
		}
	
}
