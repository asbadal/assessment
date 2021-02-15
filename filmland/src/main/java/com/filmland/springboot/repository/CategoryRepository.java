package com.filmland.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.filmland.springboot.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("SELECT ca from category ca where ca.name=?1")
	public Category getCategortByName(String name);
}
