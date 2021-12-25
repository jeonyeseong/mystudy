package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.QuestionboardService;
import com.kh.community.model.vo.QuestionboardComment;

/**
 * Servlet implementation class QuestionboardCommnetEnrollServlet
 */
@WebServlet("/community/questionboardCommentEnroll")
public class QuestionboardCommnetEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionboardService questionboardService = new QuestionboardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.valueOf(request.getParameter("boardNo"));
		int commentLevel = Integer.valueOf(request.getParameter("commentLevel"));
		int commentRef = Integer.valueOf(request.getParameter("commentRef"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		QuestionboardComment bc = new QuestionboardComment(0, commentLevel, writer, content, boardNo, commentRef, null);
		System.out.println("[QuestionboardCommentEnrollServlet] bc = " + bc);
		
		
		int result = questionboardService.insertQuestionBoardComment(bc);
		String msg = result > 0 ? "댓글 등록 성공" : "댓글 등록 실패";
		request.getSession().setAttribute("msg", msg);
		
		
		String location = request.getContextPath() + "/community/questionboardView?no=" + boardNo;
		response.sendRedirect(location);
	}

}
