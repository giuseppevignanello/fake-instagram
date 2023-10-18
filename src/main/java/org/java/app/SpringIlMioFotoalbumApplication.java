package org.java.app;

import org.java.app.pojo.Photo;
import org.java.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	@Autowired
	private PhotoService photoService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Photo photo1 = new Photo("Photo1", "La prima foto", "https://picsum.photos/200/300", true);
		Photo photo2 = new Photo("Photo2", "La seconda foto", "https://picsum.photos/200/300", true);
		Photo photo3 = new Photo("Photo3", "La terza foto", "https://picsum.photos/200/300", true);
		
		photoService.save(photo1); 
		photoService.save(photo2); 
		photoService.save(photo3);
	}

}
