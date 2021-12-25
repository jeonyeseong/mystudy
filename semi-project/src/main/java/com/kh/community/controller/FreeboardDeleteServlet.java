package com.kh.community.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.community.model.service.FreeboardService;

/**
 * Servlet implementation class FreeboardDeleteServlet
 */
@WebServlet("/community/freeboardDelete")
public class FreeboardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FreeboardService freeboardService = new FreeboardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			int no = Integer.parseInt(request.getParameter("no"));

			int result = freeboardService.deleteFreeBoard(no);
			String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";

			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/community/freeboardList");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	
	
	}

}
