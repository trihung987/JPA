package me.trihung.services.impl;

import java.util.List;

import me.trihung.dao.ICategoryDao;
import me.trihung.dao.impl.CategoryDaoImpl;
import me.trihung.dao.services.ICategoryService;
import me.trihung.entity.Category;

public class CategoryServiceImpl implements ICategoryService{

	public ICategoryDao cateDao = new CategoryDaoImpl();
	
	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public List<Category> findAll(int page, int pageSize) {
		return cateDao.findAll(page, pageSize);
	}

	@Override
	public Category findById(int id) {
		return cateDao.findById(id);
	}

	@Override
	public void insert(Category category) {
		cateDao.insert(category);
	}

	@Override
	public boolean update(Category category) {
		return cateDao.update(category);
	}

	@Override
	public boolean delete(int id) {
		return cateDao.delete(id);
	}

	@Override
	public List<Category> findByName(String keyword) {
		return cateDao.findByName(keyword);
	}

	@Override
	public boolean softDelete(Category category) {
		return cateDao.softDelete(category);
	}

	@Override
	public int count() {
		return cateDao.count();
	}

}
