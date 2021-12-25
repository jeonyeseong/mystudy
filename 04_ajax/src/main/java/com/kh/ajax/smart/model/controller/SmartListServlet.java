package com.kh.ajax.smart.model.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.ajax.smart.model.service.SmartService;
import com.kh.ajax.smart.model.vo.SmartPhone;

/**
 * Servlet implementation class SmartListServlet
 */
@WebServlet("/smart/smartList.do")
public class SmartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SmartService smartService = new SmartService();

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			System.out.println("smartlistServlet");
			final int start = 1;
			final int end = 5;
			
			System.out.println("service시작");
			List<SmartPhone> list = smartService.selectAllSmart(start, end);
			System.out.println("service끝");
			System.out.println("[smartListServlet] = " + list);
			
			
			
			
			
			
			
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(list, response.getWriter());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
