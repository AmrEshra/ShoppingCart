package com.edureka;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.edureka.model.UserAccount;
import com.edureka.repository.UserAccountRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@EnableAuthorizationServer
@AllArgsConstructor
@EnableResourceServer
public class AuthenticationServerApplication {
	   
	private final BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServerApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner init(UserAccountRepository userRepository) {
		return args -> Arrays.asList(new UserAccount("ali", passwordEncoder.encode("123"))).stream()
				.forEach(user -> userRepository.save(user));
	}
		
}
