package edu.learn.spring.dak.repositories.jpa;

import edu.learn.spring.dak.models.jpa.StudentV2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Student`s repository v2 based on Jpa")
@DataJpaTest
@Import({StudentV2RepositoryJpa.class})
public class StudentV2RepositoryJpaImplTest {

    @Autowired
    private StudentV2RepositoryJpa repositoryJpa;

    @DisplayName("should load by EntityGraph all student list with full info")
    @Test
    void usingEntityGraphShouldReturnCorrectStudentsListWithAllInfo() {
        System.out.println("\n\n\n___________________________________________________________________________________");
        List<StudentV2> students = repositoryJpa.findAllWithEntityGraph();

        assertThat(students)
                .isNotNull()
                .hasSize(10)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);

        System.out.println("\n\n\n___________________________________________________________________________________");
    }

    @DisplayName("should load by `join fetch` all student list with full info")
    @Test
    void usingJoinFetchShouldReturnCorrectStudentsListWithFullInfo() {
        System.out.println("\n\n\n___________________________________________________________________________________");
        List<StudentV2> students = repositoryJpa.findAllWithJoinFetch();

        assertThat(students)
                .isNotNull()
                .hasSize(10)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);

        System.out.println("\n\n\n___________________________________________________________________________________");
    }
}
