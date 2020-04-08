package com.zhddk.Springv1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhddk.Springv1.entity.schinfo;
import com.zhddk.Springv1.service.SchinfoService;

@Controller
public class SchoolinfoController {
	
	@Autowired
	private SchinfoService schinfoService;
	
	//院校库主界面
	@RequestMapping("/schoolinfo.html")
	public String schoolinfo() {
		return "schoolinfo.html";
	}
	
	@ResponseBody
	@PostMapping("/schoolinfo.html/action")
	public List<schinfo> FindSchinfoaction(@RequestParam String schpro,String schtypename,String schlevelname){
	
		List<schinfo> schinfomap =  schinfoService.searchschinfoByjson(schpro+"%",schtypename+"%",schlevelname+"%");
		
		return schinfomap;
	}
	
}
