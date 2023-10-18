package org.java.app.service;


import java.util.List;

import org.java.app.pojo.Category;
import org.java.app.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired 
	private CategoryRepo categoryRepo;
	
	public void save(Category category) {
		categoryRepo.save(category);
	}

	public List<Category> findAll() {
		return categoryRepo.findAll();	}

	public Category findById(int id) {
		return categoryRepo.findById(id).get();
	}

	public void delete(Category category) {
		categoryRepo.delete(category);
		
	}
	
}
