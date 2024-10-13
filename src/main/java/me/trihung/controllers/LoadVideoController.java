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
@WebServlet(urlPatterns = "/video") 
public class LoadVideoController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fname");
		System.out.println(fileName);
		if (fileName==null || fileName.length()<=0)
			return;
		File file = new File(getServletContext().getRealPath("") + Constant.UPLOAD_DIRECTORY_VIDEO_VID + fileName);
		resp.setContentType("video/mp4");
		if (file.exists()) {
			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
		}
	}
}
