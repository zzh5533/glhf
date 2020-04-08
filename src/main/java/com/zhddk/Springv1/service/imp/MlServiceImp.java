package com.zhddk.Springv1.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhddk.Springv1.dao.MlMapper;
import com.zhddk.Springv1.entity.Ml;
import com.zhddk.Springv1.service.MlService;

@Component
public class MlServiceImp implements MlService {
	
	@Autowired
	private MlMapper mlMapper;
	
	@Override
	public Map<String, String> searchmlBymlh(String mlh) {
		List<Ml> mllist = mlMapper.searchmlBymlh(mlh);
		Map<String, String> mlmap = new HashMap<String, String>();
		for(Ml e : mllist){
			mlmap.put(e.getzycode(), e.getzyname());
		}
		
		return mlmap;
	}
	
	
	
}
