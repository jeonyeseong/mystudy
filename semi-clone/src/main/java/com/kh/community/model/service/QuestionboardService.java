package com.kh.community.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.community.model.dao.QuestionboardDao;
import com.kh.community.model.vo.Attachment;
import com.kh.community.model.vo.Freeboard;
import com.kh.community.model.vo.Questionboard;
import com.kh.community.model.vo.QuestionboardComment;

public class QuestionboardService {
	private QuestionboardDao questionboardDao = new QuestionboardDao();

	public List<Questionboard> selectAllQuestionBoard(Map<String, Integer> param) {
		Connection conn = getConnection();
		List<Questionboard> list = questionboardDao.selectAllQuestionBoard(conn, param);
		close(conn);
		return list;
	}

	public int insertQuestionBoard(Questionboard questionboard) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = questionboardDao.insertQuestionBoard(conn, questionboard);
			
			// 방금 insert된 boardNo 조회 : select seq_board_no.currval from dual
			int boardNo = questionboardDao.selectLastQuestionBoardNo(conn);
			System.out.println("[QuestionoardService] boardNo = " + boardNo);
			questionboard.setNo(boardNo); // servlet에서 참조할 수 있도록한다.
			
			List<Attachment> attachments = questionboard.getAttachments();
			if(attachments != null) {
				// insert into attachment values(seq_attachment_no.nextval, 0, ?, ?, default)
				for(Attachment attach : attachments) {
					attach.setBoardNo(boardNo); // FK컬럼값 설정(중요)
					result = questionboardDao.insertAttachment(conn, attach);
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	
	public Questionboard selectOneQuestionBoardAttachements(int no) {
		Connection conn = getConnection();
		Questionboard questionboard = questionboardDao.selectOneQuestionBoardAttachements(conn, no);
		close(conn);
		return questionboard;
	}

	public int updateReadCount(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = questionboardDao.updateReadCount(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	public int updateLikeCount(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = questionboardDao.updateLikeCount(conn,no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	public Attachment selectOneAttachment(int no) {
		Connection conn = getConnection();
		Attachment attach = questionboardDao.selectOneAttachment(conn, no);
		close(conn);
		return attach;
	}
	
	
	public List<Attachment> selectAttachmentByBoardNo(int no) {
		Connection conn = getConnection();
		List<Attachment> attachments = questionboardDao.selectAttachmentByBoardNo(conn, no);
		close(conn);
		return attachments;
	}

	public int deleteQuestionBoard(int no) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = questionboardDao.deleteQuestionBoard(conn, no);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	
	public Questionboard selectOneQuestionBoard(int no) {
		Connection conn = getConnection();
		Questionboard questionboard = questionboardDao.selectOneQuestionBoard(conn, no);
		List<Attachment> attachments = questionboardDao.selectAttachmentByBoardNo(conn, no);
		questionboard.setAttachments(attachments);
		close(conn);
		return questionboard;
	}
	
	
	public int updateQuestionBoard(Questionboard questionboard) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			// 트랙잭션처리할 코드
			// 1. board update
			result = questionboardDao.updateQuestionBoard(conn, questionboard);
			
			// 2. attachment insert
			List<Attachment> attachments = questionboard.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					result = questionboardDao.insertAttachment(conn, attach); 
				}
			}
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteAttachment(int delFileNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = questionboardDao.deleteAttachment(conn, delFileNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	public List<QuestionboardComment> selectQuestionBoardCommentList(int boardNo) {
		Connection conn = getConnection();
		List<QuestionboardComment> commentList = questionboardDao.selectQuestionBoardCommentList(conn, boardNo);
		close(conn);
		return commentList;
	}

	public int insertQuestionBoardComment(QuestionboardComment bc) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = questionboardDao.insertQuestionBoardComment(conn, bc);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteQuestionBoardComment(int no) {
		Connection conn = getConnection(); 
		int result = 0;
		try {
			result = questionboardDao.deleteQuestionBoardComment(conn, no);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	public int selectTotalQuestionBoardCount() {
		Connection conn = getConnection();
		int totalContent = questionboardDao.selectTotalQuestionBoardCount(conn);
		close(conn);
		return totalContent;
	}
	
	
	public List<Questionboard> searchMember(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Questionboard> list = questionboardDao.searchMember(conn, param);
		close(conn);
		return list;
	}

	public List<Questionboard> selectMyQuestionBoard(Map<String, Integer> param, String memberId) {
		Connection conn = getConnection();
		List<Questionboard> list = questionboardDao.selectMyQuestionBoard(conn, param, memberId);
		close(conn);
		return list;
	}


}
