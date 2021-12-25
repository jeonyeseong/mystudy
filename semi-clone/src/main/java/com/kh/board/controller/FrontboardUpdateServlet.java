package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.FrontboardService;
import com.kh.board.model.vo.Frontboard;
import com.kh.community.model.vo.Freeboard;

/**
 * Servlet implementation class FrontboardUpdateServlet
 */
@WebServlet("/board/frontboardUpdate")
public class FrontboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FrontboardService frontboardService = new FrontboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		Frontboard frontboard = frontboardService.selectOneBoard(no);
		System.out.println("[frontboardUpdateServlet] frontboard = " + frontboard);
		
		request.setAttribute("frontboard", frontboard);
		request
			.getRequestDispatcher("/WEB-INF/views/board/frontboardUpdate.jsp")
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
		
		Frontboard frontboard = new Frontboard(no, title, writer, content, 0, null, no);
		
		int result = frontboardService.updateFrontBoard(frontboard);
		System.out.println("[FrontboardUpdateServlet] result = " + result);
		String msg = result > 0 ? "게시물 수정 성공!" : "게시물 수정 실패!";
		
		// 3.redirect: DML은 redirect해서 url을 변경한다.
		// location: /mvc/board/boardList
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/board/frontboardView?no=" + frontboard.getNo();
		response.sendRedirect(location);
	}

}
