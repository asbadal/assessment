package com.filmland.springboot.service;

import java.util.List;
import java.util.Map;

import com.filmland.springboot.model.SharedSubscription;
import com.filmland.springboot.model.Subscription;

public interface SubscriptionService {
	
	Map<String, Object> createSubscription(Subscription subscription);
	
	Map<String, Object> shareSubscription(SharedSubscription sharedSubscription);

	List<Subscription> getAllSubscription();
	
	List<Subscription> getAllSubscriptionByEmail(String email);

	Subscription getSubscriptionByEmail(String email);
	
	Subscription getSubscriptionByEmailAndCategory(String email, String category);

}
