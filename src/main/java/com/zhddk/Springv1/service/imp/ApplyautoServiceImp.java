package com.zhddk.Springv1.service.imp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.zhddk.Springv1.dao.ApplyautoMapper;
import com.zhddk.Springv1.dao.ApplyschMapper;
import com.zhddk.Springv1.entity.Applyreal;
import com.zhddk.Springv1.entity.Applysch;
import com.zhddk.Springv1.entity.Userxkinfo;
import com.zhddk.Springv1.service.ApplyautoService;

@Component
public class ApplyautoServiceImp implements ApplyautoService {
	
	@Autowired
	private ApplyautoMapper applyautoMapper;
	@Autowired
	private ApplyschMapper applyschMapper;
	@Autowired
    private RedisTemplate redisTemplate;
	
	
	@Override
	public List<Applysch> applyauto(String existing, String username) {
		
		//从缓存中获取筛选条件
        String key = existing + username;
        ValueOperations<String, List<Applysch>> operations = redisTemplate.opsForValue();
		
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	List<Applysch> applyautocc = operations.get(key);
            return applyautocc;
        }
        
        
        
		int num=Integer.parseInt(existing);
		int cc = num/3;//冲刺
		int px = num/3;//平行
		int bd = num/3;//保底
		bd+=num-cc-px-bd;
		
		//获取用户分数
		Userxkinfo userscore = applyschMapper.userscore(username);
		
		List<Applysch> applyautocc = applyautoMapper.ccapplyautoMapper(userscore.getUesrscore(),userscore.getUserrank(),cc);
		List<Applysch> applyautopx = applyautoMapper.pxapplyautoMapper(userscore.getUesrscore(),userscore.getUserrank(),px);
		List<Applysch> applyautobd = applyautoMapper.bdapplyautoMapper(userscore.getUesrscore(),userscore.getUserrank(),bd);
		applyautocc.addAll(applyautopx);
		applyautocc.addAll(applyautobd);
		
		// 插入缓存
		operations.set(key, applyautocc, 10, TimeUnit.SECONDS);
		
		return applyautocc;
	}

}
