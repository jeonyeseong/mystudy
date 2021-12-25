package com.kh.mvc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mvc.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터값 가져오기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("boardNo=" + boardNo + ", no = " + no);

		//2. 비지니스로직 호출
		int result = boardService.deleteBoardComment(no);
		String msg = (result > 0) ? "댓글 삭제 성공!" : "댓글 삭제 실패!";	
		
		//3. 받은 결과에 따라 view단 처리위임.
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/board/boardView?no=" + boardNo;
		response.sendRedirect(location);
	}


}
