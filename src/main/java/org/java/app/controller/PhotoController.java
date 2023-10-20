package org.java.app.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.auth.pojo.User;
import org.java.app.pojo.Category;
import org.java.app.pojo.Photo;
import org.java.app.service.CategoryService;
import org.java.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public String getIndex(Model model, @RequestParam(required = false) String title, Authentication auth) {
		User user = (User) auth.getPrincipal();
		if(user.getUsername().equals("admin")) {
			List<Photo> photos = title == null ? photoService.findAll() : photoService.filterByName(title, title);
			model.addAttribute("photos", photos);
		} else {
			List<Photo> photos = title == null 
					? photoService.findUserPhotos(user)
					: photoService.filterByNameUser(user, title, title);
					model.addAttribute("photos", photos);
		}
		
		return "index";

	}

	@GetMapping("/{id}")
	public String show(Model model, @PathVariable int id, Authentication auth ) {
		
		User user = (User) auth.getPrincipal();
		Optional<Photo> optPhoto = photoService.findById(id);
		
		if(optPhoto.isEmpty()) {
			return "redirect:/";
		}
		Photo photo = optPhoto.get();
		if(user.getId() == 1 || photo.getUser().getId() == user.getId()) {
			List<Category> categories = photo.getCategories();
			model.addAttribute("photo", photo);
			model.addAttribute("categories", categories);

			return "show";
		} else {
			return "redirect:/";
		}
		

	}

	@GetMapping("/create")
	public String getCreateForm(Model model) {

		List<Category> categories = categoryService.findAll();
		model.addAttribute("photo", new Photo());
		model.addAttribute("categories", categories);
		return "create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute Photo photo,
			BindingResult bindingResult, Model model, Authentication auth) {
		
		List<Photo> photos = photoService.findAll(); 
		model.addAttribute("photos", photos);
		
		if(bindingResult.hasErrors()) {
			System.out.println("Error: ");
			bindingResult.getAllErrors().forEach(System.out::println);
			return "create";
		}
		
		photo.setLikes(0);
		photo.setUser((User) auth.getPrincipal());
		
		try {
			photoService.save(photo); 
			List<Category> categories = categoryService.findAll(); 
			for (Category category: categories) {
				if(photo.hasCategory(category))
					category.addPhotos(photo); 
				else category.removePhotos(photo);
			}
		} catch(Exception e) {
			System.out.println("Errors: " + e.getClass().getSimpleName());
			return "create";
		}
		
		return "redirect:/";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable int id, Model model, Authentication auth) {
		User user = (User) auth.getPrincipal();
		
		Optional<Photo> optPhoto = photoService.findById(id);
		
		if(optPhoto.isEmpty()) {
			return "redirect:/";
		}
		
		Photo photo = optPhoto.get();
		if(user.getId() == 1 || photo.getUser().getId() == user.getId()) {
			List<Category> categories = categoryService.findAll();
			model.addAttribute("photo", photo);
			model.addAttribute("categories", categories); 
			return "edit"; 
		} else {
			return "redirect:/";
		}
		
		
	}
	
	@PostMapping("/edit/{id}")
	public String update(@PathVariable int id, @Valid @ModelAttribute Photo photo,
			BindingResult bindingResult, Model model, Authentication auth) {
		List<Photo> photos = photoService.findAll(); 
		model.addAttribute("photos", photos);
		
		if(bindingResult.hasErrors()) {
			System.out.println("Error: ");
			bindingResult.getAllErrors().forEach(System.out::println);
			return "edit";
		}
		
		Optional<Photo> optPhoto = photoService.findById(id);
		
		if (optPhoto.isEmpty())
			return "redirect:/";
		
		User userAuth = (User) auth.getPrincipal();
		User user = optPhoto.get().getUser();
		photo.setUser(user);
		
		
		if(userAuth.getUsername().equals("admin") && !photo.getVisible()) {
			photo.setHidden(true);
		}
		
		try {
			photoService.save(photo); 
		} catch (Exception e) {
			System.out.println("Errors: " + e.getClass().getSimpleName());
			return "edit";
		}
		return "index";
	}
	
	@PostMapping("/delete/{id}") 
	public String delete(@PathVariable int id, Photo photo, Model model) {
		List<Photo> photos = photoService.findAll(); 
		model.addAttribute("photos", photos); 
		
		Optional<Photo> photoToDelete = photoService.findById(id);
		if(photoToDelete.isEmpty()) {
			return "redirect:/";
		}
		photoService.delete(photoToDelete.get());
		return "redirect:/";
	}

				
}

