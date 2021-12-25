package com.kh.studygroup.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.MvcUtils;
import com.kh.studygroup.board.model.service.StudyGroupBoardService;
import com.kh.studygroup.board.model.vo.Attachment;
import com.kh.studygroup.board.model.vo.StudyGroupBoard;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class GroupBoardEnroll
 */
@WebServlet("/studygroup/boardEnroll")
public class GroupBoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupBoardService service = new StudyGroupBoardService();   
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		
		// 1. 사용자 입력값받기
		try {
			
			// A. server computer에 사용자 업로드 파일 저장
			String saveDirectory = getServletContext().getRealPath("/upload/group");
			int maxPostSize = 1024 * 1024 * 10;	// 10MB
			String encoding = "utf-8";
			
			// 파일명 재지정 정책 객체 : 
			// DefaultFileRenamePolicy 동일한 이름의 파일은 numbering을 통해 overwrite을 방지
			
			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			
			
			MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			
			
			
			int group = Integer.parseInt(multipartRequest.getParameter("group"));
			String title = multipartRequest.getParameter("title");
			String writer = multipartRequest.getParameter("writer");
			String content = multipartRequest.getParameter("content");
			
			StudyGroupBoard board = new StudyGroupBoard(0,group,title,writer,content,0);
			
			
			// 저장된 파일정보 -> Attachment객체 생성 .-> List<Attachment>객체에 추가 -> Board객체에 추가
			File upFile1 = multipartRequest.getFile("upFile1");
			File upFile2 = multipartRequest.getFile("upFile2");
			
			if(upFile1 != null || upFile2 != null) {
				List<Attachment> attachments = new ArrayList<>();
				
				
				// 현재 fk인 boardNo필드값은 비어있다.
				if(upFile1 != null) {
					Attachment attach1 = MvcUtils.makeAttachment(multipartRequest,"upFile1");
					attachments.add(attach1);
				}
				if(upFile2 != null) {
					Attachment attach2 = MvcUtils.makeAttachment(multipartRequest,"upFile2");
					attachments.add(attach2);
				}
				
				board.setAttachments(attachments);
				System.out.println("[BoardEnrollServlet] attachments = " + attachments);
				
			}
			
			
			
			// 2. 업무로직
			
			int result = service.groupBoardEnroll(board);
			
			// 3. redirect : DML은 redirect해서 url을 변경한다.
			
			String location = request.getContextPath() + "/studygroup/boardlist";
			response.sendRedirect(location);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
