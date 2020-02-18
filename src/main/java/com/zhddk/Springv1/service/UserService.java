package com.zhddk.Springv1.service;

import com.zhddk.Springv1.entity.UserBasicInfo;

public interface UserService {

	boolean addUser(UserBasicInfo userbasicinfo);
	
	boolean searchUserByusername(UserBasicInfo userbasicinfo);
}
