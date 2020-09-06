package edu.learn.spring.dak.models.jdbc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Avatar {

	private long id;
	private String photoUrl;
	
}
