package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.studygroup.model.service.StudyGroupService;
import com.kh.studygroup.model.vo.StudyGroup;

/**
 * Servlet implementation class BoardFormServlet
 */
@WebServlet("/board/boardForm")
public class BoardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupService groupService = new StudyGroupService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//HttpSession session = request.getSession();
		HttpSession session = request.getSession(true);
				
				
		// timeout설정 - web.xml 설정보다 우선순위가 높다.
		session.setMaxInactiveInterval(10*60); // 초단위
				
		Member loginMember = (Member) session.getAttribute("loginMember");	
		int GroupNo = loginMember.getStudy_group();
		StudyGroup studygroup = groupService.selectOneGroup(GroupNo);
		
		
		
		request.setAttribute("studyGroup", studygroup);
		request
			.getRequestDispatcher("/WEB-INF/views/board/boardForm.jsp")
			.forward(request, response);
	}


}
