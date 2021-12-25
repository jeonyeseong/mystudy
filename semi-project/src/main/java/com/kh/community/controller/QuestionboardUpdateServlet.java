package com.kh.community.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.MvcFileRenamePolicy;
import com.kh.community.model.service.QuestionboardService;
import com.kh.community.model.vo.Attachment;
import com.kh.community.model.vo.MvcUtils;
import com.kh.community.model.vo.Questionboard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class QuestionboardUpdateServlet
 */
@WebServlet("/community/questionboardUpdate")
public class QuestionboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionboardService questionboardService = new QuestionboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		// 2.업무로직
		Questionboard questionboard = questionboardService.selectOneQuestionBoard(no);
		System.out.println("[QuestionboardUpdateServlet] questionboard = " + questionboard);
		
		// 3.view단처리
		request.setAttribute("questionboard", questionboard);
		request
			.getRequestDispatcher("/WEB-INF/views/community/questionboardUpdate.jsp")
			.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// A. server computer에 사용자 업로드파일 저장
			String saveDirectory = getServletContext().getRealPath("/upload/board");
			System.out.println("[BoardUpdateServlet] saveDirectory = " + saveDirectory);
			
			int maxPostSize = 1024 * 1024 * 10; // 10MB 
			String encoding = "utf-8";
			
			// 파일명 재지정 정책 객체 
			// DefaultFileRenamePolicy 동일한 이름의 파일은 numbering을 통해 overwrite을 방지
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			
			MultipartRequest multipartRequest = 
					new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			
			// B. 업로드한 파일 정보를 db에 저장 : attachment테이블 파일하나당 1행 저장
			
			
			// 1.사용자입력값 -> Board객체
			// MultipartRequest객체 생성하는 경우, 기존 request가 아닌 MultipartRequest객체에서 값을 가져와야 한다.
			int no = Integer.parseInt(multipartRequest.getParameter("no"));
			String title = multipartRequest.getParameter("title");
			String writer = multipartRequest.getParameter("writer");
			String content = multipartRequest.getParameter("content");
			String[] delFiles = multipartRequest.getParameterValues("delFile");
			
			Questionboard questionboard = new Questionboard(no, title, writer, content, 0, null, null, no);
			
			
			// 저장된 파일정보 -> Attachment객체 생성 -> List<Attachment>객체에 추가 -> Board객체에 추가
			Enumeration fileNames = multipartRequest.getFileNames();
			List<Attachment> attachments = new ArrayList<>();
			while(fileNames.hasMoreElements()) {
				String fileName = (String) fileNames.nextElement();
				System.out.println("[QuestionboardUpdateServlet] fileName = " + fileName);
				File upFile = multipartRequest.getFile(fileName);
				if(upFile != null) {
					Attachment attach = MvcUtils.makeAttachment(multipartRequest, fileName);
					attach.setBoardNo(no);
					attachments.add(attach);
				}
			}
			if(!attachments.isEmpty())
				questionboard.setAttachments(attachments);
			
			System.out.println("[QuestionboardUpdateServlet] questionboard = " + questionboard);
			
			// 2.업무로직
			// a. 기존첨부파일 삭제
			if(delFiles != null) {
				for(String temp : delFiles) {
					int delFileNo = Integer.parseInt(temp);
					Attachment attach = questionboardService.selectOneAttachment(delFileNo);

					//가. 첨부파일 삭제 : {saveDirectory}/{renamedFilename}
					String renamedFilename = attach.getRenamedFilename();
					File delFile = new File(saveDirectory, renamedFilename);
					boolean removed = delFile.delete();
					
					//나. DB 첨부파일 레코드 삭제
					int result = questionboardService.deleteAttachment(delFileNo);
					
					System.out.println("[QuestionboardUpdateServlet] " + renamedFilename + " 삭제 : " + removed);
					System.out.println("[QuestionboardUpdateServlet] " + renamedFilename + " 레코드 삭제 : " + removed);
					
				}
			}
			
			
			
			// b. db 레코드 수정(update board + insert attachment)
			int result = questionboardService.updateQuestionBoard(questionboard);
			System.out.println("[QuestionboardUpdateServlet] result = " + result);
			String msg = result > 0 ? "게시물 수정 성공!" : "게시물 수정 실패!";
			
			// 3.redirect: DML은 redirect해서 url을 변경한다.
			// location: /mvc/board/boardList
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/community/questionboardView?no=" + questionboard.getNo();
			response.sendRedirect(location);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
