package com.kh.studygroup.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.MvcUtils;
import com.kh.member.model.vo.Member;
import com.kh.studygroup.board.model.service.StudyGroupBoardService;
import com.kh.studygroup.board.model.vo.StudyGroupBoard;

/**
 * Servlet implementation class StudyGroupBoardListServlet
 */
@WebServlet("/studygroup/boardlist")
public class StudyGroupBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupBoardService service = new StudyGroupBoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 1. 사용자입력값
			final int numPerPage = 5;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			}catch(NumberFormatException e) {}
			
			int startNum = (cPage - 1) * numPerPage + 1;
			int endNum = cPage * numPerPage;
		
			
			Map<String, Integer> param = new HashMap<>();
			param.put("startNum", startNum);
			param.put("endNum", endNum);
			
			HttpSession session = request.getSession(true);

			Member loginMember = (Member) session.getAttribute("loginMember");		
			
			int group = loginMember.getStudy_group();
			
			// 2. 업무로직
			
			
			// 2-a. content 영역 : 페이징 쿼리
			List<StudyGroupBoard> list = service.SelectAllBoard(param,group);
			
			// 2-b. pagebar 영역 : MvcUtils.getPagebar호출
			// totalContent
			// url
			int totalContent = service.selectTotalBoardCount();
			
			String url = request.getRequestURI();
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContent, url);

			// 3. view단 처리
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			
			request
				.getRequestDispatcher("/WEB-INF/views/group/studyGroupBoardList.jsp")
				.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace(); // 로깅
			throw e; // WAS에게 예외를 전달해서 예외페이지로 이동하도록 함.
			
		} 
		
		
		

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
