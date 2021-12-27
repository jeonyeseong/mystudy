package com.kh.spring.member.controller;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	//@RequestMapping(value="/memberLogin.do", method=RequestMethod.GET)
	@GetMapping("/memberLogin.do")
	public String memberLogin() {
		return "member/memberLogin";
	}
	
	@PostMapping("/memberLogin.do")
	public String memberLogin(
			@RequestParam String id, 
			@RequestParam String password, 
			RedirectAttributes redirectAttr
	) {
		// 인증
		Member member = memberService.selectOneMember(id);
		log.info("member ={}", member);
		
							// 비밀번호 검사 메소드               평문    , 암호화된 비번
		if(member != null && bcryptPasswordEncoder.matches(password, member.getPassword())) {
			// 로그인 성공시
			redirectAttr.addFlashAttribute("msg", "로그인 성공!");
			
			//후처리는 내일
			
		}
		else {
			// 로그인 실패시
			redirectAttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		
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
