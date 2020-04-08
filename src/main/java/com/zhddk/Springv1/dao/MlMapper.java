package com.zhddk.Springv1.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.zhddk.Springv1.entity.Ml;

@Mapper
public interface MlMapper {
	
	
	@Select("SELECT * FROM zyinfo  WHERE zycode like #{mlh}")
	public List<Ml> searchmlBymlh(String mlh);

	
	
}
