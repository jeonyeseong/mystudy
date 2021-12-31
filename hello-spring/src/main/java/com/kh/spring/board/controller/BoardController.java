package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.util.HelloSpringUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/**
	 * pageContext - request - session - application
	 * 빈을 관리하는 스프링의 servlet-context가 아니다.
	 */
	@Autowired
	private ServletContext application;
	
	@GetMapping("/boardDetail.do")
	public void boardDetail(@RequestParam int no, Model model) {
		Board board = boardService.selectOneBoard(no);
		log.debug("board = {}", board);
		model.addAttribute("board",board);
	}
	
	@GetMapping("/boardList.do")
	public void boardList(
			@RequestParam(defaultValue = "1") int cPage ,
			HttpServletRequest request,
			Model model) {
		int limit = 10;
		int offset = (cPage - 1) * limit;
		Map<String, Object> param = new HashMap<>();
		param.put("limit", limit);
		param.put("offset", offset);
		
		
		List<Board> list = boardService.selectBoardList(param);
		log.debug("selectBoardList = {}", list);
		
		int totalContent = boardService.selectTotalBoardCount();
		log.debug("totalContent = {}", totalContent);
		
		String url = request.getRequestURI();
		//log.debug("url = {}", url);
		
		String pagebar = HelloSpringUtils.getPagebar(cPage, limit, totalContent, url);
		//log.debug("pagebar = {}", pagebar);
		
		model.addAttribute("list", list);
		model.addAttribute("pagebar", pagebar);
		
		
	}
	
	@GetMapping("/boardForm.do")
	public void boardForm() {
		
	}
	
	@PostMapping("/boardEnroll.do")
	public String boardEnroll(
			Board board, 
			@RequestParam(name="upFile", required = false) MultipartFile[] upFiles, 
			RedirectAttributes redirectAttr
		) throws IllegalStateException, IOException {
		
		String saveDirectory = application.getRealPath("/resources/upload/board");
		List<Attachment> attachments = new ArrayList<>();
		
		//1.첨부파일을 서버컴퓨터 저장 : rename
		//2.저장한 파일의 정보 -> Attachment객체 -> attachment insert
		for(int i = 0; i < upFiles.length; i++) {
			MultipartFile upFile = upFiles[i];
			if(!upFile.isEmpty()) {
				// 1. 저장경로 | renamedFilename
				String originalFilename = upFile.getOriginalFilename();
				String renamedFilename = HelloSpringUtils.rename(originalFilename);
				File dest = new File(saveDirectory, renamedFilename);
				upFile.transferTo(dest);
				
				//2. 
				Attachment attach = new Attachment();
				attach.setOriginalFilename(originalFilename);
				attach.setRenamedFilename(renamedFilename);
				attachments.add(attach);
			}
		}
		
		
		if(!attachments.isEmpty())
			board.setAttachments(attachments);
		log.debug("board = {}", board);
		
		int result = boardService.insertBoard(board);
		String msg = "게시물 등록 성공!";
		redirectAttr.addFlashAttribute("msg", msg);
		
		return "redirect:/board/boardList.do";
				
	}
	
}
