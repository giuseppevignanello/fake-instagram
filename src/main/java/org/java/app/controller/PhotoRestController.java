package org.java.app.controller;

import java.util.List;
import java.util.Optional;

import org.java.app.pojo.Photo;
import org.java.app.pojo.PhotoDTO;
import org.java.app.repo.PhotoRepo;
import org.java.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0")
public class PhotoRestController {
	
	@Autowired
	private PhotoService photoService; 
	
	@Autowired 
	private PhotoRepo photoRepo;
	
	@GetMapping 
	public ResponseEntity<List<Photo>> getAll() {
		List<Photo> photos = photoService.findAll(); 
		return new ResponseEntity<>(photos, HttpStatus.OK); 
		
	}
	
	@PostMapping 
	public ResponseEntity<Integer> save(@RequestBody PhotoDTO photoDTO) {
		Photo photo = new Photo(photoDTO); 
		photo = photoService.save(photo); 
		return new ResponseEntity<>(photo.getId(), HttpStatus.OK); 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Photo> getPhoto(@PathVariable int id) {
		Optional<Photo> optPhoto = photoService.findById(id); 
		
		if(optPhoto.isEmpty()) {
			 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(optPhoto.get(), HttpStatus.OK);
	}
	
	@GetMapping("/filter/{filter}")
	public ResponseEntity<List<Photo>> getFilteredPhotos(@PathVariable String filter) {
		List<Photo> filteredPhotos = photoRepo.findByTitleContaining(filter);
		return new ResponseEntity<List<Photo>>(filteredPhotos, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Photo> updatePhoto(@PathVariable int id, @RequestBody PhotoDTO photoDTO) {
		Optional<Photo> optPhoto = photoService.findById(id); 
		
		if(optPhoto.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
		Photo photo = optPhoto.get(); 
		photo.fillFromPhotoDTO(photoDTO);
		try {
			photo = photoService.save(photo); 
			return new ResponseEntity<>(photo, HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Boolean> deletePizza(@PathVariable int id) {
		Optional<Photo> optPhoto = photoService.findById(id); 
		if(optPhoto.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); 
		}
		
		Photo photo = optPhoto.get(); 
		photoService.delete(photo); 
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
}

