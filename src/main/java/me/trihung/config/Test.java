package me.trihung.config;



import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.spi.PersistenceProvider;
import jakarta.persistence.spi.PersistenceProviderResolver;
import jakarta.persistence.spi.PersistenceProviderResolverHolder;
import me.trihung.dao.services.ICategoryService;
import me.trihung.dao.services.IVideoService;
import me.trihung.entity.Category;
import me.trihung.entity.Video;
import me.trihung.services.impl.CategoryServiceImpl;
import me.trihung.services.impl.VideoServiceImpl;

public class Test {
	
	static ICategoryService categoryService = new CategoryServiceImpl();
	static IVideoService videoService = new VideoServiceImpl();
	
	public static void main(String[] args) {
		
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		
		
		try {
			trans.begin();
			trans.commit();
			System.out.println("ok");
			
		}catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}

	}
	
	public static void printListVideo() {
		Category cate = categoryService.findById(4);
		System.out.println(cate.getCategoryname());
		System.out.println(cate.getVideos().size());
	}
	
	public static void addNew() {
		
		Category cate = getCate();
		Video vid = getVid();
		vid.setCategory(cate);
		categoryService.insert(cate);
		videoService.insert(vid);
	}
	
	public static Category getCate() {
		Category cate = new Category();
		cate.setCategoryname("ngu");
		cate.setImages("a");
		cate.setStatus(0);
		return cate;
	}
	
	public static Video getVid() {
		Video video = new Video();
		video.setVideoId("test123");
		video.setTitle("alo");
		video.setActive("deo");
		video.setViews("10000");
		return video;
	}

}
