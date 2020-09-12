package edu.learn.spring.dak.repositories.jpa;

import edu.learn.spring.dak.models.jpa.Student;
import edu.learn.spring.dak.models.jpa.common.Avatar;
import edu.learn.spring.dak.models.jpa.common.Course;
import edu.learn.spring.dak.models.jpa.common.Email;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Student`s repository based on Jpa")
@DataJpaTest
@Import({StudentRepositoryJpaImpl.class, CourseRepositoryJpaImpl.class})
public class StudentRepositoryJpaImplTest {

    @Autowired
    private StudentRepositoryJpaImpl repositoryJpa;

    @Autowired
    private CourseRepositoryJpaImpl courseRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("should load info about student")
    @Test
    void shouldFindExpectedStudentById() {
        val optionalActualStudent = repositoryJpa.findById(1L);
        val expectedStudent = em.find(Student.class, 1L);

        assertThat(optionalActualStudent)
                .isPresent()
                .get().isEqualToComparingFieldByField(expectedStudent);
    }

    @DisplayName("should load student`s list with all info")
    @Test
    void shouldReturnCorrectStudentsListWithAllInfo() {
        System.out.println("\n\n\n\n" +
                "----------------------------------------------------------------------------------------------------------");

        val students = repositoryJpa.findAll();

        assertThat(students)
                .isNotNull()
                .hasSize(10)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);

        System.out.println(
                "----------------------------------------------------------------------------------------------------------"
                        + "\n\n\n\n");
    }

    @DisplayName("should correctly save all student`s info")
    @Test
    void shouldSaveAllStudentInfo() {
        val avatar = new Avatar(11L, "https://somewhere.org/ljkspavbo/7126587");
        val email = new Email(11L, "theman@example.org");
        val emails = Collections.singletonList(email);

        val course = new Course(11L, "Spring boot");
        val courses = Collections.singletonList(course);
        // courseRepositoryJpa.save(course);

        val john = new Student(11L, "Jhon Crisham", avatar, emails, courses);
        repositoryJpa.save(john);

        assertThat(john.getId()).isGreaterThan(0);

        val actualStudent = em.find(Student.class, john.getId());

        assertThat(actualStudent)
                .isNotNull()
                .matches(s -> !s.getName().equals(""))
                .matches(s -> s.getCourses() != null && s.getCourses().size() > 0 && s.getCourses().get(0).getId() > 0)
                .matches(s -> s.getAvatar() != null)
                .matches(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }
}
