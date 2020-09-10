package edu.learn.spring.dak.repositories.jpa;

import java.util.List;

import edu.learn.spring.dak.models.jpa.StudentV2;

public interface StudentV2RepositoryJpa {
	List<StudentV2> findAllWithEntityGraph();
	List<StudentV2> findAllWithJoinFetch();
}
