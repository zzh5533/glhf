package com.zhddk.Springv1.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InitdataMapper {
		
		
		@Select("SELECT COUNT(*) FROM zj20predict")
		public int countdata();
		
		@Select("SELECT schname,zyname FROM zj20predict WHERE id=#{id}")
		public Map<String,String> searchdatabyid(int id);
		
		@Select("SELECT scoreline,rank FROM zj17 WHERE schname=#{schname} and zyname=#{zyname}")
		public Map<String,String> search17data(@Param("schname")String schname,@Param("zyname")String zyname);
		@Select("SELECT scoreline,rank FROM zj18 WHERE schname=#{schname} and zyname=#{zyname}")
		public Map<String,String> search18data(@Param("schname")String schname,@Param("zyname")String zyname);
		@Select("SELECT scoreline,rank FROM zj19 WHERE schname=#{schname} and zyname=#{zyname}")
		public Map<String,String> search19data(@Param("schname")String schname,@Param("zyname")String zyname);
		
		//添加预测值
		@Update("update zj20predict set scorelinemin=#{scorelinemin},scorelinemax=#{scorelinemax},rankmin=#{rankmin},rankmax=#{rankmax} where id=#{id}")
		public int initdata(@Param("scorelinemin")String scorelinemin,@Param("scorelinemax")String scorelinemax,@Param("rankmin")String rankmin,@Param("rankmax")String rankmax,@Param("id")int id);
		
}
