package com.kh.ajax.smart.model.dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.ajax.smart.model.vo.SmartPhone;
import com.kh.ajax.smart.model.dao.*;
import static com.kh.ajax.common.JdbcTemplate.*;

public class SmartDao {
	
	private Properties prop = new Properties();
	
	/**
	 * board-query.properties 파일의 key=value 쿼리를 가져온다.
	 * - 클래스객체를 통해 build-path에 배포된 파일에 접근할 것!
	 * 
	 */
	public SmartDao() {
		File file = new File(SmartDao.class.getResource("/smart-query.properties").getPath());
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<SmartPhone> selectAllSmart(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		List<SmartPhone> list = new ArrayList<SmartPhone>();
		ResultSet rset = null;
		String sql = "select * from (select row_number () over(order by amount desc) rnum, sp.* from smartphone sp) where rnum between ? and ?";
		System.out.println("smartDao sql = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				SmartPhone sp = new SmartPhone();
				sp.setPname(rset.getString("pname"));
				sp.setAmount(rset.getInt("amount"));
				sp.setPdate(rset.getDate("pdate"));
				
				list.add(sp);
			}
			System.out.println("smartDao = "+list);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
		return list;
	}

}
