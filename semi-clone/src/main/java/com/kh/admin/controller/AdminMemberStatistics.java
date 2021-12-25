package com.kh.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.vo.Statistics;
import com.kh.common.MvcUtils;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberStatistics
 */
@WebServlet("/admin/memberStatistics")
public class AdminMemberStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 2.업무로직
		List<Statistics> stat = memberService.languageStatistics();
		System.out.println("stat@servlet = " + stat);
		
		// 3.view단처리
		request.setAttribute("stat", stat);
		request
			.getRequestDispatcher("/WEB-INF/views/admin/statistics.jsp")
			.forward(request, response);
	}

}
