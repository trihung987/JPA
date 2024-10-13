package me.trihung.services.impl;

import java.util.List;

import me.trihung.dao.IVideoDao;
import me.trihung.dao.impl.VideoDaoImpl;
import me.trihung.dao.services.IVideoService;
import me.trihung.entity.Video;

public class VideoServiceImpl implements IVideoService{

	public IVideoDao vidDao = new VideoDaoImpl();
	
	@Override
	public List<Video> findAll() {
		return vidDao.findAll();
	}

	@Override
	public List<Video> findAll(int page, int pageSize) {
		return vidDao.findAll(page, pageSize);
	}

	@Override
	public Video findById(int id) {
		return vidDao.findById(id);
	}

	@Override
	public void insert(Video Video) {
		vidDao.insert(Video);
	}

	@Override
	public boolean update(Video Video) {
		return vidDao.update(Video);
	}

	@Override
	public boolean delete(int id) {
		return vidDao.delete(id);
	}

	@Override
	public List<Video> findByTitle(String keyword) {
		return vidDao.findByTitle(keyword);
	}

	@Override
	public boolean softDelete(Video Video) {
		return vidDao.softDelete(Video);
	}

	@Override
	public int count() {
		return vidDao.count();
	}

}
