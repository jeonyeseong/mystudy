package com.kh.mvc.photo.model.dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import static com.kh.mvc.common.JdbcTemplate.*;

import com.kh.mvc.board.model.dao.BoardDao;
import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.photo.model.vo.Photo;

public class PhotoDao {
	
	private Properties prop = new Properties();
	
	/**
	 * board-query.properties 파일의 key=value 쿼리를 가져온다.
	 * - 클래스객체를 통해 build-path에 배포된 파일에 접근할 것!
	 * 
	 */
	public PhotoDao() {
		File file = new File(PhotoDao.class.getResource("/photo-query.properties").getPath());
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[PhotoDao] prop = " + prop);
	}

	public int selectTotalContent(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = prop.getProperty("selectTotalContent");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				result = rset.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		
		
		return result;
	}

	public List<Photo> selectAllPhoto(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllPhoto");
		ResultSet rset = null;
		List<Photo> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Photo photo = new Photo();
				photo.setNo(rset.getInt("no")); 
				photo.setWriter(rset.getString("writer")); 
				photo.setContent(rset.getString("content")); 
				photo.setOriginalFilename(rset.getString("original_filename"));
				photo.setRenamedFilename(rset.getString("renamed_filename"));
				photo.setRegDate(rset.getDate("reg_date"));
				photo.setReadCount(rset.getInt("read_count"));
				list.add(photo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

}
