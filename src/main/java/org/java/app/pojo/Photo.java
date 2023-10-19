package org.java.app.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.java.app.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, length = 30, nullable = false)
	@NotBlank(message = "Title cannot be empty")
	@Length (
			max = 30, 
			message = "Title may not be longer than 30 characters"
			)
	private String title;
	
	@Lob 
	@Column(columnDefinition="text")
	@NotBlank(message = "Description cannot be empty")
	@Length(
			max = 500, 
			message = "Description may not be longer than 500 characters"
			)
	private String description;
	
	@Lob 
	@Column(columnDefinition="text")
	@NotBlank(message = "Photo cannot be empty")
	@Length(
			max = 250, 
			message = "Url may not be longer than 250 chatacters "
			)
	private String url;
	
	@Column()
	private boolean visible ;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToMany
	@JsonManagedReference 
	private List<Category> categories; 
	
	public Photo() {
		
	}
	
	public Photo(String title, String description, String url, boolean visible, User user) {
		setTitle(title); 
		setDescription(description); 
		setUrl(url); 
		setVisible(visible);
		setUser(user); 
	}
	
	

	public Photo(PhotoDTO photoDTO) {
		setTitle(photoDTO.getTitle()); 
		setDescription(photoDTO.getDescription()); 
		setUrl(photoDTO.getUrl()); 
		setVisible(photoDTO.getVisible());
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	

	public void setTitle(String title) {
		this.title = title;
		
	}
	
	public void setDescription(String description) {
		this.description = description;
		
	}
	public void setUrl(String url) {
		this.url = url;
		
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public String getTitle() {
		return title; 
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getUrl() {
		return url;
	}
	
	public boolean getVisible() {
		return visible;
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	public boolean hasCategory(Category category) {
		if(getCategories() == null) return false; 
		for(Category c : getCategories())
			if(category.getId() == c.getId())
				return true; 
		return false; 
	}
	
	public void addCategory(Category category) {
		getCategories().add(category); 
	}
	public void removeCategory(Category category) {
		getCategories().remove(category);
	}
	
	public void fillFromPhotoDTO(PhotoDTO photoDTO) {
		setTitle(photoDTO.getTitle()); 
		setDescription(photoDTO.getDescription()); 
		setUrl(photoDTO.getUrl()); 
		setVisible(photoDTO.getVisible());
	}
	
	 @Override
	    public String toString() {
	        return "MiaClasse [title=" + title
	        		+ ", description=" + description + 
	        		", url=" + url + "]";
	    }
	

	

	
	
	
}
