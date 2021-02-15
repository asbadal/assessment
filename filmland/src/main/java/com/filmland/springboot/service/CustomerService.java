package com.filmland.springboot.service;

import java.util.List;
import java.util.Map;

import com.filmland.springboot.model.Customer;

public interface CustomerService {
	
	Map<String, Object> customerRegistration(Customer customer);

	Customer getCustomerByEmail(String email);
	
	List<Customer> getAllCustomer();
}
