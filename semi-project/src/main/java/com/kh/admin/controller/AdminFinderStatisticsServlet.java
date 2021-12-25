package com.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.vo.Statistics;
import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class AdminFinderStatisticsServlet
 */
@WebServlet("/admin/finderStat")
public class AdminFinderStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String searchType = request.getParameter("searchType");
		System.out.println("searchType = " + searchType);
		
		List<Statistics> stat = memberService.Statistics(searchType);
		System.out.println("statfinder@servet stat = " + stat);
		
		String title="";
		switch(searchType) {
		case "language": title="선호 언어"; break; 
		case "enrolldate": title="가입일 통계"; break; 
		case "visitors": title="방문 수 통계"; break; 
		}
		
		request.setAttribute("stat", stat);
		request.setAttribute("title", title);
		request
		.getRequestDispatcher("/WEB-INF/views/admin/statistics.jsp")
		.forward(request, response);
		
	}

}
