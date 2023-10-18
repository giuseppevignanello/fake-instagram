package org.java.app.service;

import org.java.app.pojo.Photo;
import org.java.app.repo.PhotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

	@Autowired
	private PhotoRepo photoRepo;
	
	public Photo save(Photo photo) {
		return photoRepo.save(photo);
	}
}
