package com.zhddk.Springv1.service;

import java.util.List;
import java.util.Map;
import com.zhddk.Springv1.entity.schinfo;

public interface SchinfoService {
	List<schinfo> searchschinfoByjson(String schpro,String schtypename,String schlevelname);
}
