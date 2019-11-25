package com.project.bootstrap;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.model.Customer;
import com.project.repository.CustomerRepository;
import com.project.util.ShaHashing;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CustomerRepository customerRespository;

    public Bootstrap(CustomerRepository customerRespository) {
		this.customerRespository = customerRespository;
	}

	@Override
    public void run(String... args) throws Exception {
    	fillCustomers();
    }

	private void fillCustomers() throws NoSuchAlgorithmException {
		Customer amr = Customer.builder().firstName("Amr").lastName("Ali").userName("AmrEshra")
				.password(ShaHashing.encrypted("123456")).build();
		
		Customer ola = Customer.builder().firstName("Ola").lastName("Magdy").userName("OlaMagdy")
				.password(ShaHashing.encrypted("654321")).build();
        
        customerRespository.save(amr);
        customerRespository.save(ola);

        System.out.println("Customers Data Loaded = " + customerRespository.count() );

	}

}
