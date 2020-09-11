package edu.learn.spring.dak.repositories.jdbc;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Student`s repository based on Jdbc")
@JdbcTest
@Import({StudentRepositoryJdbcImpl.class, CourseRepositoryJdbcImpl.class})
public class StudentRepositoryJdbcImplTest {

	@Autowired
	private StudentRepositoryJdbcImpl repositoryJdbc;

	@DisplayName("should load list of all students with all info")
	@Test
	void shouldReturnCorrectStudentsListWithAllInfo() {
		val students = repositoryJdbc.findAllWithAllInfo();
		// final List<Student> students = repositoryJdbc.findAllWithAllInfo();

		assertThat(students)
				.isNotNull()
				.hasSize(10)
				.allMatch(s -> !s.getName().equals(""))
				.allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
				.allMatch(s -> s.getAvatar() != null)
				.allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
	}

}
