package com.kh.ajax.smart.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.ajax.smart.model.dao.SmartDao;
import com.kh.ajax.smart.model.vo.SmartPhone;
import static com.kh.ajax.common.JdbcTemplate.*;

public class SmartService {
	private SmartDao smartDao = new SmartDao();

	public List<SmartPhone> selectAllSmart(int start, int end) {
		System.out.println("service안 도착");
		Connection conn = getConnection();
		System.out.println("service->dao");
		List<SmartPhone> list = smartDao.selectAllSmart(conn, start, end);
		System.out.println("smartService = " + list);
		close(conn);
		return list;
	}
	
	

}
