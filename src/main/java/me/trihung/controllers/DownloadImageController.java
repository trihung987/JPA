package me.trihung.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.trihung.config.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/image") 
public class DownloadImageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("from");
		String fileName = req.getParameter("fname");
		System.out.println(fileName);
		if (fileName==null || fileName.length()<=0)
			return;
		String directory = Constant.UPLOAD_DIRECTORY_VIDEO_POSTER;
		if (type.equals("cate"))
			directory = Constant.UPLOAD_DIRECTORY_CATE;
		File file = new File(getServletContext().getRealPath("") + directory+ fileName);
		resp.setContentType("image/jpeg");
		if (file.exists()) {
			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
		}
	}
}
