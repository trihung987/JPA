package me.trihung.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import me.trihung.config.JPAConfig;
import me.trihung.dao.ICategoryDao;
import me.trihung.entity.Category;

public class CategoryDaoImpl implements ICategoryDao{

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public Category findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category cate = enma.find(Category.class, id);
		return cate;
	}

	@Override
	public void insert(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}finally {
			enma.close();
		}
		
	}

	@Override
	public boolean update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		}finally {
			enma.close();
		}
		return true;
	}

	@Override
	public boolean delete(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category catego = enma.find(Category.class, id);
			if(catego!=null)
				enma.remove(catego);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			return false;
		}finally {
			enma.close();
		}
		return true;
	}

	@Override
	public List<Category> findByName(String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Category c WHERE c.categoryname like :catename";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catename", "%"+keyword+"%");
		return query.getResultList();
	}

	@Override
	public boolean softDelete(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category> findAll(int page, int pageSize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Category c";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

}
