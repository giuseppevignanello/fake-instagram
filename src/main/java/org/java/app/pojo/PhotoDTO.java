package org.java.app.pojo;

public class PhotoDTO {
	
	private int id; 
	private String title; 
	private String description; 
	private String url; 
	private boolean visible; 
	
	public PhotoDTO() {
		
	}
	
	public PhotoDTO(String title, String description, String url, boolean visible) {
		setTitle(title); 
		setDescription(description); 
		setUrl(url); 
		setVisible(visible);
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
		
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDescription(String description) {
		this.description = description;
		
	}

	public void setTitle(String title) {
		this.title = title;
		
	}
	
	public boolean getVisible() {
		return visible; 
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
}
