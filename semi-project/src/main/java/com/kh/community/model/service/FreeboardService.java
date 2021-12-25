package com.kh.community.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.board.model.vo.Frontboard;
import com.kh.community.model.dao.FreeboardDao;
import com.kh.community.model.vo.Freeboard;
import com.kh.community.model.vo.FreeboardComment;


public class FreeboardService {

	private FreeboardDao freeboardDao = new FreeboardDao();

	public List<Freeboard> selectAllFreeBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Freeboard> list = freeboardDao.selectAllFreeBoard(conn, param);
		close(conn);
		return list;
	}
	
	
	public int selectTotalFreeBoardCount() {
		Connection conn = getConnection();
		int totalContent = freeboardDao.selectTotalFreeBoardCount(conn);
		close(conn);
		return totalContent;
	}
	

	public int insertFreeBoard(Freeboard freeboard) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = freeboardDao.insertFreeBoard(conn, freeboard);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	


	public Freeboard selectOneFreeBoard(int no) {
		Connection conn = getConnection();
		Freeboard freeboard = freeboardDao.selectAllFreeBoard(conn, no);
		close(conn);
		return freeboard;
	}


	public int updateReadCount(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = freeboardDao.updateReadCount(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public int deleteFreeBoard(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = freeboardDao.deleteFreeBoard(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public int updateFreeBoard(Freeboard freeboard) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
	
			result = freeboardDao.updateFreeBoard(conn, freeboard);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public List<FreeboardComment> selectFreeBoardCommentList(int boardNo) {
		Connection conn = getConnection();
		List<FreeboardComment> commentList = freeboardDao.selectFreeBoardCommentList(conn, boardNo);
		close(conn);
		return commentList;
	}


	public int insertFreeBoardComment(FreeboardComment bc) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = freeboardDao.insertFreeBoardComment(conn, bc);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public int deleteFreeBoardComment(int no) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = freeboardDao.deleteFreeBoardComment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}


	public int updateLikeCount(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = freeboardDao.updateLikeCount(conn,no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}




	public List<Freeboard> searchMember(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Freeboard> list = freeboardDao.searchMember(conn, param);
		close(conn);
		return list;
	}


	public List<Freeboard> selectMyFreeBoard(Map<String, Integer> param, String memberId) {
		Connection conn = getConnection();
		List<Freeboard> list = freeboardDao.selectMyFreeBoard(conn, param, memberId);
		close(conn);
		return list;
	}





	
}
