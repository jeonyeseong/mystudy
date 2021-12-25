package com.kh.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.admin.controller.AdminMemberListServlet;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩 처리    필터 만들면 지우기!!!!!
		request.setCharacterEncoding("utf-8");
		
		
		// 1. 사용자 입력값 처리
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		String msg;
		String location;
		
		System.out.println("[MemberLogin@Servlet] memberId = " + memberId + ", password = " + password );
		
		
		// 2. 업무 로직
		Member member  = memberService.selectOneMember(memberId);
		
		
		System.out.println("[member@MemberLoginServlet] member = " + member);
		
		
		
		//HttpSession session = request.getSession();
		HttpSession session = request.getSession(true);
		
		
		// timeout설정 - web.xml 설정보다 우선순위가 높다.
		session.setMaxInactiveInterval(10*60); // 초단위
		
		
		
		if (member != null && password.equals(member.getPassword())) {
			
			
			
			// 로그인 성공
			session.setAttribute("msg", "로그인 성공!");
			session.setAttribute("loginMember", member);
			System.out.println("session.getId() = "+session.getId());
			location = request.getContextPath()+"/board/boardList";
//			location = request.getHeader("Referer");
			
			// 아이디저장 체크박스 처리
			// 브라우져 호환성을 고려해 도메인당 쿠키개수 50개, 하나의 value값은 4kb를 넘지 않도록 한다.
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath());
				
			if(saveId != null) {
				cookie.setMaxAge(7 * 24 * 60 * 60); // 7일
				// persistent(영속)쿠키 : 초단위로 시간을 입력
				// session 쿠키 : setMaxAge설정 안한 경우				
			}
			else {
				cookie.setMaxAge(0); // 즉시 삭제
			}
			response.addCookie(cookie);
			
			//방문자 통계
			int result = memberService.totalVisitor();
			System.out.println(result > 0 ? "방문자 기록 완료" : "방문자 기록 실패");
			
			
		}
		
		else {
			// 로그인 실패
			session.setAttribute("msg", "로그인 실패!");
			location = request.getHeader("Referer");
		}
		
		
		// 3. view단 제공
//		String location = request.getHeader("Referer");
		System.out.println("location = "+ location);
		response.sendRedirect(location);
		
		
	}




}
