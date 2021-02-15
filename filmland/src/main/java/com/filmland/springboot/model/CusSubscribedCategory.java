package com.filmland.springboot.model;

public class CusSubscribedCategory {
	private String name;
	private long remainingContent;
	private double price;
	private String startDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
