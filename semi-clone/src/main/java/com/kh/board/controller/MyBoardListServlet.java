package com.kh.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.FrontboardService;
import com.kh.board.model.vo.Frontboard;
import com.kh.community.model.service.FreeboardService;
import com.kh.community.model.service.QuestionboardService;
import com.kh.community.model.vo.Freeboard;
import com.kh.community.model.vo.MvcUtils;
import com.kh.community.model.vo.Questionboard;
import com.kh.member.model.vo.Member;
import com.kh.studygroup.board.model.service.StudyGroupBoardService;
import com.kh.studygroup.board.model.vo.StudyGroupBoard;

/**
 * Servlet implementation class MyBoardListServlet
 */
@WebServlet("/board/MyBoardList")
public class MyBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FrontboardService frontboardService = new FrontboardService();
	private FreeboardService freeboardService = new FreeboardService();
	private QuestionboardService questionboardService = new QuestionboardService();
	private StudyGroupBoardService service = new StudyGroupBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			final int numPerPage = 5;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {}
//			int start = (cPage - 1) * numPerPage + 1; 
//			int end = cPage * numPerPage;
			int start = 1; 
			int end = 10;
			Map<String, Integer> param = new HashMap<>();
			param.put("start", start);
			param.put("end", end);
			
			
			String memberId = request.getParameter("memberId");
			System.out.println("memberId = " + memberId);
			
			int studyNo = Integer.parseInt(request.getParameter("studyNo"));
			System.out.println("studyNo = " + studyNo);
			
			List<Frontboard> list = frontboardService.myboardlist(param,memberId);
			System.out.println("myboardList@servlet = " + list);
			
			List<Freeboard> free = freeboardService.selectMyFreeBoard(param,memberId);
			System.out.println("freeboard@servlet = " + free);
			
			List<Questionboard> QnA = questionboardService.selectMyQuestionBoard(param,memberId);
			System.out.println("QnA@servlet = " + QnA);
			
			List<StudyGroupBoard> studyboard = service.selectMyboardList(param,studyNo,memberId);
			System.out.println("studyboard@servlet = " + studyboard);
			
//			int totalContent = freeboardService.selectTotalFreeBoardCount();
//			String url = request.getRequestURI(); 
//			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContent, url);
//			System.out.println("pagebar@servlet = " + pagebar);
			
			request.setAttribute("list", list);
			request.setAttribute("free", free);
			request.setAttribute("QnA", QnA);
			request.setAttribute("studyboard", studyboard);
//			request.setAttribute("pagebar", pagebar);
			
			request
			.getRequestDispatcher("/WEB-INF/views/board/myboardList.jsp")
			.forward(request, response);
			
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
