package edu.learn.spring.dak.repositories.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.learn.spring.dak.models.jdbc.Avatar;
import edu.learn.spring.dak.models.jdbc.Email;
import edu.learn.spring.dak.models.jdbc.Student;

public class StudentResultSetExtractor implements ResultSetExtractor<Map<Long, Student>> {

	public StudentResultSetExtractor() {		
	}

	@Override
	public Map<Long, Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<Long, Student> students = new HashMap<>();
		
		while (rs.next()) {
			long id = rs.getLong("id");
			Student student = students.get(id);
			
			if (student == null) {
				student = new Student(
						id, 
						rs.getString("name"),
						new Avatar(rs.getLong("avatar_id"), rs.getString("photo_url")),
						new ArrayList<>(),
						new ArrayList<>()
				);
			}
			
			student.getEmails().add(new Email(rs.getLong("email_id"),
					                          rs.getString("email")));
		}
		
		return students;
	}

}
