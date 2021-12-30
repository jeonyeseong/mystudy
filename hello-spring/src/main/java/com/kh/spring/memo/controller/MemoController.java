package com.kh.spring.memo.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.memo.model.service.MemoService;
import com.kh.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Proxy객체를 통해 AOP를 구현한다.
 *  - 인터페이스구현객체 : JDK동적 proxy객체를 의존주입한다.
 *  - 인터페이스구현하지 않은 객체 : CGLIB에 의해 생성된 PROXY객체를 의존주입한다.
 *
 */
@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	
	@Autowired
	private MemoService memoService;

	/**
	 * 리턴하는 ViewName이 없는 경우, 요청url로부터 jsp경로를 유추해서 찾아간다.
	 * (ViewNameTranslator빈)
	 * 
	 * memo/memo.do -> "memo/memo"
	 */
	
	@GetMapping("/memo.do")
	public void memo(Model model) {
		log.debug("컨트롤러 주업무");
		List<Memo> list = memoService.memoList();
		log.info("list = {}", list);
				
		model.addAttribute("list", list);
		
	}
	
	@PostMapping("/insertMemo.do")
	public String insertMemo(Memo memo, RedirectAttributes redirectAttr) {
		//StopWatch stopwatch = new StopWatch();
		//stopwatch.start();
		//log.debug("stopwatch 시작");
		log.debug("의존타입 : {}", memoService.getClass());
		try {
			log.debug("memo={}", memo);

			int result = memoService.insertMemo(memo);			
			
			redirectAttr.addFlashAttribute("msg", result > 0 ? "메모추가 성공": "메모추가 실패");
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e; // spring container 예외처리 위임
		}
		
		//stopwatch.stop();
		//log.debug("stopwatch 스탑");
		
		//System.out.println(stopwatch.prettyPrint());
		return "redirect:/memo/memo.do";
	}
	
	@PostMapping("/deleteMemo.do")
	public String deleteMemo(
			@RequestParam int no, 
			String password,
			RedirectAttributes redirectAttr
	) {
		log.debug("no = {} , password = {}", no , password);
		
		int result = memoService.deleteMemo(no);
		
		redirectAttr.addFlashAttribute("msg", result > 0 ? "메모삭제 성공": "메모삭제 실패");
		
		return "redirect:/memo/memo.do";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//boolean allowEmpty - true 빈문자열 ""인 경우 null변환함.
		PropertyEditor editor = new CustomDateEditor(sdf, true);
		// j.u.Date변환시 해당 editor객체 사용
		binder.registerCustomEditor(Date.class, editor);
		
	}
}
