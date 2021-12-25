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

import com.kh.board.model.vo.Frontboard;
import com.kh.community.model.service.FreeboardService;
import com.kh.community.model.vo.Freeboard;
import com.kh.community.model.vo.MvcUtils;

/**
 * Servlet implementation class FreeboardListServlet
 */
@WebServlet("/community/freeboardList")
public class FreeboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FreeboardService freeboardService = new FreeboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int numPerPage = 10;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {}
		int start = (cPage - 1) * numPerPage + 1; 
		int end = cPage * numPerPage;
		Map<String, Integer> param = new HashMap<>();
		param.put("start", start);
		param.put("end", end);
		
		
		List<Freeboard> list = freeboardService.selectAllFreeBoard(param);
		System.out.println("list@servlet = " + list);
		
		
		int totalContent = freeboardService.selectTotalFreeBoardCount();
		String url = request.getRequestURI(); 
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
		
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
				.getRequestDispatcher("/WEB-INF/views/community/freeboardList.jsp")
				.forward(request, response);
	}

	
}

