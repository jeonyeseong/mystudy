package com.kh.studygroup.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.service.FrontboardService;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class StudyGroupApplyAlramServlet
 */
@WebServlet("/group/groupApply")
public class StudyGroupApplyAlramServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FrontboardService frontboardService = new FrontboardService();
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 인코딩 처리    필터 만들면 지우기!!!!!
		request.setCharacterEncoding("utf-8");
		
		
		HttpSession session = request.getSession(true);
		//memberservice에서 처리
		Member loginMember = (Member) session.getAttribute("loginMember");	
		
		String memberId = loginMember.getMember_id();
		String writer = (String) request.getParameter("writer");
		writer = writer.trim();
		
	
		int result = memberService.insertAlram(memberId, writer);

		
		String msg = result > 0 ? "신청 완료" : "신청 실패";
		session.setAttribute("msg", msg);
		
		String location = request.getHeader("Referer");
		System.out.println("location = "+ location);
		response.sendRedirect(location);

	}

}
