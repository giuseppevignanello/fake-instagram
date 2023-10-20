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
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

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

		Category landscape = new Category("landscape");
		Category portrait = new Category("portrait");
		Category macro = new Category("macro");
		Category wildlife = new Category("wildlife");
		Category street = new Category("street");
		Category food = new Category("food");

		categoryService.save(landscape);
		categoryService.save(portrait);
		categoryService.save(macro);
		categoryService.save(wildlife);
		categoryService.save(street);
		categoryService.save(food);

		Photo bear = new Photo("bear", "A bear in its natural habitat",
				"https://cdn.naturettl.com/wp-content/uploads/2020/08/04123339/creative-ways-to-improve-your-wildlife-photography-800x534.jpg?p=20603",
				true, false, 0, myUser, landscape, wildlife);
		Photo red_hiding_hood = new Photo("Red Hiding Hood", "A little girl dressed as a red riding hood",
				"https://images.unsplash.com/photo-1624711517157-25991163e537?auto=format&fit=crop&q=80&w=1000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8cG9ydHJhaXQlMjBwaG90b2dyYXBoeXxlbnwwfHwwfHx8MA%3D%3D",
				true, false, 0, myUser, portrait);
		Photo thai_food = new Photo("Street Food in Thailand", "La terza foto", "https://images.pexels.com/photos/920570/pexels-photo-920570.jpeg?cs=srgb&dl=pexels-min-an-920570.jpg&fm=jpg", true, false, 0, myUser2,
				street, food);

		photoService.save(bear);
		photoService.save(red_hiding_hood);
		photoService.save(thai_food);

	}

}
