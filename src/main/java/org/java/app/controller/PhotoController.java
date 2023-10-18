package org.java.app.controller;


import java.util.List;
import java.util.Optional;

import org.java.app.pojo.Category;
import org.java.app.pojo.Photo;
import org.java.app.service.CategoryService;
import org.java.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller 
@RequestMapping("/")
public class PhotoController {

	@Autowired 
	private PhotoService photoService; 
	@Autowired 
	private CategoryService categoryService;
	
	@GetMapping("/")
	public String getIndex(Model model, @RequestParam(required = false) String title) {
		List<Photo> photos = title == null ? photoService.findAll() : photoService.filterByName(title, title);
		model.addAttribute("photos", photos);
		return "index";
		
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable int id ) {
		
		Optional<Photo> photo = photoService.findById(id); 
		List<Category> categories = photo.get().getCategories();
		model.addAttribute("photo", photo.get());
		model.addAttribute("categories", categories);
		
		return "show";
		
	}
}
