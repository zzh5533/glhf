package com.zhddk.Springv1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhddk.Springv1.entity.UserBasicInfo;


@Mapper
public interface UserBasicInfoMapper {
	
	//查所有用户
	@Select("select * from userbasicinfo")
	public List<UserBasicInfo> searchAllUsers();
	
	//查单个用户
	@Select("SELECT * FROM userbasicinfo  WHERE username = #{username}")
	public UserBasicInfo searchUserByusername(String username);
	
	//添加用户
	@Insert("INSERT INTO userbasicinfo values(#{userid},#{username},#{userpwd},#{usersex},#{userphone},#{useremail},#{useradmin})")
	public int addUser(UserBasicInfo userbasicinfo);

/*	@InsertProvider(type = UserBasicInfoProvider.class, method = "addUser" )
	public int addUser(UserBasicInfo userbasicinfo);*/
	
	//删
	@Delete("delete from userbasicinfo where userid = #{userid}")
	int deleteUserById(int userid);	
	
	//改
	@Update("update userbasicinfo set username=#{username},userpwd=#{userpwd},usersex=#{usersex},userphone=#{userphone},useremail=#{useremail},useradmin=#{useradmin} where userid=#{userid}")
	int updateUser(UserBasicInfo userbasicinfo);
	
	
}
