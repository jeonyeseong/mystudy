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
import com.kh.community.model.vo.MvcUtils;
import com.kh.community.model.vo.Questionboard;


/**
 * Servlet implementation class QuestionboardListServlet
 */
@WebServlet("/community/questionboardList")
public class QuestionboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionboardService questionboardService = new QuestionboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자입력값
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
			
			// 2. 업무로직
			// 2-a. content영역 : 페이징쿼리
			List<Questionboard> list = questionboardService.selectAllQuestionBoard(param);
			System.out.println("list@servlet = " + list);
			
			int totalContent = questionboardService.selectTotalQuestionBoardCount();
			String url = request.getRequestURI(); 
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
			System.out.println("pagebar@servlet = " + pagebar);
			
			// 3. view단 처리
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request
				.getRequestDispatcher("/WEB-INF/views/community/questionboardList.jsp")
				.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
