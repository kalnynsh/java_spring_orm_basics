package edu.learn.spring.dak.repositories.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import edu.learn.spring.dak.models.jdbc.Avatar;
import edu.learn.spring.dak.models.jdbc.Student;

@Mapper
public interface StudentRepositoryMyBatis {

	@Select("select * from students")
	
	@Results(id = "studentAllMap", value = {
			@Result(property = "id", column = "id"),
			@Result(property = "name", column = "name"),
			
			@Result(property = "avatar", column = "avatar_id", javaType = Avatar.class,
				one = @One(select = "edu.learn.spring.dak.repositories.mybatis.AvatarRepositoryMyBatis.getAvatarById", 
							fetchType = FetchType.EAGER)),		
			
			@Result(property = "emails", column = "id", javaType = List.class,
				many = @Many(select = "edu.learn.spring.dak.repositories.mybatis.EmailRepositoryMyBatis.getEmailsByStudentId", 
							fetchType = FetchType.EAGER)),
			
			@Result(property = "courses", column = "id", javaType = List.class,
			many = @Many(select = "edu.learn.spring.dak.repositories.mybatis.CourseRepositoryMyBatis.getCoursesByStudentId", 
						fetchType = FetchType.EAGER))
	})
	
	List<Student> findAllWithAllInfo();
	
	@Select("select * from students where id = #{id}")
	@ResultMap("studentAllMap")
	Student findById(long id);
	
	@Select("select count(*) as students_count from students")
	long getStudentsCount();
	
	@Insert("insert into students(name, avatar_id) values (#{name}, #{avatar.id})")
	void insert(Student student);
	
	@Update("update students set name = #{name} where id = #{id}")
	void updateName(Student student);
	
	@Delete("delete from students where id = #{id}")
	void deleteById(long id);
	
}
