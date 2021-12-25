package com.kh.studygroup.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.studygroup.model.service.StudyGroupService;

/**
 * Servlet implementation class GroupApplicantServlet
 */
@WebServlet("/studygroup/applicant")
public class GroupApplicantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupService groupService = new StudyGroupService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//HttpSession session = request.getSession();
		HttpSession session = request.getSession(true);
						
		Member loginMember = (Member) session.getAttribute("loginMember");	
		String leaderId = loginMember.getMember_id();
		
		List<Member> list = groupService.applicantList(leaderId);
		
		
		request.setAttribute("list", list);
		
		request
		.getRequestDispatcher("/WEB-INF/views/group/groupApplicant.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
