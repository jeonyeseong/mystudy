package com.kh.mvc.photo.model.servicer;

import com.kh.mvc.board.model.vo.Board;
import com.kh.mvc.photo.model.dao.PhotoDao;
import com.kh.mvc.photo.model.vo.Photo;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static com.kh.mvc.common.JdbcTemplate.*;

public class PhotoService {
	private PhotoDao photoDao = new PhotoDao();

	public int selectTotalContent() {
		Connection conn = getConnection();
		
		int result = photoDao.selectTotalContent(conn);
				
		close(conn);
		return result;
	}

	public List<Photo> selectAllPhoto(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Photo> list = photoDao.selectAllPhoto(conn, param);
		close(conn);
		return list;
	}

}
