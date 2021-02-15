package com.filmland.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "subscription")
@Table(name = "subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "email")
	private String email;

	@Column(name = "available_category")
	private String availableCategory;

	@Column(name = "remaining_content")
	private long remainingContent;

	@Column(name = "price")
	private double price;

	@Column(name = "head_subscription_id")
	private long headSubscriptionId;

	@CreationTimestamp
	private Date startDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvailableCategory() {
		return availableCategory;
	}

	public void setAvailableCategory(String availableCategory) {
		this.availableCategory = availableCategory;
	}

	public long getRemainingContent() {
		return remainingContent;
	}

	public void setRemainingContent(long remainingContent) {
		this.remainingContent = remainingContent;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getHeadSubscriptionId() {
		return headSubscriptionId;
	}

	public void setHeadSubscriptionId(long headSubscriptionId) {
		this.headSubscriptionId = headSubscriptionId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
