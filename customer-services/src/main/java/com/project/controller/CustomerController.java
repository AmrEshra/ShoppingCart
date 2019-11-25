package com.project.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.exception.AuthenticationFailedException;
import com.project.model.Customer;
import com.project.model.UserAccount;
import com.project.services.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private final CustomerService customerService;
	
	@Autowired
	RestTemplate restTemplate;
	
	public CustomerController(CustomerService customerService, RestTemplate restTemplate) {
        this.customerService = customerService;
        this.restTemplate = restTemplate;
    }
	
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Customer> login(@RequestParam("username") String username, @RequestParam("password") String password)  throws NoSuchAlgorithmException, AuthenticationFailedException {
		Customer customer = customerService.authentication(username, password);
		return new ResponseEntity<Customer> (customer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers(){

		List<Customer> customerList = customerService.getAllCustomers();
		HttpHeaders responseHeaders = new HttpHeaders();
		
        return new ResponseEntity<>(customerList, responseHeaders, HttpStatus.OK);
    }
	
	@GetMapping("/customer/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable("id") Long id){

		Customer customer = customerService.getCustomerById(id);
		HttpHeaders responseHeaders = new HttpHeaders();
		
        return new ResponseEntity<>(customer.getUserName(), responseHeaders, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Boolean> createNewCustomer(@RequestBody Customer customer){
		
		UserAccount user = UserAccount.builder().userName(customer.getUserName()).password(customer.getPassword()).build();
		ResponseEntity<Void> response = restTemplate.postForEntity("http://authorization-server/signup", user, Void.class);
		
		if(!response.getStatusCode().equals(HttpStatus.CREATED)) {
			throw new RuntimeException("Failed to add to authorization server.");
		}
		
        boolean saved = false;
		try {
			saved = customerService.createNewCustomer(customer);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
