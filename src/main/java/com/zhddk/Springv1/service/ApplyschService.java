package com.zhddk.Springv1.service;

import java.util.List;
import com.zhddk.Springv1.entity.Applyreal;

public interface ApplyschService {
	List<Applyreal> searchapplysch(String[] schpro,String schlevelname,String[] zy,int pageno,String username);
}
