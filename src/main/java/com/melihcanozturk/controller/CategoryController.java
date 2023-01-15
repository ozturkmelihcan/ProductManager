package com.melihcanozturk.controller;

import com.melihcanozturk.entity.Category;
import com.melihcanozturk.service.CategoryService;
import com.melihcanozturk.util.BAUtils;

public class CategoryController {

	
	CategoryService categoryService;
	
	public CategoryController() {
		this.categoryService = new CategoryService();
	}
	
	public void createCategory() {
		
		Category category = new Category();
		String categoryName = BAUtils.readString("Please enter the category you want to add...");
		category.setName(categoryName);
		categoryService.create(category);
		
	}

	

}
