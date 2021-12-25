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
import com.kh.studygroup.model.vo.StudyGroupMember;

/**
 * Servlet implementation class GroupAdministrateServlet
 */
@WebServlet("/studygroup/administrate")
public class GroupAdministrateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupService groupService = new StudyGroupService();
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 인코딩 처리    필터 만들면 지우기!!!!!
		request.setCharacterEncoding("utf-8");
		
		
		// 1. 사용자 입력처리
		
		//HttpSession session = request.getSession();
		HttpSession session = request.getSession(true);
				
				
		// timeout설정 - web.xml 설정보다 우선순위가 높다.
		session.setMaxInactiveInterval(10*60); // 초단위
				
		Member loginMember = (Member) session.getAttribute("loginMember");	
		int studyGroup = loginMember.getStudy_group();
		
		
		// 2. 업무 로직
		List<StudyGroupMember> MemberList = groupService.selectAllGroupMember(studyGroup);
		request.setAttribute("MemberList", MemberList);
		
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/group/groupAdministrate.jsp")
			.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 0. 인코딩 처리    필터 만들면 지우기!!!!!
		request.setCharacterEncoding("utf-8");
		
		
		
		String memberId = request.getParameter("memberId");
		int studyGroup = Integer.parseInt(request.getParameter("studyGroup"));
		
		
		int result = groupService.deleteGroupMember(studyGroup,memberId);
		
	
		
		String location = request.getContextPath() + "/studygroup/administrate";
		response.sendRedirect(location);
		
	}

}
