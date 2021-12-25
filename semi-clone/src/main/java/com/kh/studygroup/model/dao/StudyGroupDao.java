package com.kh.studygroup.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;
import com.kh.studygroup.model.exception.GroupException;
import com.kh.studygroup.model.vo.StudyGroup;
import com.kh.studygroup.model.vo.StudyGroupMember;

public class StudyGroupDao {

	private static Properties prop = new Properties();
	
	public StudyGroupDao(){
		// /build/classes 하위에서 파일을 조회
		String filepath = MemberDao.class.getResource("/study-group-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int InsertGroup(StudyGroup group, Connection conn) {
	
		String sql = prop.getProperty("InsertGroup");
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, group.getGroup_name());
			pstmt.setInt(2, group.getMax_member());
			pstmt.setString(3, group.getStatus());
			pstmt.setString(4, group.getArea());
			pstmt.setString(5, group.getLanguage());
			pstmt.setString(6, group.getOn_off());
			
			
			result = pstmt.executeUpdate();
			System.out.println("InsertGroup Dao = " + result);
			
		}
		 catch (SQLException e) {
				throw new GroupException("그룹 생성 오류!",e);
			}
		finally {
			close(pstmt);
			
		}
		
		return result;
	}

	public int selectLastGroupNo(StudyGroup group, Connection conn) {
		
		String sql = prop.getProperty("selectLastGroupNo");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int num = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				num = rset.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			throw new GroupException("최근 그룹 번호 조회 오류!",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return num;
	}

	public int InsertGroupMember(Connection conn, Member member, String adminRole, int groupNum) {
		
		String sql = prop.getProperty("InsertGroupMember");

		PreparedStatement pstmt = null;
		int result = 0;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,groupNum);
			pstmt.setString(2, member.getMember_id());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, adminRole);
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new GroupException("그룹 멤버 업데이트 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int UpdateMemberStudyGroup(Connection conn, int groupNum, Member member) {
		String sql = prop.getProperty("UpdateMemberStudyGroup");

		PreparedStatement pstmt = null;
		int result = 0;
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, groupNum);
			pstmt.setString(2, member.getMember_id());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new GroupException("멤버 스터디 그룹 업데이트 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		
		
		return result;
		
	}

	public List<StudyGroupMember> selectAllGroupMember(Connection conn, int studyGroup) {
		String sql = prop.getProperty("selectAllGroupMember");

		PreparedStatement pstmt = null;
		List<StudyGroupMember> MemberList = new ArrayList<>();
		ResultSet rset = null;

		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studyGroup);
			rset = pstmt.executeQuery();
		
			while(rset.next()) {
				StudyGroupMember member = new StudyGroupMember();
				member.setGroupNum(rset.getInt("group_member_no"));
				member.setGroupMemberId(rset.getString("group_member_id"));
				member.setGroupMemberName(rset.getString("group_member_name"));
				member.setStudyTime(rset.getString("group_member_study_time"));
				member.setMemberRole(rset.getString("group_member_role"));
				
				MemberList.add(member);
				
			}
			
		} catch (SQLException e) {
			throw new GroupException("스터디 그룹 멤버 출력 오류!",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		return MemberList;
	}

	public String selectStudyTime(Connection conn, String memberId) {
		String sql = prop.getProperty("selectStudyTime");

		PreparedStatement pstmt = null;
		String dbTime = null;
		ResultSet rset = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				dbTime = rset.getString("group_member_study_time");
				
			}
			
			
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new GroupException("스터디 그룹 멤버 공부시간 출력 오류!",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return dbTime;
	}

	public int insertStudyTime(Connection conn, String memberId, String time) {
		String sql = prop.getProperty("insertStudyTime");

		PreparedStatement pstmt = null;
		String dbTime = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, time);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			System.out.println("[StudyGroup@Dao] result = "+ result);
			
		} 
		catch (SQLException e) {
			throw new GroupException("스터디 그룹 멤버 공부시간 입력 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		return result;
	}

	public StudyGroup selectOneGroup(Connection conn, int groupNo) {
		String sql = prop.getProperty("selectOneGroup");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		StudyGroup studyGroup = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
	
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				studyGroup = new StudyGroup();
				studyGroup.setGroup_number(rset.getInt("group_no"));
				studyGroup.setGroup_name(rset.getString("group_name"));
				studyGroup.setMax_member(rset.getInt("max_member"));
				studyGroup.setNow_member(rset.getInt("now_member"));
				studyGroup.setStatus(rset.getString("recruitment_status"));
				studyGroup.setArea(rset.getString("area"));
				studyGroup.setLanguage(rset.getString("language"));
				studyGroup.setOn_off(rset.getString("on_off"));
				
			}
			
		} catch (SQLException e) {
			throw new GroupException("스터디 그룹 가져오기 오류!",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		return studyGroup;
	}

	public List<Member> applicantList(Connection conn, String leaderId) {
		String sql = prop.getProperty("applicantList");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Member> list = new ArrayList<>();
		Member member = null;

		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, leaderId);
			
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
				
				list.add(member);
				
			}
			
			
		} catch (SQLException e) {
			throw new GroupException("신청자 목록 가져오기 오류!",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int deleteApplicant(Connection conn, String leaderId, String memberId) {
		String sql = prop.getProperty("deleteApplicant");

		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, leaderId);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new GroupException("신청자 목록 삭제 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectOneMember(Connection conn, String memberId) {
		String sql = prop.getProperty("selectOneMember");

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				member = new Member();
				
				member.setMember_id(rset.getString("member_id"));
				member.setMember_name(rset.getString("member_name"));
				
			}
			
		}  catch (SQLException e) {
			throw new GroupException("회원 한명 정보 가져오기 오류!",e);
		}
		finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return member;
	}

	public int updateApplicant(Connection conn, int studyGroup, Member member) {
		String sql = prop.getProperty("updateApplicant");

		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studyGroup);
			pstmt.setString(2, member.getMember_id());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, "U");
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new GroupException("신청자 목록 삭제 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectMemberRole(Connection conn, String memberId) {
		String sql = prop.getProperty("selectMemberRole");

		PreparedStatement pstmt = null;
		String memberRole = null;
		ResultSet rset;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				memberRole = rset.getString(1);
			}
			
		}catch (SQLException e) {
			throw new GroupException("멤버 권한 조회 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		
		return memberRole;
	}

	public int deleteGroupMember(Connection conn, int studyGroup, String memberId) {
		String sql = prop.getProperty("deleteGroupMember");

		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			pstmt.setInt(2, studyGroup);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new GroupException("멤버 삭제 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		
		
		
		return result;
	}

	public int deleteMemberGroupNo(Connection conn, String memberId) {
		String sql = prop.getProperty("deleteMemberGroupNo");

		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new GroupException("멤버 그룹번호 삭제 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		
		
		
		return result;
	}

	public int updateNowMember(Connection conn, int studyGroup) {
		String sql = prop.getProperty("updateNowMember");

		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studyGroup);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new GroupException("멤버 추가 카운트 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		
		
		
		return result;
	}

	public int deleteNowMember(Connection conn, int studyGroup) {
		String sql = prop.getProperty("deleteNowMember");

		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studyGroup);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new GroupException("now 멤버 삭제 카운트 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		
		
		
		return result;
	}

	public int updateKolaMember(Connection conn, Member member) {
		String sql = prop.getProperty("updateKolaMember");

		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, member.getStudy_group());
			pstmt.setString(2, member.getMember_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new GroupException("코라 멤버 업데이트 오류!",e);
		}
		finally {
			close(pstmt);
		}
		
		
		
		
		
		return result;
	}

}
