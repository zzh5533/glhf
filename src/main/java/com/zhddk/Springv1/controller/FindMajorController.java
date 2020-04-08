package com.zhddk.Springv1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhddk.Springv1.service.MlService;



@Controller
public class FindMajorController {
	
	@Autowired
	private MlService mlService;
	
	//门类号
	String mlh,xkh,cch;
	
	//查专业主界面
	@RequestMapping("/findMajor.html")
	public String FindMajor() {
		return "findMajor.html";
	}
	
	
	
	//接受层次号和返回html
	@PostMapping("/findMajor.html/ccaction")
	public String FindMajorccaction(@RequestBody String key) {
		cch=key.substring(4,8)+"__";
		return "cc.html";
	}
	//准备返回的html
	@ResponseBody
	@PostMapping("/findMajor.html/ccaction1")
	public Map<String, String> FindMajorccaction1() {
		Map<String, String> ccmap =  mlService.searchmlBymlh(cch);
		return ccmap;
	}
	
	
		
		
	//接受门类号和返回html
	@PostMapping("/findMajor.html/mlaction")
	public String FindMajormlaction(@RequestBody String key) {
		mlh=key.substring(4,10)+"_%";
		return "ml.html";
	}
	//准备返回的html
	@ResponseBody
	@PostMapping("/findMajor.html/mlaction1")
	public Map<String, String> FindMajormlaction1() {
		Map<String, String> mlmap =  mlService.searchmlBymlh(mlh);
		return mlmap;
	}
	
	
	
	//接受学科号和返回html
		@PostMapping("/findMajor.html/xkaction")
		public String FindMajorxkaction(@RequestBody String key) {
			xkh=key.substring(8,12)+"%";
			return "xk.html";
		}
		//准备返回的html
		@ResponseBody
		@PostMapping("/findMajor.html/xkaction1")
		public Map<String, String> FindMajoraction1() {
			Map<String, String> xkmap =  mlService.searchmlBymlh(xkh);
			return xkmap;
		}
	
}
