package edu.learn.spring.dak.models.jdbc;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	private long id;
	private String name;
	private Avatar avatar;
	
	private List<Email> emails;
	private List<Course> courses;
	
}
