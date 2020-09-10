package edu.learn.spring.dak.repositories.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.learn.spring.dak.models.jpa.common.Course;

public class CourseRepositoryJpaImpl implements CourseRepositoryJpa {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Course save(Course course) {
		em.persist(course);
		
		return course;
	}

}
