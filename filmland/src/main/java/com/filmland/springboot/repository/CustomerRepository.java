package com.filmland.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.filmland.springboot.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	@Query("SELECT cu from customer cu where cu.email=?1")
	public Customer getCustomerByEmail(String email);
}
