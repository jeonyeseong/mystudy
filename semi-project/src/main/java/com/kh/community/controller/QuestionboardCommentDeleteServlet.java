package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.QuestionboardService;

/**
 * Servlet implementation class QuestionboardCommentDeleteServlet
 */
@WebServlet("/community/questionboardCommentDelete")
public class QuestionboardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionboardService questionboardService = new QuestionboardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("boardNo=" + boardNo + ", no = " + no);

		//2. 비지니스로직 호출
		int result = questionboardService.deleteQuestionBoardComment(no);
		String msg = (result > 0) ? "댓글 삭제 성공" : "댓글 삭제 실패";	
		
		//3. 받은 결과에 따라 view단 처리위임.
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/community/questionboardView?no=" + boardNo;
		response.sendRedirect(location);
	}

}
