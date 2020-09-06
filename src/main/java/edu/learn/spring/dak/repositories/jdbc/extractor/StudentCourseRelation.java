package edu.learn.spring.dak.repositories.jdbc.extractor;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentCourseRelation {

	private final long studentId;
	private final long courseId;

}
