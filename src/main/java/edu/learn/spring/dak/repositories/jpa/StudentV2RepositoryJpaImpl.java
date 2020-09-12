package edu.learn.spring.dak.repositories.jpa;

import edu.learn.spring.dak.models.jpa.StudentV2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StudentV2RepositoryJpaImpl implements StudentV2RepositoryJpa {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<StudentV2> findAllWithEntityGraph() {
		EntityGraph<?> entityGraph = em.getEntityGraph("StudentWithAvatarAndEmails");
		TypedQuery<StudentV2> query = em.createQuery("select s from StudentV2 s", StudentV2.class);
		query.setHint("javax.persistence.fetchgraph", entityGraph);

		return query.getResultList();
	}

	@Override
	public List<StudentV2> findAllWithJoinFetch() {
		return em
				.createQuery("select distinct s from StudentV2 s join fetch s.avatar join fetch s.emails", StudentV2.class)
				.getResultList();
	}
}
