package org.java.app.controller;

import java.util.List;

import org.java.app.pojo.Category;
import org.java.app.pojo.Photo;
import org.java.app.service.CategoryService;
import org.java.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired 
	private PhotoService photoService; 
	
	@GetMapping 
	public String getIndex(Model model) {
		List <Category> categories = categoryService.findAll();
		System.out.println(categories);
		model.addAttribute("categories", categories);
		return "categoryIndex";
	}
	
	@GetMapping("create") 
	public String create(Model model) {
		List <Photo> photos = photoService.findAll(); 
		Category category = new Category(); 
		model.addAttribute("category", category); 
		model.addAttribute("photo", photos);
		return "categoryCreate";
	}
	@PostMapping("create")
	public String store(@Valid @ModelAttribute Category category,
			BindingResult bindingResult,
			Model model) {
		categoryService.save(category);
		List<Photo> photos = photoService.findAll();
		
		for(Photo photo: photos) {
			if(category.hasPhoto(photo)) 
				photo.addCategory(category);
			else photo.removeCategory(category);
			photoService.save(photo);
		}
				return "redirect:/categories";
				
	}
	
	@PostMapping("delete/{id}")
	public String delete(@PathVariable int id, Category category, Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories); 
		Category categoryToDelete = categoryService.findById(id);
		
		if(categoryToDelete != null) {
			List <Photo> photos = photoService.findAll(); 
			for(Photo photo: photos) {
				photo.removeCategory(categoryToDelete); 
				photoService.save(photo);
			}
			
			categoryService.delete(categoryToDelete);
		}
		return "redirect:/categories"; 
		
	}
}
