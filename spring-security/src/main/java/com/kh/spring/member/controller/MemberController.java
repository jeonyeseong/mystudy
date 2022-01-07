package com.kh.spring.member.controller;


import java.beans.PropertyEditor;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
	
	
	/**
	 * 1. Authentication DI받기
	 * form-login.login-page와 대응
	 */
	@GetMapping("/memberLogin.do")
	public void memberLogin() {
		
	}
	
//	@GetMapping("/memberDetail.do")
	public void memberDetail(Authentication authentication) {
		log.debug("authentication = {}" , authentication);
		log.debug("1 = {}" , authentication.getPrincipal());
		log.debug("2 = {}" , authentication.getName());
		
	}
	/**
	 * 2. SecurityContextHolder로부터 가져오기
	 */
//	@GetMapping("/memberDetail.do")
	public void memberDetail() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		Member principal = (Member) authentication.getPrincipal();
		Object credential = authentication.getCredentials();
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
	
		log.debug("principal = {}", principal);
		log.debug("credential = {}", credential); //null
		log.debug("authorities = {}", authorities);
		
	}
	/**
	 * 이 방법이 좋다
	 * @AutenticationPrincipal
	 * -import org.springframework.security.core.annotation.AuthenticationPrincipal;
	 * -servlet-context 에서 
	 * beans:bean class="org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver" 등록
	 */
	@GetMapping("/memberDetail.do")
	public void memberDetail(@AuthenticationPrincipal Member member) {
		log.debug("member = {}", member);
	}
	
	@PostMapping("/memberUpdate.do")
	public ResponseEntity<?> memberUpdate(
			Member updateMember, 
			@AuthenticationPrincipal Member oldMember 
	){
		log.debug("updateMember = {}", updateMember);
		log.debug("oldMember = {}", oldMember);
		
		// 1.db record갱신
		
		// 2.security context의 authentication 갱신
		oldMember.setName(updateMember.getName());
		oldMember.setBirthday(updateMember.getBirthday());
		oldMember.setEmail(updateMember.getEmail());
		oldMember.setPhone(updateMember.getPhone());
		oldMember.setAddress(updateMember.getAddress());
		oldMember.setGender(updateMember.getGender());
		oldMember.setHobby(updateMember.getHobby());
		oldMember.setAuthorities((List<SimpleGrantedAuthority>) updateMember.getAuthorities());
		
		
		Authentication newAuthentication =
				new UsernamePasswordAuthenticationToken(
						oldMember, 
						oldMember.getPassword(), 
						oldMember.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
		
		return ResponseEntity.ok("success!");
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








