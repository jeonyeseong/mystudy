package com.kh.studygroup.board.model.dao;

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

import com.kh.studygroup.board.model.exception.BoardException;
import com.kh.studygroup.board.model.vo.Attachment;
import com.kh.studygroup.board.model.vo.StudyGroupBoard;

public class StudyGroupBoardDao {
	private Properties prop = new Properties();
	
	public  StudyGroupBoardDao(){
		// /build/classes 하위에서 파일을 조회
		File file =  new File(StudyGroupBoardDao.class.getResource("/study-group-query.properties").getPath());
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public List<StudyGroupBoard> SelectAllBoard(Connection conn, Map<String, Integer> param, int group) {

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllBoard");
		ResultSet rset = null;
		
		List<StudyGroupBoard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("startNum"));
			pstmt.setInt(2, param.get("endNum"));
			pstmt.setInt(3, group);

			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				StudyGroupBoard board = new StudyGroupBoard();
				board.setBoardNo(rset.getInt("group_board_no"));
				board.setStudy_Group_No(rset.getInt("study_group_no"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setRead_count(rset.getInt("read_count"));
				board.setReg_Date(rset.getDate("reg_date"));
				
				board.setAttachCount(rset.getInt("attach_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}
		

		
		
		return list;
	}


	public int selectTotalBoardCount(Connection conn) {

		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalBoardCount");
		ResultSet rset = null;
		int totalCount = 0;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		return totalCount;
	}


	public StudyGroupBoard SelectOneBoardAttachments(Connection conn, int no) {
		PreparedStatement pstmt = null;
		StudyGroupBoard board = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOnBoardAttachments");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				board = new StudyGroupBoard();
				board.setBoardNo(rset.getInt("group_board_no"));
				board.setStudy_Group_No(rset.getInt("study_group_no"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setRead_count(rset.getInt("read_count"));
				board.setReg_Date(rset.getDate("reg_date"));
				
				int attachNo = rset.getInt("no");
				
				if(attachNo != 0) {
					// 첨부파일이 있는 경우 1행 또는 2행이다.
					List<Attachment> attachments = new ArrayList<>();
					do {
						Attachment attach = new Attachment();
						attach.setNo(rset.getInt("no"));
						attach.setStudy_Group_No(rset.getInt("study_group_no"));
						attach.setBoard_No(rset.getInt("board_no"));
						attach.setOriginal_filename(rset.getString("original_filename"));
						attach.setRenamed_filename(rset.getString("renamed_filename"));
						attach.setReg_Date(rset.getDate("reg_date"));
						attachments.add(attach);
						
					} while(rset.next());
					board.setAttachments(attachments);
					
				}
			}
			
		} catch (SQLException e) {
			throw new BoardException("게시글 불러오기 오류!",e);
		}
		finally {
			
			close(rset);
			close(pstmt);
		}
		
		
		
		return board;
	}


	public int UpdateReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		StudyGroupBoard board = null;
		int result = 0;
		String sql = prop.getProperty("updateReadCount");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BoardException("조회수 증가 오류!",e);
		}
		finally {
			
			close(pstmt);
		}
		
		
		return result;
	}


	public Attachment selectOneAttachment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		Attachment attach = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneAttachment");
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setStudy_Group_No(rset.getInt("study_group_no"));
				attach.setBoard_No(rset.getInt("board_no"));
				attach.setOriginal_filename(rset.getString("original_filename"));
				attach.setRenamed_filename(rset.getString("renamed_filename"));
				attach.setReg_Date(rset.getDate("reg_date"));

			}
			
			
		} catch (SQLException e) {
			throw new BoardException("첨부파일 조회 오류!",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		
		return attach;
	}


	public int groupBoardEnroll(Connection conn, StudyGroupBoard board) {
		PreparedStatement pstmt = null;
		Attachment attach = null;
		int result = 0;
		String sql = prop.getProperty("groupBoardEnroll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getStudy_Group_No());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getWriter());
			pstmt.setString(4, board.getContent());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BoardException("그룹 게시글 업로드 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		
		return result;
	}


	public int selectLastGroupBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectLastGroupBoardNo");
		ResultSet rset = null;
		int boardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new BoardException("최근 게시글 번호 조회 오류!",e);
		}
		finally {
			close(pstmt);
		}
		return boardNo;
	}


	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, attach.getStudy_Group_No());
			pstmt.setInt(2, attach.getBoard_No());
			pstmt.setString(3, attach.getOriginal_filename());
			pstmt.setString(4, attach.getRenamed_filename());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException("그룹 첨부파일 업로드 오류!",e);
		}
		return result;
	}


	public List<Attachment> selectAttachmentByBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAttachmentByBoard");
		ResultSet rset = null;
		List<Attachment> attachments = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Attachment attach = new Attachment();
				attach.setNo(rset.getInt("no"));
				attach.setStudy_Group_No(rset.getInt("study_group_no"));
				attach.setBoard_No(rset.getInt("board_no"));
				attach.setOriginal_filename(rset.getString("original_filename"));
				attach.setRenamed_filename(rset.getString("renamed_filename"));
				attach.setReg_Date(rset.getDate("reg_date"));
				
				attachments.add(attach);
				
			}
			
		} catch (SQLException e) {
			throw new BoardException("삭제를 위한 첨부 파일 조회 오류!",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return attachments;
	}


	public int deleteBoard(Connection conn, int no) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BoardException("그룹 게시글 삭제 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public int updateGroupBoard(Connection conn, StudyGroupBoard board) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateGroupBoard");
		
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BoardException("그룹 게시글 수정 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public int deleteAttachment(Connection conn, int delFileNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAttachment");
		
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, delFileNo);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BoardException("그룹 첨부 파일 삭제 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public List<StudyGroupBoard> selectMyboardList(Connection conn, Map<String, Integer> param, int studyNo, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectMyboardList");
		ResultSet rset = null;
		
		List<StudyGroupBoard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studyNo);
			pstmt.setString(2, memberId);
			pstmt.setInt(3, param.get("start"));
			pstmt.setInt(4, param.get("end"));

			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				StudyGroupBoard board = new StudyGroupBoard();
				board.setBoardNo(rset.getInt("group_board_no"));
				board.setStudy_Group_No(rset.getInt("study_group_no"));
				board.setTitle(rset.getString("title"));
				board.setWriter(rset.getString("writer"));
				board.setContent(rset.getString("content"));
				board.setRead_count(rset.getInt("read_count"));
				board.setReg_Date(rset.getDate("reg_date"));
				
				board.setAttachCount(rset.getInt("attach_count"));
				list.add(board);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
		

		
		
}
