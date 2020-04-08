package com.zhddk.Springv1.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VipController {
	
		//vip业务主界面
		@RequestMapping("/vip.html")
		public String vip() {
			return "vip.html";
		}
	
}
