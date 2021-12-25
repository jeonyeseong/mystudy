package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.QuestionboardService;

/**
 * Servlet implementation class QuestionboardLikeServlet
 */
@WebServlet("/community/questionboardLike")
public class QuestionboardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionboardService questionboardService = new QuestionboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = questionboardService.updateLikeCount(no);
		String msg = (result > 0) ? "추천 성공" : "추천 실패";	
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/community/questionboardView?no=" + no;
		response.sendRedirect(location);
	}

}
