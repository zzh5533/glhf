package com.zhddk.Springv1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.zhddk.Springv1.entity.Applysch;
import com.zhddk.Springv1.entity.Userscore;
import com.zhddk.Springv1.entity.Userxkinfo;

@Mapper
public interface ApplyschMapper {
	//获得学校和专业数据
	@Select("<script>"
			+ "SELECT schcode,schname,zycode,zyname FROM zj19 WHERE schname in"
			+ "(SELECT schname FROM schinfo  WHERE "
			+ "<foreach collection='schpro' item='schpros' open='(' close=')' separator='or'>schpro like #{schpros}</foreach> "
			+ "and schlevelname like #{schlevelname}) and ("
			+ "<foreach collection='zy' item='zys' index='index' open='(' close=')' separator='or'>zyname like #{zys} </foreach>)"
			+ "limit #{pageno},20 "
			+ "</script>")
	@Results(id="ApplyMap", value={
			@Result(column="schcode", property="schcode"),
		    @Result(column="schname", property="schname"),
		    @Result(column="zycode", property="zycode"),
		    @Result(column="zyname", property="zyname")
			})
	public List<Applysch> searchapplysch(@Param("schpro")String[] schpro,@Param("schlevelname")String schlevelname,@Param("zy")String[] zy,@Param("pageno")int pageno);

	//获取总条数
	@Select("<script>"
			+ "SELECT COUNT(*) FROM zj19 WHERE schname in"
			+ "(SELECT schname FROM schinfo  WHERE "
			+ "<foreach collection='schpro' item='schpros' open='(' close=')' separator='or'>schpro like #{schpros}</foreach> "
			+ "and schlevelname like #{schlevelname}) and ("
			+ "<foreach collection='zy' item='zys' index='index' open='(' close=')' separator='or'>zyname like #{zys} </foreach>) "
			+ "</script>")
	public int searchapplyschsum(@Param("schpro")String[] schpro,@Param("schlevelname")String schlevelname,@Param("zy")String[] zy);
	
	//获取预测分数
	@Select("SELECT scorelinemin,scorelinemax,rankmin,rankmax FROM zj20predict WHERE schname = #{schname} and zyname = #{zyname}")
	@Results(id="forecastMap", value={
			@Result(column="scorelinemin", property="scorelinemin"),
		    @Result(column="scorelinemax", property="scorelinemax"),
		    @Result(column="rankmin", property="rankmin"),
		    @Result(column="rankmax", property="rankmax")
			})
	public Userscore forecastbyschname(@Param("schname")String schname,@Param("zyname")String zyname);
	
	
	//获取用户分数
	@Select("SELECT uesrscore,userrank FROM userxkinfo WHERE userid = (SELECT userid FROM userbasicinfo WHERE username like #{username})")
	@Results(id="userscore", value={
			@Result(column="uesrscore", property="uesrscore"),
		    @Result(column="userrank", property="userrank")
			})
	public Userxkinfo userscore(@Param("username")String username);
	
	/*@Select("SELECT userid FROM userbasicinfo WHERE username like #{username}")
	public int searchid(@Param("username")String username);*/
	
}
