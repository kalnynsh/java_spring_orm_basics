package edu.learn.spring.dak.repositories.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import edu.learn.spring.dak.models.jdbc.Avatar;

@Mapper
public interface AvatarRepositoryMyBatis {
	@Select("select * from avatars where id = #{id}")
	
	@Results(value = {
			@Result(property = "id", column = "id"),
			@Result(property = "photoUrl", column = "photo_url")
	})
	
	Avatar getAvatarById(long id);
}
