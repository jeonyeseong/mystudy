package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.FrontboardService;
import com.kh.board.model.vo.FrontboardComment;

/**
 * Servlet implementation class FrontboardCommentEnroll
 */
@WebServlet("/board/frontboardCommentEnroll")
public class FrontboardCommentEnroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FrontboardService frontboardService = new FrontboardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int boardNo = Integer.valueOf(request.getParameter("boardNo"));
		int commentLevel = Integer.valueOf(request.getParameter("commentLevel"));
		int commentRef = Integer.valueOf(request.getParameter("commentRef"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		FrontboardComment bc = new FrontboardComment(0, commentLevel, writer, content, boardNo, commentRef, null);
		System.out.println("[BoardCommentEnrollServlet] bc = " + bc);
		
		int result = frontboardService.insertFrontBoardComment(bc);
		String msg = result > 0 ? "댓글 등록 성공" : "댓글 등록 실패";
		request.getSession().setAttribute("msg", msg);
		
		String location = request.getContextPath() + "/board/frontboardView?no=" + boardNo;
		response.sendRedirect(location);
	}

}
