package org.java.app.pojo;

import java.util.Arrays;
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
	
	public Category() {
		
	}
	
	public Category(String name) {
		setName(name);
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	public boolean hasPhoto(Photo photo) {
		if(getPhotos() == null) return false; 
		for (Photo p : getPhotos())
			if (p.getId() == photo.getId())
				return true; 
		return false;
	}
	
	public void addPhotos (Photo ...photos) {
		getPhotos().addAll(Arrays.asList(photos));
	}
	public void removePhotos(Photo ...photos) {
		getPhotos().removeAll(Arrays.asList(photos));
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "]" + getName();
	}
}
