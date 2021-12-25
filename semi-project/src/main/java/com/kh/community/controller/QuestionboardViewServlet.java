package com.kh.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.exception.QuestionboardException;
import com.kh.community.model.service.QuestionboardService;
import com.kh.community.model.vo.Questionboard;
import com.kh.community.model.vo.QuestionboardComment;

/**
 * Servlet implementation class QuestionboardViewServlet
 */
@WebServlet("/community/questionboardView")
public class QuestionboardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionboardService questionboardService = new QuestionboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.파라미터핸들링
			int no  = Integer.parseInt(request.getParameter("no"));
			
			//2.업무로직
			// 상세보기를 요청하면, 해당글에 대한 boardCookie가 존재하지 않을때 조회수를 1증가한다. 
			// a.검사
			Cookie[] cookies = request.getCookies();
			boolean hasRead = false;
			String questionboardCookieVal = "";
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if("questionboardCookie".equals(name)) {
						questionboardCookieVal = value;
						if(value.contains("[" + no + "]")) {
							hasRead = true;
							break;
						}
					}
				}
			}
			// b.조회수 증가 및 쿠키생성
			if(!hasRead) {
				int result = questionboardService.updateReadCount(no);
				
				Cookie cookie = new Cookie("questionboardCookie", questionboardCookieVal + "[" + no + "]");
				cookie.setPath(request.getContextPath() + "/community/questionboardView");
				cookie.setMaxAge(1 * 24 * 60 * 60);
				response.addCookie(cookie);
				
				System.out.println("[QuestionboardViewServlet] 조회수 증가 및 questionboardCookie 생성");
				
			}
			
			
			Questionboard questionboard = questionboardService.selectOneQuestionBoardAttachements(no);
			System.out.println("[QuestionboardViewServlet] questionboard = " + questionboard);
			//게시글 가져오기에 실패한경우
			if(questionboard == null) {
				throw new QuestionboardException("해당 게시글이 존재하지 않습니다.");
			}
			
			// XSS공격대비 <> 변환처리
			String content = questionboard.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			
			// 개행문자처리
			content = content.replaceAll("\n", "<br/>");
			questionboard.setContent(content);

			// 댓글 목록 조회
			List<QuestionboardComment> commentList = questionboardService.selectQuestionBoardCommentList(no);
			System.out.println("[QuestionboardViewServlet] commentList = " + commentList);
			
			//3. jsp forwarding
			request.setAttribute("commentList", commentList);
			request.setAttribute("questionboard", questionboard);
			request.getRequestDispatcher("/WEB-INF/views/community/questionboardView.jsp")
				   .forward(request, response);
		} catch(NumberFormatException e) {
			throw new QuestionboardException("유효한 게시글 번호가 아닙니다.", e);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


}
