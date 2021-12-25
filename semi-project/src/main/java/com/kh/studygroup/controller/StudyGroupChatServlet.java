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
 * Servlet implementation class StudyGroupChat
 */
@WebServlet("/studygroup/chat")
public class StudyGroupChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupService groupService = new StudyGroupService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩 처리    필터 만들면 지우기!!!!!
		request.setCharacterEncoding("utf-8");
		
		//HttpSession session = request.getSession();
		HttpSession session = request.getSession(true);
				
				
		// timeout설정 - web.xml 설정보다 우선순위가 높다.
		session.setMaxInactiveInterval(10*60); // 초단위
				
		Member loginMember = (Member) session.getAttribute("loginMember");	
		int studyGroup = loginMember.getStudy_group();
		
		List<StudyGroupMember> MemberList = groupService.selectAllGroupMember(studyGroup);
		StringBuilder sb = new StringBuilder();
		int count = 1;
		for(StudyGroupMember member : MemberList){
			sb.append(member.getGroupMemberId()); 
			if(count != MemberList.size()) {
				sb.append(",");
			}
			count++;
		}
		String str = sb.toString();
		
		request.setAttribute("arrId", str);
		
		
		request
		.getRequestDispatcher("/WEB-INF/views/group/studyGroupChatRoom.jsp")
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
