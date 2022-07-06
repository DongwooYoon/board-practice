package jpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jpa.springboot.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{
	public Category findByEnName(String categoryEnName);
}
