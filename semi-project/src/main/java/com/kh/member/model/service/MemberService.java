package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


import com.kh.member.model.vo.Member;
import com.kh.studygroup.model.vo.Alram;
import com.kh.admin.vo.Statistics;
import com.kh.member.model.dao.MemberDao;

import com.kh.member.model.vo.Member;

import com.kh.member.model.exception.MemberException;


public class MemberService {

	public static final String USER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	
	private static MemberDao memberDao = new MemberDao();
	
	public Member selectOneMember(String memberId) {
		
		Connection conn = getConnection();
		Member member = new Member();
		
		try {
			
			member = memberDao.selectOneMember(memberId,conn);
			
			
		}
		catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		
		
		
		return member;
	}


	public int updateMember(Member member) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = memberDao.updateMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	public int updatePassword(Member member) {
		Connection conn = null;
		int result = 0;;
		try {
			conn = getConnection();
			result = memberDao.updatePassword(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = null;
		int result = 0;
		try{
			conn = getConnection();
			result = memberDao.deleteMember(conn, memberId);
			if(result == 0)
				throw new MemberException("해당 회원은 존재하지 않습니다.");
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);			
		}
		return result;
	}
	
		public int insertMember(Member member) {
		Connection conn = null;
		int result = 0;
		try {
			// 1.Connection객체 생성
			conn = getConnection();
			// 2.Dao요청
			result = memberDao.insertMember(conn, member);
			// 3.트랜잭션처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			// 4.Connection 자원반납
			close(conn);
		}
		return result;
	}


		public List<Member> searchMember(Map<String, Object> param) {
			Connection conn = getConnection();
			List<Member> list = memberDao.searchMember(conn, param);
			close(conn);
			return list;
		}


		public List<Member> selectAllMember(Map<String, Object> param) {
			Connection conn = getConnection();
			List<Member> list = memberDao.selectAllMember(conn, param);
			close(conn);
			return list;
		}


		public int selectTotalMemberCount() {
			Connection conn = getConnection();
			int totalCount = memberDao.selectTotalMemberCount(conn);
			close(conn);
			return totalCount;
		}


		public int updateMemberRole(Member member) {
			Connection conn = null;
			int result = 0;
			try {
				conn = getConnection();
				result = memberDao.updateMemberRole(conn, member);
				commit(conn);
			} catch (Exception e) {
				rollback(conn);
				throw e;
			} finally {
				close(conn);
			}
			
			return result;
		}


		public List<Statistics> Statistics(String searchType) {
			Connection conn = getConnection();
			List<Statistics> stat = memberDao.Statistics(conn, searchType);
			close(conn);
			return stat;
		}
		
		public List<Statistics> languageStatistics() {
			Connection conn = getConnection();
			List<Statistics> stat = memberDao.languageStatistics(conn);
			close(conn);
			return stat;
		}


		public int insertAlram(String memberId, String leader) {
			Connection conn = getConnection();
			int result = 0;
			try {
				// 1.Connection객체 생성
				conn = getConnection();
				// 2.Dao요청
				
				result = memberDao.insertAlram(conn, memberId, leader);
				// 3.트랜잭션처리
				commit(conn);
			} catch (Exception e) {
				rollback(conn);
				throw e;
			} finally {
				// 4.Connection 자원반납
				close(conn);
			}
			return result;
		}


		public List<Alram> selectAllAlram(String memberId) {
			Connection conn = getConnection();
			List<Alram> alramlist = memberDao.selectAllAlram(conn, memberId);
			close(conn);
			return alramlist;
		}


		public int totalVisitor() {
			Connection conn = getConnection();
			int result = 0;
			try {
				// 1.Connection객체 생성
				conn = getConnection();
				// 2.Dao요청
				System.out.println("selectVisitor");
				Statistics stat = memberDao.selectVisitor(conn);
				if(stat != null) {
					result = memberDao.updateVisitor(conn);
					System.out.println("visitor count 완료");
				}
				else {
					System.out.println("updateVisitor");
					result = memberDao.insertVisitor(conn);
					result = memberDao.updateVisitor(conn);
					System.out.println("visitor insert후 count 완료");
				}
				// 3.트랜잭션처리
				commit(conn);
			} catch (Exception e) {
				rollback(conn);
				throw e;
			} finally {
				// 4.Connection 자원반납
				close(conn);
			}
			return result;
		}

}
