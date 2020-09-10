package edu.learn.spring.dak.repositories.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import edu.learn.spring.dak.models.jpa.Student;

@Repository
public class StudentRepositoryJpaImpl implements StudentRepositoryJpa {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<Student> findById(long id) {
		return Optional.ofNullable(em.find(Student.class, id));
	}

	@Override
	public List<Student> findAll() {
		return em
				.createQuery("select s from Student s", Student.class)
				.getResultList();
	}
	
	@Override
	public Student save(Student student) {
		if (student.getId() <= 0) {
			em.persist(student);
			
			return student;
		}
		
		return em.merge(student);
	}
}
