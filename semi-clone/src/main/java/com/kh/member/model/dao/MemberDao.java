package com.kh.member.model.dao;

import static com.kh.common.JDBCTemplate.close;

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

import static com.kh.common.JDBCTemplate.*;

import com.kh.admin.vo.Statistics;
import com.kh.member.model.exception.MemberException;

import com.kh.member.model.vo.Member;
import com.kh.studygroup.model.vo.Alram;

public class MemberDao {

	private static Properties prop = new Properties();
	
	public MemberDao(){
		// /build/classes 하위에서 파일을 조회
		String filepath = MemberDao.class.getResource("/member-query.properties").getPath();

		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Member selectOneMember(String memberId,Connection conn) {

		String sql = prop.getProperty("SelectOneMember");
		PreparedStatement pstmt = null;
		Member member = null;
		ResultSet rset = null;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,memberId);
			
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				member = new Member();
				
				member.setMember_id(rset.getString("member_id"));
				member.setPassword(rset.getString("password"));
				member.setMember_name(rset.getString("member_name"));
				member.setMember_role(rset.getString("member_role"));
				member.setGender(rset.getString("gender"));
				member.setLanguage(rset.getString("language"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setAddress(rset.getString("address"));
				member.setEnroll_date(rset.getDate("enroll_date"));
				member.setStudy_group(rset.getInt("study_group"));		
				
			}
			
			
		} catch (SQLException e) {
			throw new MemberException("로그인 오류 발생",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		
		return member;
	}



	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMember_name());
			pstmt.setString(2, member.getGender());

			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getLanguage());
			pstmt.setString(7, member.getMember_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int updatePassword(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMember_id());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("비밀번호 수정 오류!", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, memberId);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원 삭제 오류!", e);
		} finally {
			close(pstmt);
		} 
		
		return result;
	}


	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		int result = 0;
		
		try {
			// 1.PreapredStatement객체 준비 - sql값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, member.getMember_role());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getLanguage());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getPhone());
			pstmt.setString(9, member.getAddress());
			
			// 2.실행 - executeUpdate
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException("회원가입 오류!", e);
		} finally {
			// 3.자원반납
			close(pstmt);
		}
		
		
		return result;
	}


	public List<Member> searchMember(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("searchMember");
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		
		String searchType = (String) param.get("searchType");
		String searchKeyword = (String) param.get("searchKeyword");
		switch(searchType) {
		case "memberId": sql += " member_id like '%" + searchKeyword + "%'"; break;
		case "memberName": sql += " member_name like '%" + searchKeyword + "%'"; break;
		case "gender": sql += " gender = '" + searchKeyword + "'"; break;
		case "rank": sql = " gender = '" + searchKeyword + "'"; break;
		}
		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member member = new Member();
				member.setMember_id(rset.getString("member_id"));
				member.setPassword(rset.getString("password"));
				member.setMember_name(rset.getString("member_name"));
				member.setMember_role(rset.getString("member_role"));
				member.setGender(rset.getString("gender"));
				
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setAddress(rset.getString("address"));
				member.setLanguage(rset.getString("language"));
				member.setEnroll_date(rset.getDate("enroll_date"));
				list.add(member);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}


	public List<Member> selectAllMember(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllMember");
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		try {
			// 1.pstmt객체생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("startNum"));
			pstmt.setInt(2, (int) param.get("endNum"));
			
			// 2.실행
			rset = pstmt.executeQuery();
			// 3.rset처리 : 하나의 레코드 -> vo객체하나 -> list에 추가
			while(rset.next()) {
				Member member = new Member();
				member.setMember_id(rset.getString("member_id"));
				member.setPassword(rset.getString("password"));
				member.setMember_name(rset.getString("member_name"));
				member.setMember_role(rset.getString("member_role"));
				member.setGender(rset.getString("gender"));
				member.setEmail(rset.getString("email"));
				member.setPhone(rset.getString("phone"));
				member.setAddress(rset.getString("address"));
				member.setLanguage(rset.getString("language"));
				member.setEnroll_date(rset.getDate("enroll_date"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원반납
			close(rset);
			close(pstmt);
		}
		return list;
	}


	public int selectTotalMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectTotalMemberCount");
		ResultSet rset = null;
		int totalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalCount = rset.getInt(1); // 컬럼인덱스 1부터
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalCount;
	}


	public int updateMemberRole(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMemberRole"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMember_role());
			pstmt.setString(2, member.getMember_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원권한변경 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<Statistics> Statistics(Connection conn, String searchType) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Statistics> stat = new ArrayList<>();
		String sql = "";
		switch(searchType) {
		case "language" : sql = prop.getProperty("languageStatistics"); break;
		case "enrolldate" : sql = prop.getProperty("enrolldateStatistics"); break;
		case "visitors" : sql = prop.getProperty("visitorStatistics"); break;
		}
		System.out.println("stat sql = " + sql);
		System.out.println("visitors".equals(searchType) ? "today" : searchType);
		try {
			pstmt = conn.prepareStatement(sql);
			// 2.실행
			rset = pstmt.executeQuery();
			// 3.rset처리 : 하나의 레코드 -> vo객체하나 -> list에 추가
			while(rset.next()) {
				Statistics s = new Statistics();
				s.setStat(rset.getString("visitors".equals(searchType) ? "today" : searchType));
				s.setCount(rset.getInt("count"));
				stat.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원반납
			close(rset);
			close(pstmt);
		}
		return stat;
	}


	public int insertAlram(Connection conn, String memberId, String leader) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertAlram"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, leader);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("회원권한변경 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<Alram> selectAllAlram(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllAlram");
		ResultSet rset = null;
		List<Alram> alramlist = new ArrayList<>();
		try {
			// 1.pstmt객체생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			// 2.실행
			rset = pstmt.executeQuery();
			// 3.rset처리 : 하나의 레코드 -> vo객체하나 -> list에 추가
			while(rset.next()) {
				Alram alram = new Alram();
				alram.setMember_id(rset.getString("member_id"));
				alramlist.add(alram);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원반납
			close(rset);
			close(pstmt);
		}
		return alramlist;
	}


	public Statistics selectVisitor(Connection conn) {
		String sql = prop.getProperty("selectVisitor");
		PreparedStatement pstmt = null;
		Statistics stat = null;
		ResultSet rset = null;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				stat = new Statistics();
				
				stat.setStat(rset.getString("today"));
				stat.setCount(rset.getInt("count"));				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		
		return stat;
	}


	public int updateVisitor(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateVisitor");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);	
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	public int insertVisitor(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertVisitor");
		int result = 0;
		
		try {
			// 1.PreapredStatement객체 준비 - sql값대입
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3.자원반납
			close(pstmt);
		}
		
		
		return result;
	}


	public List<Statistics> languageStatistics(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Statistics> stat = new ArrayList<>();
		String sql = prop.getProperty("languageStatistics");
		try {
			pstmt = conn.prepareStatement(sql);
			// 2.실행
			rset = pstmt.executeQuery();
			// 3.rset처리 : 하나의 레코드 -> vo객체하나 -> list에 추가
			while(rset.next()) {
				Statistics s = new Statistics();
				s.setStat(rset.getString("language"));
				s.setCount(rset.getInt("count"));
				stat.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원반납
			close(rset);
			close(pstmt);
		}
		return stat;
	}


	


}
