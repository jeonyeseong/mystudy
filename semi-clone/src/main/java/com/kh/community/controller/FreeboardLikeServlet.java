package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.FreeboardService;

/**
 * Servlet implementation class FreeboardLikeServlet
 */
@WebServlet("/community/freeboardLike")
public class FreeboardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FreeboardService freeboardService = new FreeboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = freeboardService.updateLikeCount(no);
		String msg = (result > 0) ? "추천 성공" : "추천 실패";	
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/community/freeboardView?no=" + no;
		response.sendRedirect(location);
	}

}
