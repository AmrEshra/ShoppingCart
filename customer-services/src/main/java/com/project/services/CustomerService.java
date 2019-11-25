package com.project.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exception.AuthenticationFailedException;
import com.project.model.Customer;
import com.project.repository.CustomerRepository;
import com.project.util.ShaHashing;

@Service
public class CustomerService {
	
	@Autowired
    CustomerRepository customerRepository;

	public Customer authentication(String userName, String password) 
			throws NoSuchAlgorithmException, AuthenticationFailedException {
		Customer customer = customerRepository.findByUserName(userName);
		
		if(customer.getPassword().equals(ShaHashing.encrypted(password)))
			return customer;
		else
			throw new AuthenticationFailedException();
	}
	
    public boolean createNewCustomer(Customer customer) throws NoSuchAlgorithmException {
    	customer.setPassword(ShaHashing.encrypted(customer.getPassword()));
        Customer saved = customerRepository.save(customer);
        return saved !=null;
    }
    
    public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
    
    public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).get();
	}

}
