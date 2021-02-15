package com.filmland.springboot.model;

import java.util.ArrayList;

public class CustomerCategoryData {
	private ArrayList<CusAvailableCategory> availableCategories;
	private ArrayList<CusSubscribedCategory> subscribedCategories;

	public ArrayList<CusAvailableCategory> getAvailableCategories() {
		return availableCategories;
	}

	public void setAvailableCategories(ArrayList<CusAvailableCategory> availableCategories) {
		this.availableCategories = availableCategories;
	}

	public ArrayList<CusSubscribedCategory> getSubscribedCategories() {
		return subscribedCategories;
	}

	public void setSubscribedCategories(ArrayList<CusSubscribedCategory> subscribedCategories) {
		this.subscribedCategories = subscribedCategories;
	}

}
