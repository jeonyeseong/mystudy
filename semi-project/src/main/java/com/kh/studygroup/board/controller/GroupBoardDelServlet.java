package com.kh.studygroup.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.studygroup.board.model.service.StudyGroupBoardService;
import com.kh.studygroup.board.model.vo.Attachment;

/**
 * Servlet implementation class GroupBoardDelServlet
 */
@WebServlet("/studygroup/boardDel")
public class GroupBoardDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupBoardService service = new StudyGroupBoardService();   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			int no = Integer.parseInt(request.getParameter("no"));
			//2. 업무로직
			// a. 업로드 파일 삭제 
			List<Attachment> attachments = service.selectAttachmentByBoard(no);
			if(attachments != null && !attachments.isEmpty()) {
				String saveDirectory = getServletContext().getRealPath("/upload/group");
				for(Attachment attach : attachments) {
					String renamedFilename = attach.getRenamed_filename();
					File delFile = new File(saveDirectory, renamedFilename);
					boolean deleted = delFile.delete();
					System.out.println("[BoardDeleteServlet] 파일삭제 (" + renamedFilename + ") : " + deleted);
				}
			}
			
			int result = service.deleteBoard(no);
			String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";
	
			// 3. redirect : /mvc/board/boardList로 이동
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/studygroup/boardlist");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
