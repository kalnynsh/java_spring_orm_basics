package edu.learn.spring.dak.repositories.jdbc;

import java.util.List;

import edu.learn.spring.dak.models.jdbc.Student;

interface StudentRepositoryJdbc {

	List<Student> findAllWithAllInfo();

}
