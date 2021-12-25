package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.FreeboardService;
import com.kh.community.model.vo.Freeboard;

/**
 * Servlet implementation class FreeboardEnrollServlet
 */
@WebServlet("/community/freeboardEnroll")
public class FreeboardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FreeboardService freeboardService = new FreeboardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			Freeboard freeboard = new Freeboard(0, title, writer, content, 0, null, 0);
			System.out.println("[FreeBoardEnrollServlet] freeboard = " + freeboard);
			
			int result = freeboardService.insertFreeBoard(freeboard);
			System.out.println("[FreeBoardEnrollServlet] result = " + result);
			String msg = result > 0 ? "게시물 등록 성공" : "게시물 등록 실패";
			
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/community/freeboardList";
			response.sendRedirect(location);
		} catch (IOException e) {
			throw e;
		}
		
		
	}

}
