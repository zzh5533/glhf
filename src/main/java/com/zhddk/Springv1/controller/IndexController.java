package com.zhddk.Springv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	
	//首页
	@RequestMapping("")
	public String mainIndex() {
		return "index.html";
	}
	
	@RequestMapping("/index.html")
	public String index() {
		return "index.html";
	}
}
