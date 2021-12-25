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
 * Servlet implementation class GroupBoardUpdateServlet
 */
@WebServlet("/studygroup/boardUpdate")
public class GroupBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupBoardService service = new StudyGroupBoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		StudyGroupBoard board = service.SelectOneBoardAttachments(no);
		
		request.setAttribute("board", board);
		
		request
			.getRequestDispatcher("/WEB-INF/views/group/studyGroupBoardUpdate.jsp")
			.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			// A. server computer에 사용자 업로드 파일 저장
			String saveDirectory = getServletContext().getRealPath("/upload/group");
			int maxPostSize = 1024 * 1024 * 10;	// 10MB
			String encoding = "utf-8";
			
			// 파일명 재지정 정책 객체 : 
			// DefaultFileRenamePolicy 동일한 이름의 파일은 numbering을 통해 overwrite을 방지
			
			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			
			
			MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			
			
			int no = Integer.parseInt(multipartRequest.getParameter("no"));
			int group = Integer.parseInt(multipartRequest.getParameter("group"));
			String title = multipartRequest.getParameter("title");
			String writer = multipartRequest.getParameter("writer");
			String content = multipartRequest.getParameter("content");
			String[] delFiles = multipartRequest.getParameterValues("delFile");
			
			
			StudyGroupBoard board = new StudyGroupBoard(no,group,title,writer,content,0);
			
			
			// 저장된 파일정보 -> Attachment객체 생성 .-> List<Attachment>객체에 추가 -> Board객체에 추가
			File upFile1 = multipartRequest.getFile("upFile1");
			File upFile2 = multipartRequest.getFile("upFile2");
			
			if(upFile1 != null || upFile2 != null) {
				List<Attachment> attachments = new ArrayList<>();
				
				
				// 현재 fk인 boardNo필드값은 비어있다.
				if(upFile1 != null) {
					Attachment attach1 = MvcUtils.makeAttachment(multipartRequest,"upFile1");
					attach1.setBoard_No(no);
					attachments.add(attach1);
				}
				if(upFile2 != null) {
					Attachment attach2 = MvcUtils.makeAttachment(multipartRequest,"upFile2");
					attach2.setBoard_No(no);
					attachments.add(attach2);
				}
				
				board.setAttachments(attachments);
				System.out.println("[BoardEnrollServlet] attachments = " + attachments);
				
			}
			
			// 2. 업무로직
			
			// a. 기존 첨부파일 삭제
			if(delFiles != null) {
				for(String temp : delFiles) {
					int delFileNo = Integer.parseInt(temp);
					//가. 첨부파일 삭제
					Attachment attach = service.selectOneAttachment(delFileNo);
					String renamedFilename = attach.getRenamed_filename();
					File delFile = new File(saveDirectory, renamedFilename);
					
					delFile.delete();
					
					//나. DB 첨부파일 레코드 삭제
					int result = service.deleteAttachment(delFileNo);
					
					
					
				}
			}
			// b. db레코드 수정
			
			int result = service.updateGroupBoard(board);
			
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
