package edu.learn.spring.dak.repositories.jpa;

import edu.learn.spring.dak.models.jpa.common.Course;

public interface CourseRepositoryJpa {
	Course save(Course course);
}
