package edu.learn.spring.dak.repositories.jdbc;

import java.util.List;

import edu.learn.spring.dak.models.jdbc.Course;

interface CourseRepositoryJdbc {

	List<Course> findAllUsed();

}
