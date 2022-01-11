package com.kh.spring.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.common.util.HelloSpringUtils;
import com.kh.spring.menu.model.service.MenuService;
import com.kh.spring.menu.model.vo.Menu;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @RestController
 * @Controller + 모든 메소드에 @responseBody에 적용
 *
 */
@RestController
@Slf4j
@CrossOrigin
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menus")
	public List<Menu> menus(){
		List<Menu> list = menuService.selectAllMenu();
		log.debug("list = {}", list);
		
		// CORS 정책 대비
		//response.setHeader("Access-control-Allow-Origin", "*"); // 모든 Origin
		return list;
	}
	
	/**
	 * @PathVariable 경로변수
	 *  - url의 일부를 매개인자로 받을 수 있다.
	 * 
	 */
	
	@GetMapping("/menus/{type}/{taste}")
	public List<Menu> selectMenu(@PathVariable String type, @PathVariable String taste){
		log.debug("type = {}", type);
		Map<String, Object> param = new HashMap<>();
		param.put("type", type);
		param.put("taste", taste);
		
		return menuService.selectMenuByTypeAndTaste(param);
	}
	
	/**
	 * @RequestBody
	 * 	- 요청메세지바디의 json문자열을 parsing해서 java객체로 변환
	 * 	
	 */
	@PostMapping("/menu")
	public Map<String, Object> insertMenu(@RequestBody Menu menu){
		log.debug("menu = {}", menu);
		int result = menuService.insertMenu(menu);
		
		Map<String, Object> map = new HashMap<>();
		map.put("msg", "등록성공!");
		map.put("result", result);
		return map;
	}
	
	@GetMapping("/menu/{one}")
	public ResponseEntity<?> selectOneMenu(@PathVariable int one){
		log.debug("one = {}", one);
		Menu menu = menuService.selectOneMenu(one);
		if(menu != null) {
			log.debug("menu = {}", menu);
			return ResponseEntity.ok(menu);
		}
		else
			return ResponseEntity.notFound().build();
	}
		

	/**
	 *	/menu/page1 1~5
	 *	/menu/page2 6~10
	 *	/menu/page/3 11~15
	 * @param page
	 * @return
	 */
	@GetMapping("/menu/page/{page}")
	public ResponseEntity<?> selectPageMenu(@PathVariable int page, 
			Model model,
			HttpServletRequest request){

		int limit = 5;
		int offset = (page -1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Menu> list = menuService.selectPageMenu(rowBounds);

		//int totalMenu = menuService.selectTotalMenu();
		//log.debug("totalMenu = {}", totalMenu);
		
		//String url = request.getRequestURI();
		//log.debug("url = {}", url);
		
		//String pagebar = HelloSpringUtils.getPagebar(page, limit, totalMenu, url);
		//log.debug("pagebar = {}", pagebar);
		
//		model.addAttribute("list", list);
//		model.addAttribute("pagebar", pagebar);
		
		
		return ResponseEntity.ok(list);
	}
	
	/**
	 * 비동기요청 DML - redirect할 필요 없음. 비동기 응답메세지에 사용자 피드백을 작성
	 * 
	 * 동기요청 DML - 주소 남지 않도록 redirect, 피드백 session에 msg로 저장(RedirectAttributes)
	 * 
	 * 
	 * @param menu
	 * @return
	 */
	@PutMapping("/menu")
	public ResponseEntity<?> updateMenu(@RequestBody Menu menu){
		log.debug("menu = {}", menu);
		try {
			int result = menuService.updateMenu(menu);
			
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "등록성공!");
			map.put("result", result);
			return ResponseEntity.ok(map);
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/menu/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable int id){
		log.debug("id = {}", id);
		try {
			int result = menuService.deleteMenu(id);
			
			Map<String, Object> map = new HashMap<>();
			if(result >0) {
				map.put("msg", "삭제성공");
				map.put("result", result);
				return ResponseEntity.ok(map);
			}
			else {
				return ResponseEntity.notFound().build();
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return ResponseEntity.internalServerError().build();
		}
		
	}


	
	
}














