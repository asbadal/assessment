package com.filmland.springboot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.filmland.springboot.model.Customer;
import com.filmland.springboot.model.CustomerCategoryData;
import com.filmland.springboot.model.SharedSubscription;
import com.filmland.springboot.model.Subscription;
import com.filmland.springboot.service.CategoryService;
import com.filmland.springboot.service.CustomerService;
import com.filmland.springboot.service.SubscriptionService;

@RestController
public class ApplicationController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/registration")
	public ResponseEntity<Map<String, Object>> registration(@RequestBody Customer customer) {
		return ResponseEntity.ok().body(this.customerService.customerRegistration(customer));
	}
	
	@PostMapping("/subscribe")
	public ResponseEntity<Map<String, Object>> subscribe(@RequestBody Subscription subscription) {
		return ResponseEntity.ok().body(this.subscriptionService.createSubscription(subscription));
	}
	
	@PostMapping("/shareSubscription")
	public ResponseEntity<Map<String, Object>> shareSubscription(@RequestBody SharedSubscription sharedSubscription) {
		return ResponseEntity.ok().body(this.subscriptionService.shareSubscription(sharedSubscription));
	}
	
	@GetMapping("/customerData")
	public ResponseEntity<CustomerCategoryData> getCustomerData() {
		return ResponseEntity.ok().body(categoryService.getAllCategoriesAndIncluded());
	}
}
