package org.java.app.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, length = 30, nullable = false)
	@NotBlank(message = "Name cannot be empty")
	@Length (
			max = 30, 
			message = "Name may not be longer than 30 characters"
			)
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	@JsonManagedReference 
	private List<Photo> photos; 
}
