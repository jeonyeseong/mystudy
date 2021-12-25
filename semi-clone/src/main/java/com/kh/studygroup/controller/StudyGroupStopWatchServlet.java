package com.kh.studygroup.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.studygroup.model.service.StudyGroupService;

/**
 * Servlet implementation class StudyGroupStopWatch
 */
@WebServlet("/studygroup/stopwatch")
public class StudyGroupStopWatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupService groupService = new StudyGroupService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request
		.getRequestDispatcher("/WEB-INF/views/group/studyGroupStopWatch.jsp")
		.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 0. 인코딩 처리    필터 만들면 지우기!!!!!
		request.setCharacterEncoding("utf-8");

		
		// 1. 사용자 입력값 처리
		String time = request.getParameter("stringTime");
		String memberId = request.getParameter("memberId");
		
		String[] sptime = time.split(":");
		
		int th = Integer.parseInt(sptime[0]);
		int tm = Integer.parseInt(sptime[1]);
		int ts = Integer.parseInt(sptime[2]);
		
		// 2. 업무 로직
		int result = 0;
		HttpSession session = request.getSession(true);
		String dbtime = groupService.selectStudyTime(memberId);
		
		if(dbtime == null) {
			result = groupService.insertStudyTime(memberId,time);
			String msg = result > 0 ? "공부 시간 등록 성공!" : "공부 시간 등록 실패!";
			session.setAttribute("msg", msg);
			
		}
		else {
			String[] dbtimelist = dbtime.split(":");
			int db_th = Integer.parseInt(dbtimelist[0]);
			int db_tm = Integer.parseInt(dbtimelist[1]);
			int db_ts = Integer.parseInt(dbtimelist[2]);
			
			th = (th + db_th) + (int) Math.floor((tm + db_tm) / 60);
			tm = (tm + db_tm) % 60 + (int) Math.floor((ts + db_ts) / 60);
			ts = (ts + db_ts) % 60;
			
			String str_th = th < 10 ? "0" + th : "" + th;
			String str_tm = tm < 10 ? "0" + tm : "" + tm;
			String str_ts = ts < 10 ? "0" + ts : "" + ts;
			
			String result_time = str_th + ":" + str_tm + ":" + str_ts;
			
			result = groupService.insertStudyTime(memberId, result_time);
			
			
			
		}
		
		
		
		// 3. view 단 제공
		String location = request.getContextPath() + "/studygroup/view";
		response.sendRedirect(location);
	
		
	}

}
