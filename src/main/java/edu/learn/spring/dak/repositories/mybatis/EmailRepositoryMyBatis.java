package edu.learn.spring.dak.repositories.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import edu.learn.spring.dak.models.jdbc.Email;

@Mapper
public interface EmailRepositoryMyBatis {
	
	@Select("select * from emails where student_id = #{studentId}")
	
	List<Email> getEmailsByStudentId(long studentId);
}
