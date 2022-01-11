package com.kh.spring.menu.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.menu.model.vo.Menu;

@Mapper
public interface MenuDao {

	List<Menu> selectAllMenu();

	List<Menu> selectMenuByTypeAndTaste(Map<String, Object> param);

	int insertMenu(Menu menu);

	Menu selectOneMenu(int one);

}
