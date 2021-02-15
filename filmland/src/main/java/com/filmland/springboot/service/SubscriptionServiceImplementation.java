package com.filmland.springboot.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.filmland.springboot.model.Category;
import com.filmland.springboot.model.Customer;
import com.filmland.springboot.model.SharedSubscription;
import com.filmland.springboot.model.Subscription;
import com.filmland.springboot.repository.CategoryRepository;
import com.filmland.springboot.repository.CustomerRepository;
import com.filmland.springboot.repository.SubscriptionRepository;

@Service
@Transactional
public class SubscriptionServiceImplementation implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Map<String, Object> createSubscription(Subscription subscription) {
		
		String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		String status = "";
		String message = "";
		
		boolean validForRegistration = true;
		
		// check if fields are valid
		if (areSubscriptionFieldsValid(subscription)) {
			// check if valid for subscription
			
			// check if post user equals authenticated user
			if (authenticatedUser.equalsIgnoreCase(subscription.getEmail())) {
				
				// check if available category is valid
				Category dbCategory = this.categoryRepository.getCategortByName(subscription.getAvailableCategory());
				
				if (dbCategory != null) {
					
					// update post object fields
					subscription.setRemainingContent(dbCategory.getAvailableContent());
					subscription.setPrice(dbCategory.getPrice());
					
					// check if subscription exists
					Subscription dbSubscription = getSubscriptionByEmailAndCategory(subscription.getEmail(), subscription.getAvailableCategory());
					
					if (dbSubscription != null) {
						validForRegistration = false;
						
						status = "Subscription Failed";
						message = "The authenticated user already has a subscription for the given category";
					} 
					
				} else {
					validForRegistration = false;
					
					status = "Subscription Failed";
					message = "Category is not available";
				}
				
			} else {
				validForRegistration = false;
				
				status = "Subscription Failed";
				message = "The authenticated user cannot subscribe for someone else";
			}
			
			if (validForRegistration) {
				
				this.subscriptionRepository.save(subscription);
				
				status = "Subscription successful";
				message = "Thank you for subscribing";
			}
			
		} else {
			status = "Subscription Failed";
			message = "Review the submitted structure/values";
		}
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", status);
		body.put("message", message);

		return body;
		
	}

	@Override
	public List<Subscription> getAllSubscription() {
		return this.subscriptionRepository.findAll();
	}

	@Override
	public Subscription getSubscriptionByEmail(String email) {
		return null;
	}

	@Override
	public Subscription getSubscriptionByEmailAndCategory(String email, String category) {
		return this.subscriptionRepository.getSubscriptionByEmailAndCategory(email, category);
	}
	
	private boolean areSubscriptionFieldsValid(Subscription subscription) {
		
		boolean valid = true;
		
		if (subscription.getEmail() == null) {
			valid = false;
		} else {
			if (subscription.getEmail().equalsIgnoreCase("")) {				
				valid = false;
			}
		}
		
		if (subscription.getAvailableCategory() == null) {
			valid = false;
		} else {
			if (subscription.getAvailableCategory().equalsIgnoreCase("")) {
				valid = false;
			}
		}
		
		return valid;		
	}
	
	private boolean areSharedSubscriptionFieldsValid(SharedSubscription sharedSubscription) {
		
		boolean valid = true;
				
		if (sharedSubscription.getEmail() == null) {
			valid = false;
		} else {
			if (sharedSubscription.getEmail().equalsIgnoreCase("")) {				
				valid = false;
			}
		}
		
		if (sharedSubscription.getCustomer() == null) {
			valid = false;
		} else {
			if (sharedSubscription.getCustomer().equalsIgnoreCase("")) {
				valid = false;
			}
		}
		
		if (sharedSubscription.getSubscribedCategory() == null) {
			valid = false;
		} else {
			if (sharedSubscription.getSubscribedCategory().equalsIgnoreCase("")) {
				valid = false;
			}
		}
		
		return valid;		
	}

	@Override
	public Map<String, Object> shareSubscription(SharedSubscription sharedSubscription) {
		
		String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getName();
		
		String status = "";
		String message = "";
		
		boolean validForSharing = true;
		
		// check if fields are valid
		if (areSharedSubscriptionFieldsValid(sharedSubscription)) {
			
			// check if post user equals authenticated user
			if (authenticatedUser.equalsIgnoreCase(sharedSubscription.getEmail())) {
				
				// check if the shared customer is valid
				Customer dbCustomer = this.customerRepository.getCustomerByEmail(sharedSubscription.getCustomer());
				
				if (dbCustomer != null) {
					
					// check if category exists
					
					// check if available category is valid
					Category dbCategory = this.categoryRepository.getCategortByName(sharedSubscription.getSubscribedCategory());
					
					if (dbCategory != null) {
						
						// check if post user is subscribed to the post category
						Subscription dbSubscription = getSubscriptionByEmailAndCategory(sharedSubscription.getEmail(), sharedSubscription.getSubscribedCategory());
						
						if (dbSubscription == null) {
							validForSharing = false;
							
							status = "Subscription Sharing Failed";
							message = "The authenticated user is not subscribed to the given category";
						} else {
							
							// check if de customer is already shared with the given category
							
							// check if post user is subscribed to the post category
							Subscription dbCustomerSubscription = getSubscriptionByEmailAndCategory(sharedSubscription.getCustomer(), sharedSubscription.getSubscribedCategory());
							
							if (dbCustomerSubscription != null) {
								validForSharing = false;
																
								status = "Subscription Sharing Failed";
								message = "The customer is already subscribed/shared with the given category";
							}
						}
					} else {
						validForSharing = false;
						
						status = "Subscription Sharing Failed";
						message = "The category is not available";
					}
				} else {
					validForSharing = false;
					
					status = "Subscription Sharing Failed";
					message = "The authenticated user cannot share with a non customer";
				}
				
			} else {
				validForSharing = false;
				
				status = "Subscription Sharing Failed";
				message = "The authenticated User cannot share for someone else";
			}
			
			if (validForSharing) {
				
				Subscription subscription = new Subscription();
				subscription.setEmail(sharedSubscription.getCustomer());
				subscription.setAvailableCategory(sharedSubscription.getSubscribedCategory());
				subscription.setHeadSubscriptionId(getSubscriptionByEmailAndCategory(sharedSubscription.getEmail(), sharedSubscription.getSubscribedCategory()).getId());
								
				this.subscriptionRepository.save(subscription);
				
				status = "Subscription Sharing successful";
				message = "Thank you for sharing your subscription";
			}
			
		} else {
			status = "Subscription Sharing Failed";
			message = "Review the submitted structure/values";
		}
		
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", status);
		body.put("message", message);

		return body;
	}
	
	@Override
	public List<Subscription> getAllSubscriptionByEmail(String email) {
		return this.subscriptionRepository.getSubscriptionsByEmail(email);
	}

}
