package me.trihung.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import me.trihung.config.Constant;
import me.trihung.dao.services.ICategoryService;
import me.trihung.dao.services.IVideoService;
import me.trihung.entity.Video;
import me.trihung.services.impl.CategoryServiceImpl;
import me.trihung.services.impl.VideoServiceImpl;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 70, maxFileSize = 1024 * 1024 * 70, maxRequestSize = 1024 * 1024 * 70)
@WebServlet(urlPatterns = { "/manager/video", "/manager/video/edit", "/manager/video/update",
		"/manager/video/insert", "/manager/video/delete", "/manager/video/search" })
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public IVideoService videoService = new VideoServiceImpl();
	public ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/manager/video/edit")) {
			String id = req.getParameter("id");
			System.out.println(id + " >");
			Video video = videoService.findById(Integer.parseInt(id));
			System.out.println(video.getTitle());
			req.setAttribute("video", video);
			req.getRequestDispatcher("/view/manager/video/video-edit.jsp").forward(req, resp);
		} else if (url.contains("/manager/video/insert")) {
			req.getRequestDispatcher("/view/manager/video/video-insert.jsp").forward(req, resp);
			
		} else if (url.contains("/manager/video/delete")) {
			String id = req.getParameter("id");
			Video video = videoService.findById(Integer.parseInt(id));
			if (video==null) {
				loadCategories(req, resp);
				return;
			}
			videoService.delete(video.getId());
			System.out.println("delete video id: " + id);
			loadCategories(req, resp);
		} else if (url.contains("/manager/video/search")) {
			String name = req.getParameter("search");
			System.out.println("search by title: " + name);
			if (name.strip().length() == 0) {
				List<Video> list = videoService.findAll();
				req.setAttribute("listvideo", list);
				req.getRequestDispatcher("/view/manager/video-list.jsp").forward(req, resp);
				return;
			}
			List<Video> list = videoService.findByTitle(name);
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/view/manager/video-list.jsp").forward(req, resp);
		} else {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/view/manager/video/video-list.jsp").forward(req, resp);
		}
	}
	

	private String getFileName(Part part) {
		return part.getSubmittedFileName();
	}
 
	public String uploadFile(HttpServletRequest request, Boolean imgorvideo) {
		String directory =  imgorvideo ? Constant.UPLOAD_DIRECTORY_VIDEO_POSTER : Constant.UPLOAD_DIRECTORY_VIDEO_VID;
		String uploadPath = getServletContext().getRealPath("") + directory;
		File uploadDir = new File(uploadPath);

		if (!uploadDir.exists())
			uploadDir.mkdirs();
		try {
			String fileName = "";
			Part part = request.getPart(imgorvideo?"images":"videos");
			fileName = getFileName(part);
			if (fileName==null || fileName.length() <= 0)
				return null;
			System.out.println("write " + fileName+"|");
			System.out.println(uploadPath);
			part.write(uploadPath + fileName);
			request.setAttribute("message", "File " + fileName + " has uploaded successfully!");
			return fileName;

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "There was an error: " + e.getMessage());

		}
		return null;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if (url.contains("/manager/video/update")) {
			System.out.println("update >>>>>>>>>>>>>>>>>");
			String videotitle = req.getParameter("videotitle");
			String videoactive = req.getParameter("videoactive");
			String videodes = req.getParameter("videodes");
			String videoview = req.getParameter("videoviews");
			String videocateid = req.getParameter("videocateid");
			String poster = uploadFile(req, true);
			String videoid = uploadFile(req, false);
			String id = req.getParameter("id");
			System.out.println("=> "+videotitle);
			System.out.println("=> "+videoactive);
			System.out.println("=> "+videodes);
			System.out.println("=> "+videoview);
			System.out.println("=> "+videocateid);
			System.out.println("ID: "+id);
			Video video = videoService.findById(Integer.parseInt(id));
			if (poster!=null)
				video.setPoster(poster);
			
			if (videoid!=null)
				video.setVideoId(videoid);
			
			video.setTitle(videotitle);
			video.setActive(videoactive);
			video.setDescription(videodes);
			video.setViews(videoview);
			videoService.update(video);
			
			loadCategories(req, resp);

		} else if (url.contains("/manager/video/insert")) {
			System.out.println("insert >>>>>>>>>>>>>>>>>>>>");
			String videotitle = req.getParameter("videotitle");
			String videoactive = req.getParameter("videoactive");
			String videodes = req.getParameter("videodes");
			String videoview = req.getParameter("videoviews");
			String videocateid = req.getParameter("videocateid");
			String poster = uploadFile(req, true);
			String videoid = uploadFile(req, false);
			Video video = new Video();
			System.out.println("=> "+videotitle);
			System.out.println("=> "+videoactive);
			System.out.println("=> "+videodes);
			System.out.println("=> "+videoview);
			System.out.println("=> "+videocateid);
			video.setPoster(poster);
			video.setVideoId(videoid);
			video.setTitle(videotitle);
			video.setActive(videoactive);
			video.setDescription(videodes);
			video.setViews(videoview);
			video.setCategory(cateService.findById(Integer.parseInt(videocateid)));
			
			videoService.insert(video);
			loadCategories(req, resp);
		}
	}

	public void loadCategories(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(req.getContextPath() + "/manager/video");
	}

}
