package org.java.app;

import org.java.app.auth.pojo.Role;
import org.java.app.auth.pojo.User;
import org.java.app.auth.service.RoleService;
import org.java.app.auth.service.UserService;
import org.java.app.pojo.Category;
import org.java.app.pojo.Photo;
import org.java.app.service.CategoryService;
import org.java.app.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner{

	@Autowired
	private PhotoService photoService;
	
	@Autowired 
	private CategoryService categoryService;
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Role admin = new Role("ADMIN");
		Role user = new Role("USER");
		
		roleService.save(admin);
		roleService.save(user);
		
		final String pwsAdmin = new BCryptPasswordEncoder().encode("password");
		final String pwsUser = new BCryptPasswordEncoder().encode("password");
		
		User myAdmin = new User("admin", pwsAdmin, admin);
		User myUser = new User("user", pwsUser, user);
		User myUser2 = new User("user2", pwsUser, user);
		
		userService.save(myAdmin);
		userService.save(myUser);
		userService.save(myUser2);
		
		Photo photo1 = new Photo("Photo1", "La prima foto", "https://picsum.photos/200/300", true, false, myUser);
		Photo photo2 = new Photo("Photo2", "La seconda foto", "https://picsum.photos/200/300", true, false, myUser);
		Photo photo3 = new Photo("Photo3", "La terza foto", "https://picsum.photos/200/300", true, false, myUser2);
		
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
