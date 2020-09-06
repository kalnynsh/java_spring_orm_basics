package edu.learn.spring.dak.repositories.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.learn.spring.dak.models.jdbc.Course;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryJdbcImpl implements CourseRepositoryJdbc {

	@Autowired
	private final JdbcOperations opr;
	
	@Override
	public List<Course> findAllUsed() {
		return opr.query("select c.id, c.name " 
				+"from courses c inner join student_courses sc on c.id sc.course_id "
				+ "group by c.id, c.name", new CourseRowMapper());
	}
	
	private class CourseRowMapper implements RowMapper<Course> { 
		@Override
		public Course mapRow(ResultSet rst, int rowNum) throws SQLException {
			return new Course(rst.getLong(1), rst.getString(2));
		}
	}

}
