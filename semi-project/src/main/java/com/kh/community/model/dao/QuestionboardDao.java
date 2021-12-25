package com.kh.community.model.dao;

import static com.kh.common.JDBCTemplate.close;

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

import com.kh.community.model.exception.FreeboardException;
import com.kh.community.model.exception.QuestionboardException;
import com.kh.community.model.vo.Attachment;
import com.kh.community.model.vo.Freeboard;
import com.kh.community.model.vo.Questionboard;
import com.kh.community.model.vo.QuestionboardComment;

public class QuestionboardDao {
	
private Properties prop = new Properties();
	
	public QuestionboardDao() {
		File file = new File(FreeboardDao.class.getResource("/questionboard-query.properties").getPath());
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<Questionboard> selectAllQuestionBoard(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllQuestionBoard");
		ResultSet rset = null;
		List<Questionboard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Questionboard board = new Questionboard();
				board.setNo(rset.getInt("no"));
				board.setTitle(rset.getString("title")); 
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setReadCount(rset.getInt("read_count"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setAsk(rset.getString("ask"));			
				board.setLikeCount(rset.getInt("like_count"));
				board.setCommentCount(rset.getInt("comment_count"));
				board.setAttachCount(rset.getInt("attach_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			throw new QuestionboardException("게시글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int insertQuestionBoard(Connection conn, Questionboard questionboard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQuestionBoard");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, questionboard.getTitle());
			pstmt.setString(2, questionboard.getWriter());
			pstmt.setString(3, questionboard.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QuestionboardException("게시물 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int selectLastQuestionBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectLastQuestionBoardNo");
		ResultSet rset = null;
		int boardNo = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new QuestionboardException("최근 게시글번호 조회 오류", e);
		} finally {
			close(pstmt);
		}
		return boardNo;
	}


	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QuestionboardException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public Questionboard selectOneQuestionBoardAttachements(Connection conn, int no) {
		Questionboard questionboard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneQuestionBoardAttachements");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				questionboard = new Questionboard();
				questionboard.setNo(rset.getInt("no"));
				questionboard.setTitle(rset.getString("title"));
				questionboard.setWriter(rset.getString("writer"));
				questionboard.setContent(rset.getString("content"));
				questionboard.setReadCount(rset.getInt("read_count"));
				questionboard.setRegDate(rset.getDate("reg_date"));
				questionboard.setAsk(rset.getString("ask"));			
				questionboard.setLikeCount(rset.getInt("like_count"));
				
				int attachNo = rset.getInt("attach_no");
				if(attachNo != 0) {
					// 첨부파일이 있는 경우 1행 또는 2행이다.
					List<Attachment> attachments = new ArrayList<>();
					do {
						Attachment attach = new Attachment();
						attach.setNo(rset.getInt("attach_no"));
						attach.setBoardNo(rset.getInt("board_no"));
						attach.setOriginalFilename(rset.getString("original_filename"));
						attach.setRenamedFilename(rset.getString("renamed_filename"));
						attach.setRegDate(rset.getDate("reg_date"));
						attachments.add(attach);
					} while (rset.next());
					questionboard.setAttachments(attachments);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return questionboard;
	}
	

	public int updateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QuestionboardException("조회수 증가 처리 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public int updateLikeCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateLikeCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QuestionboardException("추천수 증가 처리 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	public Attachment selectOneAttachment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectOneAttachment");
		ResultSet rset = null;
		Attachment attach = null;
		
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("board_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
			}
		}catch(Exception e){
			throw new QuestionboardException("첨부파일 조회 오류", e);
		}finally{
			close(rset);
			close(pstmt);
		}
		return attach;
	}

	public List<Attachment> selectAttachmentByBoardNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAttachmentByBoardNo");
		ResultSet rset = null;
		List<Attachment> attachments = new ArrayList<>();
		
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문미완성
			pstmt.setInt(1, boardNo);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Attachment attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setBoardNo(rset.getInt("board_no"));
				attach.setOriginalFilename(rset.getString("original_filename"));
				attach.setRenamedFilename(rset.getString("renamed_filename"));
				attach.setRegDate(rset.getDate("reg_date"));
				attachments.add(attach);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return attachments;

	}
	
	
    public int deleteQuestionBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteQuestionBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문미완성
			pstmt.setInt(1, no);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
    
    
    public Questionboard selectOneQuestionBoard(Connection conn, int no) {
		Questionboard questionboard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneQuestionBoard");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				questionboard = new Questionboard();
				questionboard.setNo(rset.getInt("no"));
				questionboard.setTitle(rset.getString("title"));
				questionboard.setWriter(rset.getString("writer"));
				questionboard.setContent(rset.getString("content"));
				questionboard.setReadCount(rset.getInt("read_count"));
				questionboard.setRegDate(rset.getDate("reg_date"));
				questionboard.setAsk(rset.getString("ask"));			
				questionboard.setLikeCount(rset.getInt("like_count"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return questionboard;
	}

    
    
    public int updateQuestionBoard(Connection conn, Questionboard questionboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateQuestionBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, questionboard.getTitle());
			pstmt.setString(2, questionboard.getContent());
			pstmt.setInt(3, questionboard.getNo());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteAttachment(Connection conn, int delFileNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAttachment"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문미완성
			pstmt.setInt(1, delFileNo);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	
	public List<QuestionboardComment> selectQuestionBoardCommentList(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectQuestionBoardCommentList");
		ResultSet rset = null;
		List<QuestionboardComment> commentList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				QuestionboardComment bc = new QuestionboardComment();
				bc.setNo(rset.getInt("no"));
				bc.setCommentLevel(rset.getInt("comment_level"));
				bc.setWriter(rset.getString("writer"));
				bc.setContent(rset.getString("content"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setCommentRef(rset.getInt("comment_ref"));
				bc.setRegDate(rset.getDate("reg_date"));
				commentList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return commentList;
	}

	public int insertQuestionBoardComment(Connection conn, QuestionboardComment bc) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertQuestionBoardComment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getCommentLevel()); 	// 1, 2
			pstmt.setString(2, bc.getWriter()); 	// memberId
			pstmt.setString(3, bc.getContent()); 	// ..
			pstmt.setInt(4, bc.getBoardNo());		// boardNo
			pstmt.setObject(5, bc.getCommentRef() == 0 ? null : bc.getCommentRef());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteQuestionBoardComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteQuestionBoardComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new QuestionboardException("댓글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int selectTotalQuestionBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalQuestionBoardCount");
		ResultSet rset = null;
		int totalContent = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	
	
	public List<Questionboard> searchMember(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchMember");
		ResultSet rset = null;
		List<Questionboard> list = new ArrayList<>();
		
		String searchType = (String) param.get("searchType");
		String searchKeyword = (String) param.get("searchKeyword");
		switch(searchType) {
		case "writer": sql += " writer like '%" + searchKeyword + "%'"; break;
		case "title": sql += " title like '%" + searchKeyword + "%'"; break;
		case "content": sql += " content like '%" + searchKeyword + "%'"; break;
//		case "gender": sql += " gender = '" + searchKeyword + "'"; break;
//		case "rank": sql = " gender = '" + searchKeyword + "'"; break;
		}
		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Questionboard questionboard = new Questionboard();
				questionboard.setNo(rset.getInt("no"));
				questionboard.setTitle(rset.getString("title")); 
				questionboard.setWriter(rset.getString("writer"));
				questionboard.setContent(rset.getString("content"));
				questionboard.setReadCount(rset.getInt("read_count"));
				questionboard.setRegDate(rset.getDate("reg_date"));
				questionboard.setLikeCount(rset.getInt("like_count"));
				
				list.add(questionboard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public List<Questionboard> selectMyQuestionBoard(Connection conn, Map<String, Integer> param, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectMyQuestionBoard");
		ResultSet rset = null;
		List<Questionboard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, param.get("start"));
			pstmt.setInt(3, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Questionboard board = new Questionboard();
				board.setNo(rset.getInt("no"));
				board.setTitle(rset.getString("title")); 
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setReadCount(rset.getInt("read_count"));
				board.setRegDate(rset.getDate("reg_date"));
				board.setAsk(rset.getString("ask"));			
				board.setLikeCount(rset.getInt("like_count"));
				//board.setCommentCount(rset.getInt("comment_count"));
				board.setAttachCount(rset.getInt("attach_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			throw new FreeboardException("게시글 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
				



}
