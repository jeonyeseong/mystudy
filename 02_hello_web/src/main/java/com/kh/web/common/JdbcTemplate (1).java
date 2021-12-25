package com.kh.web.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * 
 * static 자원을 사용하는 jdbc 공용클래스
 *
 */
public class JdbcTemplate {

	private static String driverClass;
	private static String url; //접속프로토콜@url:port:sid
	private static String user;
	private static String password;
	
	static {
		//resources/datasource.properties 내용 불러오기
		//buil-path의 절대경로 가져오기
		// / -> /src/main/webapp/WEB-INF/classes/
		final String datasourceConfigPath = 
				JdbcTemplate.class.getResource("/datasource.properties").getPath();
		System.out.println(datasourceConfigPath);
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(datasourceConfigPath));
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		
		
			try {
//		 1. driver class 등록 : 프로그램 실행시 최초 1회만 처리
				System.out.println(Class.forName(driverClass));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static Connection getConnection() {
		Connection conn = null;
			try {
//		 2. Connection객체 생성(autoCommit false처리)
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return conn;
	}

	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) //!conn.isClosed() : conn이 닫혀있지 않다면
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && !pstmt.isClosed()) //!conn.isClosed() : conn이 닫혀있지 않다면
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) //!conn.isClosed() : conn이 닫혀있지 않다면
			rset.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
