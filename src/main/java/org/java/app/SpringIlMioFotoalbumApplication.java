package org.java.app;

import org.java.app.pojo.Category;
import org.java.app.pojo.Photo;
import org.java.app.service.CategoryService;
import org.java.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	@Autowired
	private PhotoService photoService;
	
	@Autowired 
	private CategoryService categoryService;
	
	
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
		
		
		Category landscape = new Category("landscape");
		Category portrait = new Category("portrait");
		Category macro = new Category("macro");
		Category wildlife = new Category("wildlife");
		Category street = new Category("street");
		
		
		categoryService.save(landscape);
		categoryService.save(portrait);
		categoryService.save(macro);
		categoryService.save(wildlife);
		categoryService.save(street);


	}

}
