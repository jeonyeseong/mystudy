package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.board.model.dao.FrontboardDao;
import com.kh.board.model.vo.Frontboard;
import com.kh.board.model.vo.FrontboardComment;
import com.kh.community.model.vo.FreeboardComment;


public class FrontboardService {
	
	private FrontboardDao frontboardDao = new FrontboardDao();

	public List<Frontboard> selectAllBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Frontboard> list = frontboardDao.selectAllBoard(conn, param);
		close(conn);
		return list;
	}
	

	public int selectTotalBoardCount() {
		Connection conn = getConnection();
		int totalCount = frontboardDao.selectTotalBoardCount(conn);
		close(conn);
		return totalCount;
	}


	public int insertBoard(Frontboard frontboard) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = frontboardDao.insertBoard(conn, frontboard);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public List<Frontboard> myboardlist(Map<String, Integer> param, String memberId) {
		Connection conn = getConnection();
		List<Frontboard> list = frontboardDao.myboardlist(conn,param, memberId);
		close(conn);
		return list;
	}


	public int updateReadCount(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = frontboardDao.updateReadCount(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public Frontboard selectOneBoard(int no) {
		Connection conn = getConnection();
		Frontboard board = frontboardDao.selectOneBoard(conn, no);
//		List<Attachment> attachments = boardDao.selectAttachmentByBoardNo(conn, no);
//		board.setAttachments(attachments);
		close(conn);
		return board;
	}


	public List<Frontboard> selectAllWatchBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Frontboard> watchlist = frontboardDao.selectAllWatchBoard(conn, param);
		close(conn);
		
		return watchlist;
	}


	public List<Frontboard> selectAllBoardOnline(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Frontboard> listonline = frontboardDao.selectAllBoardOnline(conn, param);
		close(conn);
		
		return listonline;
	}


	public List<Frontboard> selectAllBoardOffline(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Frontboard> listoffline = frontboardDao.selectAllBoardOffline(conn, param);
		close(conn);
		
		return listoffline;
	}



	public List<FrontboardComment> selectFrontBoardCommentList(int boardNo) {
		Connection conn = getConnection();
		List<FrontboardComment> commentList = frontboardDao.selectFrontBoardCommentList(conn, boardNo);
		close(conn);
		return commentList;
	}


	public int insertFrontBoardComment(FrontboardComment bc) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = frontboardDao.insertFrontBoardComment(conn, bc);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	
	public int deleteFrontBoardComment(int no) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = frontboardDao.deleteFrontBoardComment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}
	
	public int deleteFrontBoard(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = frontboardDao.deleteFrontBoard(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int updateFrontBoard(Frontboard frontboard) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
	
			result = frontboardDao.updateFrontBoard(conn, frontboard);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	

	
}
