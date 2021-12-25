package com.kh.studygroup.board.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


import com.kh.studygroup.board.model.dao.StudyGroupBoardDao;
import com.kh.studygroup.board.model.vo.Attachment;
import com.kh.studygroup.board.model.vo.StudyGroupBoard;

public class StudyGroupBoardService {

	
	private StudyGroupBoardDao dao = new StudyGroupBoardDao();

	public List<StudyGroupBoard> SelectAllBoard(Map<String, Integer> param, int group) {


		Connection conn = getConnection();
		List<StudyGroupBoard> list = dao.SelectAllBoard(conn,param,group);
		
		close(conn);
		return list;
	}

	public int selectTotalBoardCount() {
		Connection conn = getConnection();
		int totalCount = dao.selectTotalBoardCount(conn);
		close(conn);
		return totalCount;
	}

	public StudyGroupBoard SelectOneBoardAttachments(int no) {
		Connection conn = getConnection();
		StudyGroupBoard board = dao.SelectOneBoardAttachments(conn, no);
		close(conn);
		return board;
	}

	public int UpdateReadCount(int no) {
		Connection conn = getConnection();
		int result = dao.UpdateReadCount(conn, no);
		close(conn);
		return result;
	}

	public Attachment selectOneAttachment(int no) {
		Connection conn = getConnection();
		Attachment attach = dao.selectOneAttachment(conn, no);
		close(conn);
		
		return attach;
	}

	public int groupBoardEnroll(StudyGroupBoard board) {
		Connection conn = getConnection();
		int result = 0;
		try {
			conn = getConnection();
			result = dao.groupBoardEnroll(conn, board);
			
			// 방금 insert된 boardNo 조회 : select seq_study_group_board.currval from dual
			int boardNo = dao.selectLastGroupBoardNo(conn);
			board.setBoardNo(boardNo);
			
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null) {
				for(Attachment attach : attachments) {
					attach.setBoard_No(boardNo); // FK컬럼값 설정(중요)
					result = dao.insertAttachment(conn, attach);
					
				}
			}
			
			
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
			
		}
			
		return result;
	}

	public List<Attachment> selectAttachmentByBoard(int no) {
		Connection conn = getConnection();
		List<Attachment> attachments = null;
		try {
			conn = getConnection();
			attachments = dao.selectAttachmentByBoard(conn,no);
			
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return attachments;
	}

	public int deleteBoard(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.deleteBoard(conn,no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	}

	public int updateGroupBoard(StudyGroupBoard board) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.updateGroupBoard(conn,board);
			
			
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = dao.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	}

	public int deleteAttachment(int delFileNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.deleteAttachment(conn,delFileNo);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	}

	public List<StudyGroupBoard> selectMyboardList(Map<String, Integer> param, int studyNo, String memberId) {
		Connection conn = getConnection();
		List<StudyGroupBoard> list = dao.selectMyboardList(conn,param,studyNo,memberId);
		
		close(conn);
		return list;
	}




	
	
	
}
