package com.kh.studygroup.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.studygroup.board.model.service.StudyGroupBoardService;
import com.kh.studygroup.board.model.vo.Attachment;

/**
 * Servlet implementation class FileDownLoadServlet
 */
@WebServlet("/studygroup/fileDownload")
public class FileDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudyGroupBoardService service = new StudyGroupBoardService();   
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
				int no = Integer.parseInt(request.getParameter("no"));
				
			
				System.out.println("[FileDownloadServlet] attach = " + no);
				// 2. 업무로직
				// a.attachment 정보 가져오기
				Attachment attach = service.selectOneAttachment(no);
				
			
				
				// b.응답메세지에 파일출력 : ServletOutputStream 가져오기
				// 파일 입력스트림 
				String saveDirectory = getServletContext().getRealPath("/upload/group");
				File downFile = new File(saveDirectory, attach.getRenamed_filename());
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
				
				ServletOutputStream sos = response.getOutputStream();
				BufferedOutputStream bos = new BufferedOutputStream(sos);
				
				
				// 헤더정보 작성
				response.setContentType("application/octet-stream"); // 이진 데이터를 가리키는 MIME타입
				// 한글은 기본인코딩 iso-8859-1 처리
				
				String originalFilename = new String(attach.getOriginal_filename().getBytes("utf-8"), "iso-8859-1");
				System.out.println("[FileDownloadServlet] originalFilename = " + originalFilename);
				response.setHeader("Content-Disposition","attachment; filename =" + originalFilename);
				
				// 출력
				int data = -1;
				while((data = bis.read()) != -1) {
					bos.write(data);
					
				}
				
				
				// 자원반납(필수)
				bos.close();
				bis.close();
				
				
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
