package com.zhddk.Springv1.service.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhddk.Springv1.dao.InitdataMapper;
import com.zhddk.Springv1.service.DataService;

@Component
public class InitdataServiceImp implements DataService {

	@Autowired
	private InitdataMapper initdataMapper;
	
	@Override
	public int initdata() {
		int result=0;
		int count = initdataMapper.countdata();
		for(int i=1;i<=count;i++) {//count
			int n =0;
			Map<String,String> getschzzname = initdataMapper.searchdatabyid(i);
			
			
			double[] scoreline = {0,0,0};
			double[] rank = {0,0,0};
			
			Map<String,String> scorank17 = new HashMap<String, String>();
			Map<String,String> scorank18 = new HashMap<String, String>();
			Map<String,String> scorank19 = new HashMap<String, String>();
			
			//获取数据
			scorank17 = initdataMapper.search17data(getschzzname.get("schname"), getschzzname.get("zyname"));
			if(scorank17==null) {
				n++;
			}else {
				scoreline[0] = Double.valueOf(scorank17.get("scoreline"));
				rank[0] = Double.valueOf(scorank17.get("rank"));
			}
			
			scorank18 = initdataMapper.search18data(getschzzname.get("schname"), getschzzname.get("zyname"));
			if(scorank18==null) {
				n++;
			}else {
				scoreline[1] = Double.valueOf(scorank18.get("scoreline"));
				rank[1] = Double.valueOf(scorank18.get("rank"));
			}
			
			scorank19 = initdataMapper.search19data(getschzzname.get("schname"), getschzzname.get("zyname"));
			if(scorank19==null) {
				n++;
			}else {
				scoreline[2] = Double.valueOf(scorank19.get("scoreline"));
				rank[2] = Double.valueOf(scorank19.get("rank"));
			}
			
			
			String scorelinemin,scorelinemax,rankmin,rankmax;
			
			if(n==2) {
				
				scorelinemin = Integer.toString((int)(scoreline[2]*0.99));
				scorelinemax = Integer.toString((int)(scoreline[2]*1.01));
				rankmin = Integer.toString((int)(rank[2]*0.99));
				rankmax = Integer.toString((int)(rank[2]*1.01));
				result += initdataMapper.initdata(scorelinemin,scorelinemax,rankmin,rankmax,i);
			
			}else if(n==1){
				scorelinemin = Integer.toString((int)((scoreline[0]>scoreline[1])?(scoreline[0]>scoreline[2])?scoreline[2]:scoreline[0]:(scoreline[1]>scoreline[2])?scoreline[2]:scoreline[1]));
				scorelinemax = Integer.toString((int)((scoreline[0]>scoreline[1])?(scoreline[0]<scoreline[2])?scoreline[2]:scoreline[0]:(scoreline[1]<scoreline[2])?scoreline[2]:scoreline[1]));
				rankmin = Integer.toString((int)((rank[0]>rank[1])?(rank[0]>rank[2])?rank[2]:rank[0]:(rank[1]>rank[2])?rank[2]:rank[1]));
				rankmax = Integer.toString((int)((rank[0]>rank[1])?(rank[0]<rank[2])?rank[2]:rank[0]:(rank[1]<rank[2])?rank[2]:rank[1]));
				result += initdataMapper.initdata(scorelinemin,scorelinemax,rankmin,rankmax,i);
				
			}else if(n==0) {
				double[] resulta = new double[2];
				resulta=Greypre.greyMarkov(scoreline);
				scorelinemin = Integer.toString((int)resulta[0]);
				scorelinemax = Integer.toString((int)resulta[1]);
				resulta=Greypre.greyMarkov(rank);
				rankmin = Integer.toString((int)resulta[0]);
				rankmax = Integer.toString((int)resulta[1]);
				
				result += initdataMapper.initdata(scorelinemin,scorelinemax,rankmin,rankmax,i);
			}
			
			
		
		}
		
		
		
		return result;
	}

}
