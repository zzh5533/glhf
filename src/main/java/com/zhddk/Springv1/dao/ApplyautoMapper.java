package com.zhddk.Springv1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.zhddk.Springv1.entity.Applysch;

@Mapper
public interface ApplyautoMapper {
	
	@Select("SELECT schcode,schname,zycode,zyname FROM zj20predict order by abs( #{uesrscore}- scorelinemax )  LIMIT #{nums}")
	@Results(id="ApplyMap", value={
			@Result(column="schcode", property="schcode"),
		    @Result(column="schname", property="schname"),
		    @Result(column="zycode", property="zycode"),
		    @Result(column="zyname", property="zyname")
			})
	public List<Applysch> ccapplyautoMapper(@Param("uesrscore")int uesrscore,@Param("userrank")int userrank,@Param("nums")int nums);
	
	@Select("SELECT schcode,schname,zycode,zyname FROM zj20predict  where #{uesrscore} > scorelinemin order by scorelinemin ASC LIMIT #{nums}")
	@ResultMap("ApplyMap")
	public List<Applysch> pxapplyautoMapper(@Param("uesrscore")int uesrscore,@Param("userrank")int userrank,@Param("nums")int nums);
	
	@Select("SELECT schcode,schname,zycode,zyname FROM zj20predict where #{uesrscore} < scorelinemin order by scorelinemin DESC LIMIT #{nums}")
	@ResultMap("ApplyMap")
	public List<Applysch> bdapplyautoMapper(@Param("uesrscore")int uesrscore,@Param("userrank")int userrank,@Param("nums")int nums);
	
}
