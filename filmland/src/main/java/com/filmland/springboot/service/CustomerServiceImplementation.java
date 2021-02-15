package com.filmland.springboot.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.filmland.springboot.model.Category;
import com.filmland.springboot.model.Customer;
import com.filmland.springboot.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Map<String, Object> customerRegistration(Customer customer) {

		boolean validForRegistration = true;
		boolean fieldsValid = true;
		String status = "";
		String message = "";
		
		// customer list
		Customer customerExists = null;
		
		// check if email is null or empty
		if (customer.getEmail() != null) {
			if (!customer.getEmail().equalsIgnoreCase("")) {
				customerExists = getCustomerByEmail(customer.getEmail());
				
				if (customerExists != null) {
					validForRegistration = false;
				}
			}
		} 
		
		if (validForRegistration) {
			
			if (customer.getEmail() == null) {
				fieldsValid = false;
			} else {
				if (customer.getEmail().equalsIgnoreCase("")) {
					fieldsValid = false;
				}
			}
			
			if (customer.getPassword() == null) {
				fieldsValid = false;
			} else {
				if (customer.getPassword().equalsIgnoreCase("")) {
					fieldsValid = false;
				}
			}
			
			if (fieldsValid) {
				
				// encode password
				customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
								
				this.customerRepository.save(customer);
				
				status = "Registration Successful";
				message = "Welcome to Filmland. Use the /login to access the system.";
			} else {
				status = "Registration Failed";
				message = "Review the submitted structure/values";
			}
			
		} else {
			status = "Registration Failed";
			message = "You are already a customer. Use the /login to access the system.";
		}
		
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", status);
		body.put("message", message);

		return body;
	}

	@Override
	public List<Customer> getAllCustomer() {
		return this.customerRepository.findAll();
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return this.customerRepository.getCustomerByEmail(email);
	}

}
