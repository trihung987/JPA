package me.trihung.dao.services;

import java.util.List;

import me.trihung.entity.Category;

public interface ICategoryService {
	
	List<Category> findAll();
	
	List<Category> findAll(int page, int pageSize);
	
	Category findById(int id);
	
	void insert(Category category);
	
	boolean update(Category category);
	
	boolean delete(int id);
	
	List<Category> findByName(String keyword);
	
	boolean softDelete(Category category);
	
	int count();
	
}
