package com.kh.community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.exception.FreeboardException;
import com.kh.community.model.service.FreeboardService;
import com.kh.community.model.vo.Freeboard;
import com.kh.community.model.vo.FreeboardComment;

/**
 * Servlet implementation class FreeboardViewServlet
 */
@WebServlet("/community/freeboardView")
public class FreeboardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FreeboardService freeboardService = new FreeboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.파라미터핸들링
			int no  = Integer.parseInt(request.getParameter("no"));
			
			//조회수
			Cookie[] cookies = request.getCookies();
			boolean hasRead = false;
			String freeboardCookieVal = "";
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if("freeboardCookie".equals(name)) {
						freeboardCookieVal = value; // 기존쿠키값
						if(value.contains("[" + no + "]")) {
							hasRead = true;
							break;
						}
					}
				}
			}
			if(!hasRead) {
				int result = freeboardService.updateReadCount(no);
				
				Cookie cookie = new Cookie("freeboardCookie", freeboardCookieVal + "[" + no + "]");
				cookie.setPath(request.getContextPath() + "/community/freeboardView");
				cookie.setMaxAge(1 * 24 * 60 * 60);
				response.addCookie(cookie);
				
				System.out.println("[FreeboardViewServlet] 조회수 증가 및 boardCookie 생성");
				
			}
			
			
			
			Freeboard freeboard = freeboardService.selectOneFreeBoard(no);
			System.out.println("[FreeboardViewServlet] freeboard = " + freeboard);
			//게시글 가져오기에 실패한경우
			if(freeboard == null) {
				throw new FreeboardException("해당 게시글이 존재하지 않습니다.");
			}
			
			// XSS공격대비 <> 변환처리
			String content = freeboard.getContent().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			
			// 개행문자처리
			content = content.replaceAll("\n", "<br/>");
			freeboard.setContent(content);
			
			// 댓글 목록 조회
			List<FreeboardComment> commentList = freeboardService.selectFreeBoardCommentList(no);
			System.out.println("[FreeboardViewServlet] commentList = " + commentList);
			
			
			//3. jsp forwarding
			request.setAttribute("commentList", commentList);
			request.setAttribute("freeboard", freeboard);
			request.getRequestDispatcher("/WEB-INF/views/community/freeboardView.jsp")
				   .forward(request, response);
		} catch (NumberFormatException e) {
			throw new FreeboardException("유효한 게시글 번호가 아닙니다.", e);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;

		}
	}

}
