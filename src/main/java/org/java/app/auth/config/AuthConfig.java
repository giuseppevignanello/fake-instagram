package org.java.app.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.java.app.auth.service.UserService;

@Configuration
public class AuthConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
				.requestMatchers("/create").hasAuthority("ADMIN")
				.requestMatchers("/update/**").hasAuthority("ADMIN")
				.requestMatchers("/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/categories/create").hasAuthority("ADMIN")
				.requestMatchers("/categories/delete/**").hasAuthority("ADMIN")
				.requestMatchers("/**").permitAll().and().formLogin().defaultSuccessUrl("/")
				.and().logout();

		return http.build();
	}

	@Bean
	UserService userDetailsService() {
		return new UserService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}
