package com.kh.community.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.QuestionboardService;
import com.kh.community.model.vo.Freeboard;
import com.kh.community.model.vo.Questionboard;

/**
 * Servlet implementation class QuestionboardFinderServlet
 */
@WebServlet("/community/questionboardFinder")
public class QuestionboardFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionboardService questionboardService = new QuestionboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param@servlet = " + param);
		
		
		List<Questionboard> list = questionboardService.searchMember(param);
		System.out.println("list@servlet = " + list);
		
		request.setAttribute("list", list);
		request
			.getRequestDispatcher("/WEB-INF/views/community/questionboardList.jsp")
			.forward(request, response);
		
	}

}
