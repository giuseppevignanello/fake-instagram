package org.java.app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.java.app.pojo.Message;
import org.java.app.pojo.Photo;
import org.java.app.repo.PhotoRepo;
import org.java.app.service.MessageService;
import org.java.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@Autowired
	private MessageService messageService;

	@GetMapping
	public ResponseEntity<List<Photo>> getAll() {
		List<Photo> photos = photoService.findAll();
		return new ResponseEntity<>(photos, HttpStatus.OK);

	}

	@GetMapping("{id}")
	public ResponseEntity<Photo> getPhoto(@PathVariable int id) {
		Optional<Photo> optPhoto = photoService.findById(id);

		if (optPhoto.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(optPhoto.get(), HttpStatus.OK);
	}

	@GetMapping("/filter/{filter}")
	public ResponseEntity<List<Photo>> getFilteredPhotos(@PathVariable String filter) {
		List<Photo> filteredPhotos = photoRepo.findByTitleContaining(filter);
		return new ResponseEntity<List<Photo>>(filteredPhotos, HttpStatus.OK);
	}

	@PostMapping("/send")
	public ResponseEntity<Boolean> send(@RequestBody Message message) {
		message.setDate(LocalDate.now());
		messageService.save(message);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@PostMapping("/likes_increment/{id}")
	public ResponseEntity<Photo> likesIncrement(@PathVariable int id) {
		Optional<Photo> optPhoto = photoService.findById(id);
		if (optPhoto.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		Photo photo = optPhoto.get();
		photo.incrementLikes();
		photoService.save(photo);
		return ResponseEntity.ok(photo);

	}
	@PostMapping("/likes_decrement/{id}")
	public ResponseEntity<Photo> likesDecrement(@PathVariable int id) {
		Optional<Photo> optPhoto = photoService.findById(id);

		if (optPhoto.isEmpty()) {

			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		Photo photo = optPhoto.get();
		photo.decrementLikes();
		photoService.save(photo);
		return ResponseEntity.ok(photo);

	}
}
