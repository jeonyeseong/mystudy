package com.kh.board.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.exception.FrontboardException;
import com.kh.board.model.service.FrontboardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Frontboard;
import com.kh.common.MvcFileRenamePolicy;
import com.kh.common.MvcUtils;
import com.kh.member.model.vo.Member;
import com.kh.studygroup.model.service.StudyGroupService;
import com.kh.studygroup.model.vo.StudyGroup;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class FrontboardEnrollServlet
 */
@WebServlet("/board/boardEnroll")
public class FrontboardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FrontboardService frontboardService = new FrontboardService();
	private StudyGroupService groupService = new StudyGroupService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			try {
				request.setCharacterEncoding("utf-8");
				
				String title = request.getParameter("title");
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				
			

				
				//----------------------- 스터디 그룹 정보 가져오기----------------------------------
				//HttpSession session = request.getSession();
				HttpSession session = request.getSession(true);
								
				Member loginMember = (Member) session.getAttribute("loginMember");	
				int GroupNo = loginMember.getStudy_group();
				StudyGroup studyGroup = groupService.selectOneGroup(GroupNo);
				Frontboard frontboard = new Frontboard(0, title, writer, content, 0, null, GroupNo);
				
				int result = frontboardService.insertBoard(frontboard);
				//---------------------------------------------------------------------------
				
				System.out.println("[FrontBoardEnrollServlet] result = " + result);
				String msg = result > 0 ? "게시물 등록 성공" : "게시물 등록 실패";
				
				request.getSession().setAttribute("msg", msg);
				request.setAttribute("studyGroup", studyGroup);
				String location = request.getContextPath() + "/board/boardList";
				response.sendRedirect(location);
			} catch (IOException e) {
				throw e;
			}
	}

}