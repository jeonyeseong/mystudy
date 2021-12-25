package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.FrontboardService;

/**
 * Servlet implementation class FrontboardCommentDelete
 */
@WebServlet("/board/frontboardCommentDelete")
public class FrontboardCommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FrontboardService frontboardService = new FrontboardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("boardNo=" + boardNo + ", no = " + no);

		int result = frontboardService.deleteFrontBoardComment(no);
		String msg = (result > 0) ? "댓글 삭제 성공" : "댓글 삭제 실패";	
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/board/frontboardView?no=" + boardNo;
		response.sendRedirect(location);
	}

}
