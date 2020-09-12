package edu.learn.spring.dak.models.jpa;

import edu.learn.spring.dak.models.jpa.common.Avatar;
import edu.learn.spring.dak.models.jpa.common.Course;
import edu.learn.spring.dak.models.jpa.common.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// import org.hibernate.annotations.Fetch;
// import org.hibernate.annotations.FetchMode;
//import org.hibernate.annotations.BatchSize;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
@NamedEntityGraph(name = "StudentWithAvatarAndEmails", attributeNodes = {@NamedAttributeNode(value = "avatar"), @NamedAttributeNode(value = "emails")})
public class StudentV2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "avatar_id")
	private Avatar avatar;

	@OneToMany(targetEntity = Email.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private List<Email> emails;

	// @BatchSize(size = 5)
	// @Fetch(FetchMode.SUBSELECT)
	@ManyToMany(targetEntity = Course.class, fetch = FetchType.LAZY)
	@JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"),
				inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;

}
