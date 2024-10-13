package me.trihung.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import me.trihung.config.JPAConfig;
import me.trihung.dao.IVideoDao;
import me.trihung.entity.Video;

public class VideoDaoImpl implements IVideoDao{

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Video cate = enma.find(Video.class, id);
		return cate;
	}

	@Override
	public void insert(Video Video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(Video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		}finally {
			enma.close();
		}
		
	}

	@Override
	public boolean update(Video Video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			System.out.println("update from sql");
			enma.merge(Video);
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
			Video catego = enma.find(Video.class, id);
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
	public List<Video> findByTitle(String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT v FROM Video v WHERE v.title like :vidname";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("vidname", "%"+keyword+"%");
		return query.getResultList();
	}

	@Override
	public boolean softDelete(Video Video) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Video> findAll(int page, int pageSize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Video c";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

}
