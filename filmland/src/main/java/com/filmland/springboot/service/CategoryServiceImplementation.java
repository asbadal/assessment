package com.filmland.springboot.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.filmland.springboot.model.Category;
import com.filmland.springboot.model.CusAvailableCategory;
import com.filmland.springboot.model.CusSubscribedCategory;
import com.filmland.springboot.model.CustomerCategoryData;
import com.filmland.springboot.model.Subscription;
import com.filmland.springboot.repository.CategoryRepository;
import com.filmland.springboot.repository.SubscriptionRepository;

@Service
@Transactional
public class CategoryServiceImplementation implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Override
	public Category createCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		Optional<Category> categoryFromDatabase = this.categoryRepository.findById(category.getId());
		
		if (categoryFromDatabase.isPresent()) {
			Category categoryUpdate = categoryFromDatabase.get();
			categoryUpdate.setId(category.getId());
			categoryUpdate.setName(category.getName());
			categoryUpdate.setPrice(category.getPrice());
			categoryUpdate.setBillableAfterDays(category.getBillableAfterDays());
			
			this.categoryRepository.save(categoryUpdate);
			
			return categoryUpdate;
		} else {
			return null;
		}
	}

	@Override
	public List<Category> getAllCategory() {		
		return this.categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(long categoryId) {
		
		Optional<Category> categoryFromDatabase = this.categoryRepository.findById(categoryId);
		
		if (categoryFromDatabase.isPresent()) {
			return categoryFromDatabase.get();
		} else {
			return null;
		}
	}

	@Override
	public void deleteCategory(long categoryId) {
		Optional<Category> categoryFromDatabase = this.categoryRepository.findById(categoryId);
		
		if (categoryFromDatabase.isPresent()) {
			this.categoryRepository.delete(categoryFromDatabase.get());
		}
	}

	@Override
	public CustomerCategoryData getAllCategoriesAndIncluded() {
				
		// authenticated user
		String authenticatedUser =  SecurityContextHolder.getContext().getAuthentication().getName();
		
		ArrayList<CusAvailableCategory> availableCategories = new ArrayList<CusAvailableCategory>();
		ArrayList<CusSubscribedCategory> subscribedCategories = new ArrayList<CusSubscribedCategory>();
		
		// get available categories
		List<Category> dbCategories = getAllCategory();
		
		for (Category i : dbCategories) {
			
			CusAvailableCategory nrecord = new CusAvailableCategory();
			nrecord.setName(i.getName());
			nrecord.setAvailableContent(i.getAvailableContent());
			nrecord.setPrice(i.getPrice());
			
			availableCategories.add(nrecord);
		}
		
		// get all subscribed categories
		List<Subscription> dbSubscription = this.subscriptionRepository.getSubscriptionsByEmail(authenticatedUser);
		
		for (Subscription i : dbSubscription) {
			
			CusSubscribedCategory nrecord = new CusSubscribedCategory();
			nrecord.setName(i.getAvailableCategory());
			nrecord.setRemainingContent(i.getRemainingContent());
			nrecord.setPrice(i.getPrice());
			nrecord.setStartDate(new SimpleDateFormat("dd-MM-yyyy").format(i.getStartDate()));
			
			subscribedCategories.add(nrecord);
		}
		
		// add data to CustomerCategoryData
		CustomerCategoryData customerCategoryData = new CustomerCategoryData();
		customerCategoryData.setAvailableCategories(availableCategories);
		customerCategoryData.setSubscribedCategories(subscribedCategories);
		
		return customerCategoryData;
	}

}
