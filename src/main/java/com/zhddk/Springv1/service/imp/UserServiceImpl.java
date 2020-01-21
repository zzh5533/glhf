package com.zhddk.Springv1.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhddk.Springv1.dao.UserBasicInfoMapper;
import com.zhddk.Springv1.entity.UserBasicInfo;
import com.zhddk.Springv1.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserBasicInfoMapper userBasicInfoMapper;
	
	
	@Override
	public boolean addUser(UserBasicInfo userbasicinfo) {
		int rows = userBasicInfoMapper.addUser(userbasicinfo);
		return rows>0?true:false;
	}


	@Override
	public boolean searchUserByusername(UserBasicInfo userbasicinfo) {
		UserBasicInfo rows = userBasicInfoMapper.searchUserByusername(userbasicinfo.getUsername());
		if(rows!=null) {
			if(rows.getUserpwd().equals(userbasicinfo.getUserpwd())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
}
