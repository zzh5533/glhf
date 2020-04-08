package com.zhddk.Springv1.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhddk.Springv1.entity.Applyreal;
import com.zhddk.Springv1.entity.Applysch;
import com.zhddk.Springv1.service.ApplyautoService;
import com.zhddk.Springv1.service.ApplyschService;

@Controller
public class ApplyschController {
	
	@Autowired
	private ApplyschService applyschService;
	
	@Autowired
	private ApplyautoService applyautoService;
	
	
	//志愿填报主界面
	@RequestMapping("/applysch.html")
	public String applysch() {
		return "applysch.html";
	}
	
	@ResponseBody
	@PostMapping("/applysch.html/action")
	public List<Applyreal> applyschaction(@RequestParam(value="schpro") String[] schpro,String schlevelname,@RequestParam(value="zy")String[] zy,int pageno,@RequestParam(value="username")String username ){
		/*for(int i=0;i<zy.length;i++) {
			zy[i]=zy[i]+"%";
			System.out.println(zy[i]);
		}
			System.out.println(schlevelname);
		*/
		//System.out.println(username);
		List<Applyreal> applyschmap =  applyschService.searchapplysch(schpro,schlevelname+"%",zy,(pageno-1)*20,username);
		return applyschmap;
	}
	
	@ResponseBody
	@PostMapping("/applysch.html/actionAI")
	public List<Applysch> applyschAI(@RequestParam(value="existing")String existing,@RequestParam(value="username")String username){
		
		System.out.println(existing);
		System.out.println(username);
		
		//System.out.println(username);applyschService.searchapplysch(existing,username);
		List<Applysch> applyauto = applyautoService.applyauto(existing,username);
		return applyauto;
	}
}
	
	
	

