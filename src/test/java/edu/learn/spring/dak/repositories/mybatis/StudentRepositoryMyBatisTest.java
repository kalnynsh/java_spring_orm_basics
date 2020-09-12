package edu.learn.spring.dak.repositories.mybatis;

import edu.learn.spring.dak.models.jdbc.Avatar;
import edu.learn.spring.dak.models.jdbc.Student;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Student`s repository based on MyBatis")
@SpringBootTest
@Transactional
public class StudentRepositoryMyBatisTest {

    @Autowired
    private StudentRepositoryMyBatis studentRepositoryMyBatis;

    @DisplayName("should load all student list with full info")
    @Test
    void shouldReturnCorrectStudentsListWithFullInfo() {
        val students = studentRepositoryMyBatis.findAllWithAllInfo();

        assertThat(students)
                .isNotNull()
                .hasSize(10)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getCourses() != null && s.getCourses().size() > 0)
                .allMatch(s -> s.getAvatar() != null)
                .allMatch(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }

    @DisplayName("should load students count")
    @Test
    void shouldReturnCorrectStudentCount() {
        long studentsCount = studentRepositoryMyBatis.getStudentsCount();
        assertThat(studentsCount)
                .isEqualTo(10L);
    }

    @DisplayName("should load info about student by ID")
    @Test
    void shouldFindExpectedStudentById() {
        val actualStudent = studentRepositoryMyBatis.findById(1L);

        assertThat(actualStudent).isNotNull();
        assertThat(actualStudent.getName()).isEqualTo("student_01");

        assertThat(actualStudent.getAvatar())
                .isNotNull()
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("photoUrl", "photoUrl_01");

        assertThat(actualStudent.getEmails())
                .isNotNull()
                .hasSize(2);

        assertThat(actualStudent.getCourses())
                .isNotNull()
                .hasSize(3);
    }

    @DisplayName("should save and load info about student")
    @Test
    void shouldSAveAndLoadCorrectStudent() {
        val expectedStudent = new Student();
        expectedStudent.setName("Mark Dukarsky");
        expectedStudent.setAvatar(new Avatar(10L, "https://some-org.org/avatrs/mark-dukarsky"));

        studentRepositoryMyBatis.insert(expectedStudent);

        val actualStudent = studentRepositoryMyBatis.findById(11L);

        assertThat(actualStudent)
                .isNotNull()
                .isEqualToComparingOnlyGivenFields(expectedStudent, "name");
    }

    @DisplayName("should update student`s name in DB")
    @Test
    void shouldUpdateStudentName() {
        val student = studentRepositoryMyBatis.findById(1L);
        student.setName("Georg Washington");
        studentRepositoryMyBatis.updateName(student);

        val actualStudent = studentRepositoryMyBatis.findById(1L);

        assertThat(actualStudent)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", student.getName());
    }

    @DisplayName("should delete student by id from DB")
    @Test
    void shouldDeleteStudentByIdFromDb() {
        val studentsCountBefore = studentRepositoryMyBatis.getStudentsCount();
        studentRepositoryMyBatis.deleteById(10L);
        val studentsCountAfter = studentRepositoryMyBatis.getStudentsCount();

        assertThat(studentsCountBefore - studentsCountAfter)
                .isEqualTo(1);
    }
}













































