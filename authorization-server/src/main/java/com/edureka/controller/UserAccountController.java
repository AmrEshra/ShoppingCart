package com.edureka.controller;

import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edureka.model.UserAccount;
import com.edureka.repository.UserAccountRepository;

@RestController
public class UserAccountController {
	
	private UserAccountRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
	
	public UserAccountController(UserAccountRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("user")
	public String getUserInfo(Principal principal) {
		return principal.getName();
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserAccount>> getAllUser() {
		List<UserAccount> usersList = userRepository.findAll();
		HttpHeaders responseHeaders = new HttpHeaders();
		
        return new ResponseEntity<>(usersList, responseHeaders, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Void> getUserInfo(@RequestBody UserAccount userAccount) throws URISyntaxException {

		UserAccount u = new UserAccount(userAccount.getUserName(), passwordEncoder.encode(userAccount.getPassword()));
		userRepository.save(u).getId();
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
