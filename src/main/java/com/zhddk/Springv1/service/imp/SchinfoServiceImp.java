package com.zhddk.Springv1.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.zhddk.Springv1.dao.SchinfoMapper;
import com.zhddk.Springv1.entity.Applysch;
import com.zhddk.Springv1.entity.schinfo;
import com.zhddk.Springv1.service.SchinfoService;

@Component
public class SchinfoServiceImp implements SchinfoService {

	@Autowired
	private SchinfoMapper schinfoMapper;
	@Autowired
    private RedisTemplate redisTemplate;
	
	@Override
	public List<schinfo> searchschinfoByjson(String schpro, String schtypename, String schlevelname) {
		
		//从缓存中获取筛选条件
        String key = schpro + schtypename+schlevelname;
        ValueOperations<String, List<schinfo>> operations = redisTemplate.opsForValue();
		
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
        	List<schinfo> schinfolist = operations.get(key);
            return schinfolist;
        }
        
		List<schinfo> schinfolist = schinfoMapper.searchschinfoByjson(schpro,schtypename,schlevelname);
		
		// 插入缓存
		operations.set(key, schinfolist, 10, TimeUnit.SECONDS);
		
		return schinfolist;
	}

}
