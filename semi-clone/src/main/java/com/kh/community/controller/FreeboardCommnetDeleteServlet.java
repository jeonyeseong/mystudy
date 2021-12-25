package com.kh.community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.FreeboardService;

/**
 * Servlet implementation class FreeboardCommnetDeleteServlet
 */
@WebServlet("/community/freeboardCommentDelete")
public class FreeboardCommnetDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FreeboardService freeboardService = new FreeboardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("boardNo=" + boardNo + ", no = " + no);

		int result = freeboardService.deleteFreeBoardComment(no);
		String msg = (result > 0) ? "댓글 삭제 성공" : "댓글 삭제 실패";	
		
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/community/freeboardView?no=" + boardNo;
		response.sendRedirect(location);
	}

}
