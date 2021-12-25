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

/**
 * Servlet implementation class GroupApplicantCheckServlet
 */
@WebServlet("/studygroup/applicantCheck")
public class GroupApplicantCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupService groupService = new StudyGroupService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 사용자 입력값
		String memberId = request.getParameter("memberId");
		int temp = Integer.parseInt(request.getParameter("temp"));
		int result;
		System.out.println("memberID - " + memberId );
		//HttpSession session = request.getSession();
		HttpSession session = request.getSession(true);
						
		Member loginMember = (Member) session.getAttribute("loginMember");	
		int studyGroup = loginMember.getStudy_group();
		String leaderId = loginMember.getMember_id();
		leaderId = leaderId.trim();
		// 업무로직
		if(temp == 1) {

			result = groupService.deleteApplicant(leaderId,memberId);
	
			Member member = groupService.selectOneMember(memberId);

			result = groupService.updateApplicant(studyGroup,member);
	
			if(result > 0) {
				result = groupService.updateNowMember(studyGroup);
			}
			String msg = result > 0 ? "스터디 그룹 멤버 등록 성공" : "스터디 그룹 멤버 등록 실패";
			session.setAttribute("msg", msg);
			
		}
		else {
			groupService.deleteApplicant(leaderId,memberId);
		}
		
		String location = request.getContextPath() + "/studygroup/applicant";
		response.sendRedirect(location);
		
		
		
	}

}
