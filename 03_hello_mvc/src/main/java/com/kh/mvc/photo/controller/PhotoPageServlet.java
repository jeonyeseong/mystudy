package com.kh.mvc.photo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.photo.model.servicer.PhotoService;
import com.kh.mvc.photo.model.vo.Photo;

/**
 * Servlet implementation class PhotoPageServlet
 */
@WebServlet("/photo/page")
public class PhotoPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhotoService photoService = new PhotoService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자입력값처리
		final int numPerPage = 5;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {}
		int start = (cPage - 1) * numPerPage + 1; 
		int end = cPage * numPerPage;
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		
		//2.업무로직
		List<Photo> list = photoService.selectAllPhoto(param);
		System.out.println("PhotoPage@servlet = " + list);
		
		//3.json문자열 응답메세지 출력 : gson
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(list, response.getWriter());
		
	}

}
