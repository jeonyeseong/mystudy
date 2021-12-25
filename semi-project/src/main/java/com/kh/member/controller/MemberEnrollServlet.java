package com.kh.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * GET /mvc/member/memberEnroll
	 * 
	 * - 회원가입폼을 제공
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
			.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp")
			.forward(request, response);
		
	}

	/**
	 * POST /mvc/member/memberEnroll
	 * 
	 * - DB에 레코드 기록
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1.인코딩처리 utf-8
			request.setCharacterEncoding("utf-8");
			String location="";
			
			// 2.사용자입력값 처리 사용자입력값 -> Member VO객체 생성
			String memberId = request.getParameter("memberId");
			String password = request.getParameter("password");
			String memberName = request.getParameter("memberName");
			String gender = request.getParameter("gender");
			String email01 = request.getParameter("email01");
			String selectEmail = request.getParameter("selectEmail");
			String email = email01+selectEmail;
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String[] _language = request.getParameterValues("language");
			
			System.out.println("email = " + email);
			System.out.println("_language = " + _language);
			
			
			String language = (_language != null) ? String.join(",", _language) : "";
			
			Member member = new Member(memberId, password, memberName, MemberService.USER_ROLE, gender, language, email, phone, address, null,0);
			System.out.println("member@servlet = " + member);
			
			// 3.업무로직 service객체의 insertMember호출 & 생성한 member객체 전달
			int result = memberService.insertMember(member);
			String msg = result > 0 ? "회원가입성공!" : "회원가입실패!";
			
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);
			if(result >0) {
				location = request.getContextPath()+"/board/boardList";
			}
			else {
				
				location = request.getContextPath() + "/";
			}
			// 4.redirect 및 msg처리
			response.sendRedirect(location);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e; // tomcat이 error.jsp로 위임하도록 처리
		}
	}

}
