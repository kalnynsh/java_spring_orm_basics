package edu.learn.spring.dak.repositories.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import edu.learn.spring.dak.models.jdbc.Course;
import edu.learn.spring.dak.models.jdbc.Student;
import edu.learn.spring.dak.repositories.jdbc.extractor.StudentCourseRelation;
import edu.learn.spring.dak.repositories.jdbc.extractor.StudentResultSetExtractor;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryJdbcImpl implements StudentRepositoryJdbc {

	private final CourseRepositoryJdbc courseRepository;
	private final JdbcOperations op;
	
	@Override
	public List<Student> findAllWithAllInfo() {
		List<Course> courses = courseRepository.findAllUsed();
		List<StudentCourseRelation> relations = getAllRelations();
		
		Map<Long, Student> students = op.query("select s.id, s.name, a.id avatar_id, a.photo_url, e.id email_id, e.email "
				+ "from (studets s left join avatars a on "
				+ "s.avatar_id = a.id) left join emails e on s.id = e.student_id",
				new StudentResultSetExtractor());
		
		mergeStudentsInfo(students, courses, relations);
		
		return new ArrayList<>(Objects.requireNonNull(students).values());
	}

	private List<StudentCourseRelation> getAllRelations() {
		
		return op.query("select student_id, course_id from student_courses sc order by student_id, course_id", 
				(rs, idx) -> new StudentCourseRelation(rs.getLong(1), rs.getLong(2)));
	}
	
	private void mergeStudentsInfo(Map<Long, Student> students, List<Course> courses, 
			                       List<StudentCourseRelation> relations) {
		
		Map<Long, Course> coursesMap = courses.stream().collect(Collectors.toMap(Course::getId, c -> c));
		
		relations.forEach(r -> {
			if (students.containsKey(r.getStudentId()) && coursesMap.containsKey(r.getCourseId())) {
				students.get(r.getStudentId()).getCourses().add(coursesMap.get(r.getCourseId()));
			}
		});		
	}

}
