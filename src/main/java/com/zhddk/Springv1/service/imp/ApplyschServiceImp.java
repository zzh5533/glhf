package com.zhddk.Springv1.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.zhddk.Springv1.dao.ApplyschMapper;
import com.zhddk.Springv1.entity.Applyreal;
import com.zhddk.Springv1.entity.Applysch;
import com.zhddk.Springv1.entity.Userscore;
import com.zhddk.Springv1.entity.Userxkinfo;
import com.zhddk.Springv1.service.ApplyschService;

@Component
public class ApplyschServiceImp implements  ApplyschService{
	@Autowired
	private ApplyschMapper applyschMapper;
	
	 @Autowired
	 private RedisTemplate redisTemplate;

	@Override
	public List<Applyreal> searchapplysch(String[] schpro, String schlevelname, String[] zy ,int pageno,String username) {
		
		//从缓存中获取筛选条件
		String key = new String();
		for(String s :schpro) {
			key +=s;
		}
		for(String s :zy) {
			key +=s;
		}
		key +=schlevelname;
		key +=pageno;
		key +=username;
        ValueOperations<String,List<Applyreal>> operations = redisTemplate.opsForValue();
		
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	List<Applyreal> Applyreallist = operations.get(key);
            return Applyreallist;
        }
        
       
		//获取总条数
		Integer Applyschsum = applyschMapper.searchapplyschsum(schpro,schlevelname,zy);
		
		//把数据装到新的实体类
		List<Applyreal> Applyreallist = new ArrayList<Applyreal>();
		List<Applysch> Applyschlist = applyschMapper.searchapplysch(schpro,schlevelname,zy,pageno);
		for(Applysch e : Applyschlist){
			Applyreal a = new Applyreal();
			a.setSchcode(e.getSchcode());
			a.setSchname(e.getSchname());
			a.setZycode(e.getZycode());
			a.setZyname(e.getZyname());
			a.setForecast(forecast(e.getSchname(),e.getZyname(),username));
			a.setSum(Applyschsum);
			Applyreallist.add(a);
		}
		
		// 插入缓存
		operations.set(key, Applyreallist, 10, TimeUnit.SECONDS);
		
		return Applyreallist;
	}
	
	//分数层次预测
	public String forecast(String schname,String zyname,String username) {
		
		String result = "暂无分数";
		
		//获取预测分数
		Userscore forecast = applyschMapper.forecastbyschname(schname,zyname);
		
		//获取用户分数
		Userxkinfo userscore = applyschMapper.userscore(username);
		
		//分段
		if(userscore == null) {
			return result;
		}else{
			 if(forecast.getRankmax() == 0 || forecast.getRankmin()== 0 ) {
				 if(userscore.getUesrscore()<forecast.getScorelinemin()) {
					 return "冲刺";
				 }else if(userscore.getUesrscore()>forecast.getScorelinemax()) {
					 return "保底";
				 }else {
					 return "平行";
				 }
			 }else if(userscore.getUserrank()<forecast.getRankmin()) {
				 return "保底";
			 }else if(userscore.getUserrank()>forecast.getRankmax()) {
				 return "冲刺";
			 }else {
				 return "平行";
			 }
		}
	}

	
	
	
}
