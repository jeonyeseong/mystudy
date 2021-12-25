package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.FreeboardService;
import com.kh.community.model.vo.Freeboard;


/**
 * Servlet implementation class FreeboardUpdateServlet
 */
@WebServlet("/community/freeboardUpdate")
public class FreeboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FreeboardService freeboardService = new FreeboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		Freeboard freeboard = freeboardService.selectOneFreeBoard(no);
		System.out.println("[freeboardUpdateServlet] freeboard = " + freeboard);
		
		request.setAttribute("freeboard", freeboard);
		request
			.getRequestDispatcher("/WEB-INF/views/community/freeboardUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Freeboard freeboard = new Freeboard(no, title, writer, content, 0, null, no);
		
		int result = freeboardService.updateFreeBoard(freeboard);
		System.out.println("[FreeboardUpdateServlet] result = " + result);
		String msg = result > 0 ? "게시물 수정 성공!" : "게시물 수정 실패!";
		
		// 3.redirect: DML은 redirect해서 url을 변경한다.
		// location: /mvc/board/boardList
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/community/freeboardView?no=" + freeboard.getNo();
		response.sendRedirect(location);
	}

}
