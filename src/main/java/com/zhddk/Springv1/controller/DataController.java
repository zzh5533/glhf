package com.zhddk.Springv1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhddk.Springv1.service.DataService;

@Controller
public class DataController {
	
	/*@Autowired
	private DataService dataService;

	//灰色马尔科夫链预测初始化数据
	@ResponseBody
	@RequestMapping("/applysch.html/data")
	public void applysch() {
		int flag = dataService.initdata();
		System.out.println(flag);
	}
	*/
}
