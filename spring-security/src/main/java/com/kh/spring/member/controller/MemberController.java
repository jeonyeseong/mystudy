package com.kh.spring.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	/**
	 * form-login.login-page와 대응
	 */
	@GetMapping("/memberLogin.do")
	public void memberLogin() {
		
	}
	
}
