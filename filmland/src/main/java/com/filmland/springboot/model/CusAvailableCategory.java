package com.filmland.springboot.model;

public class CusAvailableCategory {
	private String name;
	private long availableContent;
	private double price;

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

}
