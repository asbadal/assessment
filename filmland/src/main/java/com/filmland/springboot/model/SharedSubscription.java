package com.filmland.springboot.model;

public class SharedSubscription {
	
	private String email;
	private String customer;
	private String subscribedCategory;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSubscribedCategory() {
		return subscribedCategory;
	}

	public void setSubscribedCategory(String subscribedCategory) {
		this.subscribedCategory = subscribedCategory;
	}

}
