package com.kh.spring.member.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
@SessionAttributes({"loginMember", "next"})
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	/**
	 * ResponseEntity
	 * - 응답메세지를 직접 작성. 리턴객체를 json변환 기능 -> @ResponseBody
	 * - 헤더값과 상태코드를 직접 제어
	 * 
	 * 1. status code
	 * 2. 응답헤더
	 * 3. 응답메세지 body에 작성할 java객체
	 * 
	 * - 생성자를 통해 만들거나, builder패턴으로 생성할 수 있다.
	 */
	@GetMapping("/checkIdDuplicate3.do")
	public ResponseEntity<Map<String, Object>> checkIdDuplicate3(@RequestParam String id){
		
		try {
			Member member = memberService.selectOneMember(id);
			boolean available = (member == null);
			
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			map.put("available", available);
			map.put("serverTime", System.currentTimeMillis());
			
			return ResponseEntity
					.ok()
					.header("custom-header", "hello")
					.body(map);
			
			
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * @ResponseBody 리턴된 자바객체를 그대로 응답메세지에 json문자열로 변환해서 출력.
	 * - 1. jackson의존
	 * - 2. RequestMappingHandlerAdapter의 MessageConverters List객체에 jacksonMessageConverter빈이 자동등록
	 * <annotation-driven />에 의해 자동처리
	 */
	@GetMapping("/checkIdDuplicate2.do")
	@ResponseBody
	public Map<String, Object> checkIdDuplicate2(@RequestParam String id) {

		Member member = memberService.selectOneMember(id);
		boolean available = (member == null);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("available", available);
		map.put("serverTime", System.currentTimeMillis());
		
		return map;
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/checkIdDuplicate1.do")
	public String checkIdDuplicate1(@RequestParam String id,Model model) {
		//아이디중복검사
		Member member = memberService.selectOneMember(id);
		boolean available = (member == null);
		
		model.addAttribute("available", available);
		model.addAttribute("id", id);
		
		return "jsonView";
	}
	
	
	//@RequestMapping(value="/memberLogin.do", method=RequestMethod.GET)
	@GetMapping("/memberLogin.do")
	public String memberLogin(
			@RequestHeader(name="Referer", required=false) String referer, 
			@SessionAttribute(required=false) String next,
			Model model
	) {
		
		log.info("referer = {}" , referer);
		
		if(next == null)
			model.addAttribute("next",referer);
		
		return "member/memberLogin";
	}
	
	@PostMapping("/memberLogin.do")
	public String memberLogin(
			@RequestParam String id, 
			@RequestParam String password, 
			@SessionAttribute(required=false) String next,
			RedirectAttributes redirectAttr,
			Model model
	) {
		// 인증
		Member member = memberService.selectOneMember(id);
		log.info("member ={}", member);
		log.info("encodedPassword = {}", bcryptPasswordEncoder.encode(password));
		
		String location = "/";
		if(member != null && bcryptPasswordEncoder.matches(password, member.getPassword())) {
								// 비밀번호 검사 메소드               평문    , 암호화된 비번
			// 로그인 성공시
			//redirectAttr.addFlashAttribute("msg", "로그인 성공!");
			
			//기본적으로 저장된 속성을 request의 속성으로 저장
			//session속성으로 저장하려면 class level에 @SessionAttributes에 키 값을 등록해야 한다.
			model.addAttribute("loginMember", member);
			
			//next값을 location으로 등록
			log.info("next = {}", next);
			location = next;

		}
		else {
			// 로그인 실패시
			redirectAttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		
		return "redirect:" + location;
	}
	
	@GetMapping("/memberLogout.do")
	public String memberLogout(SessionStatus sessionStatus, ModelMap model) {
		
		model.clear(); // 관리되는 model속성 완전 제거
		
		//현재 세션객체의 사용완료 설정 - 세션속성등 내부 초기화, 세션객체는 재사용
		if(!sessionStatus.isComplete())
			sessionStatus.setComplete();
		
		return "redirect:/";
	}
	
	//@RequestMapping("/memberEnroll.do")
	@GetMapping("/memberEnroll.do")
	public String memberEnroll() {
		return "member/memberEnroll";
	}

	
	@PostMapping("/memberEnroll.do")
	public String memberEnroll(Member member, RedirectAttributes redirectAttr) {
		log.info("member = {}", member);
		// 비밀번호 암호화 처리
		String rawPassword = member.getPassword(); // 평문password
		// 랜덤 salt값을 이용한 hashing처리.
		String encodedPassword = bcryptPasswordEncoder.encode(rawPassword); //암호화처리
		member.setPassword(encodedPassword);
		
		
		int result = memberService.insertMember(member);
		
		// 리다이렉트후에 session의 속성을 참조할 수 있도록한다.
		redirectAttr.addFlashAttribute("msg", result > 0 ? "회원가입 성공": "회원가입 실패");
		return "redirect:/";
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
