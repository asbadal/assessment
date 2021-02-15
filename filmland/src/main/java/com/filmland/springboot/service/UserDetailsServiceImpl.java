package com.filmland.springboot.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.filmland.springboot.model.Customer;
import com.filmland.springboot.repository.CustomerRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	private CustomerRepository customerRepository;

	public UserDetailsServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.getCustomerByEmail(email);
		if (customer == null) {
			throw new UsernameNotFoundException(email);
		}
		return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
				Collections.emptyList());
	}
}
