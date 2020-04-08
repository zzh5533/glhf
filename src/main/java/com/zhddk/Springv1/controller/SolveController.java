package com.zhddk.Springv1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SolveController {
	
		//专家人工服务界面主界面
		@RequestMapping("/solve.html")
		public String solve() {
			return "solve.html";
		}
	
}
