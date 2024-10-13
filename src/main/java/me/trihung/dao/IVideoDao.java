package me.trihung.dao;

import java.util.List;

import me.trihung.entity.Video;


public interface IVideoDao {
	
	List<Video> findAll();
	
	List<Video> findAll(int page, int pageSize);
	
	Video findById(int id);
	
	void insert(Video video);
	
	boolean update(Video video);
	
	boolean delete(int id);
	
	List<Video> findByTitle(String keyword);
	
	boolean softDelete(Video video);
	
	int count();
	
}