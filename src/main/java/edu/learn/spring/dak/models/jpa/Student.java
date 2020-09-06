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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.learn.spring.dak.models.jpa.common.Avatar;
import edu.learn.spring.dak.models.jpa.common.Course;
import edu.learn.spring.dak.models.jpa.common.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity                    // Указывает, что данный класс является сущностью
@Table(name = "students")  // Задает имя таблицы, на которую будет отображаться сущность
public class Student {

	@Id  // Позволяет указать какое поле является идентификатором
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Стратегия генерации идентификаторов
	private long id;
	
	// Задает имя и некоторые свойства поля таблицы, на которое будет отображаться поле сущности
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	// Указывает на связь между таблицами "один к одному"
	@OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "avatar_id")  // Задает поле, по которому происходит объединение с таблицей для хранения связанной сущности
	private Avatar avatar;
	
	// Указывает на связь между таблицами "один ко многим"
	@OneToMany(targetEntity = Email.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id")
	private List<Email> emails;
	
	// Указывает на связь между таблицами "многие ко многим"
	@ManyToMany(targetEntity = Course.class, fetch = FetchType.LAZY /*, cascade = CascadeType.ALL*/)
	@JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"),
				inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;
}
