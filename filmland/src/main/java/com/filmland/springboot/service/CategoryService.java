package com.filmland.springboot.service;

import java.util.List;

import com.filmland.springboot.model.Category;
import com.filmland.springboot.model.CustomerCategoryData;

public interface CategoryService {
	
	Category createCategory(Category category);

	Category updateCategory(Category category);

	List<Category> getAllCategory();

	Category getCategoryById(long categoryId);
	
	CustomerCategoryData getAllCategoriesAndIncluded();

	void deleteCategory(long categoryId);
}
