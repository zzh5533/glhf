package com.zhddk.Springv1.service;

import java.util.List;

import com.zhddk.Springv1.entity.Applysch;

public interface ApplyautoService {
	List<Applysch> applyauto(String existing,String username);
}
