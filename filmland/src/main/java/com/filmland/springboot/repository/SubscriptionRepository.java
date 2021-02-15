package com.filmland.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.filmland.springboot.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	
	@Query("SELECT sd from subscription sd where sd.email=?1 and sd.availableCategory=?2")
	public Subscription getSubscriptionByEmailAndCategory(String email, String category);
	
	@Query("SELECT sd from subscription sd where sd.email=?1")
	public List<Subscription> getSubscriptionsByEmail(String email);
	
}
