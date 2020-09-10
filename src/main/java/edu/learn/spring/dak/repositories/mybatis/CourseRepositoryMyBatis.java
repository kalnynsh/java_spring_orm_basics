package edu.learn.spring.dak.repositories.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import edu.learn.spring.dak.models.jdbc.Course;

@Mapper
public interface CourseRepositoryMyBatis {
	
	@Select("select * from student_courses sc left join courses c on sc.course_id = c.id where sc.student_id = #{studentId}")
	
	List<Course> getCoursesByStudentId(long studentId);
	
}
