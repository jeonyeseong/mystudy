package com.kh.studygroup.controller;

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
 * Servlet implementation class StudyGroupCreate
 */
@WebServlet("/studygroup/create")
public class StudyGroupCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupService groupService = new StudyGroupService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		// 0. 인코딩 처리    필터 만들면 지우기!!!!!
		request.setCharacterEncoding("utf-8");
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/group/studyGroupCreate.jsp")
			.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 인코딩 처리    필터 만들면 지우기!!!!!
		request.setCharacterEncoding("utf-8");
		
		// 1. 사용자 입력값 처리
		int grouoNo = 0;
		
		String group_name = request.getParameter("groupName");
		int max_member = Integer.parseInt(request.getParameter("max_member"));
		int now_member = 1;
		String status = request.getParameter("status");
		String on_off = request.getParameter("on/off");
		String area = request.getParameter("area");
		String language = request.getParameter("language");
		
		StudyGroup group = new StudyGroup(group_name,max_member,1,status,area,language,on_off);
		System.out.println("[StudyGroupCreate@Servlet] group = " + group);
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		Member member = new Member(memberId,memberName,0);
		
		// 2. 업무로직
		
		
		grouoNo = groupService.InsertGroup(group,member);
		
		//HttpSession session = request.getSession();
		HttpSession session = request.getSession(true);
				
				
		// timeout설정 - web.xml 설정보다 우선순위가 높다.
		session.setMaxInactiveInterval(10*60); // 초단위
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		session.removeAttribute("loginMember");
		
		
		loginMember.setStudy_group(grouoNo);
		
		
		

		String msg = grouoNo > 0 ? "스터디 그룹 생성 완료" : "스터디 그룹 생성 실패";
		request.getSession().setAttribute("msg", msg);
		session.setAttribute("loginMember", loginMember);
		
		// 3. view 단
		String location = request.getContextPath() + "/studygroup/view";
		response.sendRedirect(location);
		
	}

}
