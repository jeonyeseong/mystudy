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

import com.kh.board.model.service.FrontboardService;
import com.kh.board.model.vo.Frontboard;
import com.kh.community.model.service.FreeboardService;
import com.kh.community.model.vo.Freeboard;

/**
 * Servlet implementation class FreeboardFinderServlet
 */
@WebServlet("/community/freeboardFinder")
public class FreeboardFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FreeboardService freeboardService = new FreeboardService();

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
		
		
		List<Freeboard> list = freeboardService.searchMember(param);
		System.out.println("list@servlet = " + list);
		
		request.setAttribute("list", list);
		request
			.getRequestDispatcher("/WEB-INF/views/community/freeboardList.jsp")
			.forward(request, response);
	}
	

}
