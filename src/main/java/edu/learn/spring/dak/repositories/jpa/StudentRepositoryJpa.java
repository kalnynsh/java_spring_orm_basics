package edu.learn.spring.dak.repositories.jpa;

import java.util.List;
import java.util.Optional;

import edu.learn.spring.dak.models.jpa.Student;

public interface StudentRepositoryJpa {

	Optional<Student> findById(long id);
	List<Student> findAll();
	
	Student save(Student student);
}
