package com.filmland.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "category")
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "available_content")
	private long availableContent;

	@Column(name = "price")
	private double price;

	@Column(name = "billable_after_days")
	private long billableAfterDays;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getAvailableContent() {
		return availableContent;
	}

	public void setAvailableContent(long availableContent) {
		this.availableContent = availableContent;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getBillableAfterDays() {
		return billableAfterDays;
	}

	public void setBillableAfterDays(long billableAfterDays) {
		this.billableAfterDays = billableAfterDays;
	}

}
