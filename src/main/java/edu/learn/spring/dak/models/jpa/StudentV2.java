package edu.learn.spring.dak.models.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.learn.spring.dak.models.jpa.common.Avatar;
import edu.learn.spring.dak.models.jpa.common.Course;
import edu.learn.spring.dak.models.jpa.common.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Стратегия генерации идентификаторов
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
