package com.kh.board.model.dao;



import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

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

import com.kh.board.model.exception.FrontboardException;
import com.kh.board.model.vo.Frontboard;
import com.kh.board.model.vo.FrontboardComment;
import com.kh.community.model.exception.FreeboardException;
import com.kh.community.model.vo.FreeboardComment;
import com.kh.member.model.vo.Member;


public class FrontboardDao {
	
	private Properties prop = new Properties();
	
	/**
	 * board-query.properties 파일의 key=value 쿼리를 가져온다.
	 * - 클래스객체를 통해 build-path에 배포된 파일에 접근할 것!
	 * 
	 */
	public FrontboardDao() {
		File file = new File(FrontboardDao.class.getResource("/frontboard-query.properties").getPath());
		try {
			prop.load(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Frontboard> selectAllBoard(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllBoard");
		ResultSet rset = null;
		List<Frontboard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Frontboard frontboard = new Frontboard();
				frontboard.setNo(rset.getInt("no")); // number
				frontboard.setTitle(rset.getString("title")); // varchar2, char
				frontboard.setWriter(rset.getString("writer"));
				frontboard.setContent(rset.getString("content"));
				frontboard.setReadCount(rset.getInt("read_count"));
				frontboard.setRegDate(rset.getDate("reg_date"));
				frontboard.setLanguage(rset.getString("language"));
				frontboard.setArea(rset.getString("area"));
				frontboard.setMax_member(rset.getInt("Max_member"));
				frontboard.setGroup_no(rset.getInt("group_no"));
				frontboard.setComment_count(rset.getInt("comment_count"));
				frontboard.setNow_member(rset.getInt("Now_member"));

				list.add(frontboard);
			}
			
		} catch (SQLException e) {
			throw new FrontboardException("게시글 목록 조회 오류!", e);
		} finally {
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
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalCount;
	}

	public int insertBoard(Connection conn, Frontboard frontboard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoard");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, frontboard.getTitle());
			pstmt.setString(2, frontboard.getWriter());
			pstmt.setString(3, frontboard.getContent());
			pstmt.setInt(4, frontboard.getGroup_no());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new FrontboardException("게시물 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastBoardNo(Connection conn){
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectLastBoardNo");
		ResultSet rset = null;
		int boardNo = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new FrontboardException("최근 게시글번호 조회 오류!", e);
		} finally {
			close(pstmt);
		}
		return boardNo;
	}

	public List<Frontboard> myboardlist(Connection conn, Map<String, Integer> param, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("myboardlist");
		ResultSet rset = null;
		List<Frontboard> list = new ArrayList<>();
		System.out.println("myboardList sql = " + sql);
		System.out.println(memberId);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, param.get("start"));
			pstmt.setInt(3, param.get("end"));

			rset = pstmt.executeQuery();

			while(rset.next()) {
				Frontboard frontboard = new Frontboard();
				frontboard.setNo(rset.getInt("no")); // number
				frontboard.setTitle(rset.getString("title")); // varchar2, char
				frontboard.setWriter(rset.getString("writer"));
				frontboard.setContent(rset.getString("content"));
				frontboard.setReadCount(rset.getInt("read_count"));
				frontboard.setRegDate(rset.getDate("reg_date"));
				frontboard.setLanguage(rset.getString("language"));


				list.add(frontboard);
			}
			
		} catch (SQLException e) {
			throw new FrontboardException("게시글 목록 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
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
			throw new FrontboardException("조회수 증가 처리 오류!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Frontboard selectOneBoard(Connection conn, int no) {
		Frontboard frontboard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneBoard");
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, no);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				frontboard = new Frontboard();
				frontboard.setNo(rset.getInt("no"));
				frontboard.setTitle(rset.getString("title"));
				frontboard.setWriter(rset.getString("writer"));
				frontboard.setContent(rset.getString("content"));
				frontboard.setReadCount(rset.getInt("read_count"));
				frontboard.setRegDate(rset.getDate("reg_date"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		return frontboard;
	}

	public List<Frontboard> selectAllWatchBoard(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllWatchBoard");
		ResultSet rset = null;
		List<Frontboard> watchlist = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Frontboard frontboard = new Frontboard();
				frontboard.setNo(rset.getInt("no")); // number
				frontboard.setTitle(rset.getString("title")); // varchar2, char
				frontboard.setWriter(rset.getString("writer"));
				frontboard.setContent(rset.getString("content"));
				frontboard.setReadCount(rset.getInt("read_count"));
				frontboard.setRegDate(rset.getDate("reg_date"));
				frontboard.setLanguage(rset.getString("language"));
				frontboard.setArea(rset.getString("area"));
				frontboard.setMax_member(rset.getInt("Max_member"));
				frontboard.setGroup_no(rset.getInt("group_no"));
				frontboard.setComment_count(rset.getInt("comment_count"));
				frontboard.setNow_member(rset.getInt("Now_member"));

				
				watchlist.add(frontboard);
			}
			
		} catch (SQLException e) {
			throw new FrontboardException("게시글 목록 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return watchlist;
	}

	public List<Frontboard> selectAllBoardOnline(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllBoardOnline");
		ResultSet rset = null;
		List<Frontboard> listonline = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Frontboard frontboard = new Frontboard();
				frontboard.setNo(rset.getInt("no")); // number
				frontboard.setTitle(rset.getString("title")); // varchar2, char
				frontboard.setWriter(rset.getString("writer"));
				frontboard.setContent(rset.getString("content"));
				frontboard.setReadCount(rset.getInt("read_count"));
				frontboard.setRegDate(rset.getDate("reg_date"));	
				frontboard.setLanguage(rset.getString("language"));
				frontboard.setArea(rset.getString("area"));
				frontboard.setMax_member(rset.getInt("Max_member"));
				frontboard.setGroup_no(rset.getInt("group_no"));
				frontboard.setComment_count(rset.getInt("comment_count"));
				frontboard.setNow_member(rset.getInt("Now_member"));
				
				listonline.add(frontboard);
			}
			
		} catch (SQLException e) {
			throw new FrontboardException("게시글 목록 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return listonline;
	}

	public List<Frontboard> selectAllBoardOffline(Connection conn, Map<String, Integer> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllBoardOffline");
		ResultSet rset = null;
		List<Frontboard> listoffline = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.get("start"));
			pstmt.setInt(2, param.get("end"));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Frontboard frontboard = new Frontboard();
				frontboard.setNo(rset.getInt("no")); // number
				frontboard.setTitle(rset.getString("title")); // varchar2, char
				frontboard.setWriter(rset.getString("writer"));
				frontboard.setContent(rset.getString("content"));
				frontboard.setReadCount(rset.getInt("read_count"));
				frontboard.setRegDate(rset.getDate("reg_date"));
				frontboard.setComment_count(rset.getInt("comment_count"));
				frontboard.setLanguage(rset.getString("language"));
				frontboard.setArea(rset.getString("area"));
				frontboard.setMax_member(rset.getInt("Max_member"));
				frontboard.setGroup_no(rset.getInt("group_no"));
				frontboard.setNow_member(rset.getInt("Now_member"));
				

				listoffline.add(frontboard);
			}
			
		} catch (SQLException e) {
			throw new FrontboardException("게시글 목록 조회 오류!", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return listoffline;
	}

	public List<FrontboardComment> selectFrontBoardCommentList(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoardCommentList");
		ResultSet rset = null;
		List<FrontboardComment> commentList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				FrontboardComment bc = new FrontboardComment();
				bc.setNo(rset.getInt("no"));
				bc.setCommentLevel(rset.getInt("comment_level"));
				bc.setWriter(rset.getString("writer"));
				bc.setContent(rset.getString("content"));
				bc.setBoardNo(rset.getInt("board_no"));
				bc.setCommentRef(rset.getInt("comment_ref")); // 댓글인 경우 null이고, 이는 0으로 치환된다.
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

	public int insertFrontBoardComment(Connection conn, FrontboardComment bc) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoardComment");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getCommentLevel()); 	// 1, 2
			pstmt.setString(2, bc.getWriter()); 	// memberId
			pstmt.setString(3, bc.getContent()); 	// ..
			pstmt.setInt(4, bc.getBoardNo());		// boardNo
//			pstmt.setInt(5, bc.getCommentRef());	// 0
//			pstmt.setInt(5, bc.getCommentRef() == 0 ? null : bc.getCommentRef());	// NullPointerException
			pstmt.setObject(5, bc.getCommentRef() == 0 ? null : bc.getCommentRef());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteFrontBoardComment(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteBoardComment");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new FrontboardException("댓글 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteFrontBoard(Connection conn, int no) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoard"); 
		
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateFrontBoard(Connection conn, Frontboard frontboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateBoard"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, frontboard.getTitle());
			pstmt.setString(2, frontboard.getContent());
			pstmt.setInt(3, frontboard.getNo());
			
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

	

}
