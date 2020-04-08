 package com.zhddk.Springv1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.zhddk.Springv1.entity.schinfo;

@Mapper
public interface SchinfoMapper {
	@Select("SELECT * FROM schinfo  WHERE schpro like #{schpro} and schtypename like #{schtypename} and schlevelname like #{schlevelname} ")
	public List<schinfo> searchschinfoByjson(@Param("schpro")String schpro,@Param("schtypename")String schtypename,@Param("schlevelname")String schlevelname);

}
