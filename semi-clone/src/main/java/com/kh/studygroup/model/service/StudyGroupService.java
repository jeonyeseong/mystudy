package com.kh.studygroup.model.service;

import static com.kh.common.JDBCTemplate.*;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.member.model.vo.Member;
import com.kh.studygroup.model.dao.StudyGroupDao;
import com.kh.studygroup.model.vo.StudyGroup;
import com.kh.studygroup.model.vo.StudyGroupMember;

public class StudyGroupService {
	
	public static final String USER_ROLE = "U";
	public static final String ADMIN_ROLE = "A";
	
	private static StudyGroupDao groupDao = new StudyGroupDao();
	
	
	public int InsertGroup(StudyGroup group,Member member) {
		Connection conn = getConnection();
		int groupNum = 0;
		int result = 0;
		try {
			
			result = groupDao.InsertGroup(group,conn);
			
			System.out.println("[studyGroup@Service] result1 = " + result);
			groupNum = groupDao.selectLastGroupNo(group,conn);
			
			member.setStudy_group(groupNum); // servlet에서 참조할 수 있도록 한다.
			
			
			groupDao.InsertGroupMember(conn,member, ADMIN_ROLE,groupNum);
			groupDao.UpdateMemberStudyGroup(conn,groupNum,member);

			
		
			commit(conn);
			
		}
		catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		
		
		
		return groupNum;
	}


	public List<StudyGroupMember> selectAllGroupMember(int studyGroup) {
		Connection conn = getConnection();
		List<StudyGroupMember> MemberList = null;
		
		try {
		MemberList = groupDao.selectAllGroupMember(conn,studyGroup);
		
		}
		
		catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		
		
		
		return MemberList;
	}


	public String selectStudyTime(String memberId) {
		Connection conn = getConnection();
		String dbtime = null;
		try {
		dbtime = groupDao.selectStudyTime(conn,memberId);
		
		}
		
		catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		
		
		return dbtime;
	}


	public int insertStudyTime(String memberId, String time) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = groupDao.insertStudyTime(conn,memberId,time);
			commit(conn);
		}
		
		catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		
		
		return result;	}


	public StudyGroup selectOneGroup(int groupNo) {
		Connection conn = getConnection();
	
		conn = getConnection();
		StudyGroup group = groupDao.selectOneGroup(conn,groupNo);
					

		
		close(conn);
		
		return group;
	}


	public List<Member> applicantList(String leaderId) {
		Connection conn = getConnection();
		
		conn = getConnection();
		List<Member> list = groupDao.applicantList(conn,leaderId);
					

		
		close(conn);
		
		return list;
	}


	public int deleteApplicant(String leaderId, String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			conn = getConnection();
			result = groupDao.deleteApplicant(conn,leaderId,memberId);
			
			commit(conn);
		}	catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		return result;
	}


	public Member selectOneMember(String memberId) {
		Connection conn = getConnection();
		Member member = groupDao.selectOneMember(conn,memberId);
		
		close(conn);
		return member;
	}


	public int updateApplicant(int studyGroup, Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			conn = getConnection();
			result = groupDao.updateApplicant(conn,studyGroup,member);
			member.setStudy_group(studyGroup);
			result = groupDao.updateKolaMember(conn,member);
			commit(conn);
		}	catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		return result;
	}



	public String selectMemberRole(String memberId) {
		Connection conn = getConnection();
		String memberRole = groupDao.selectMemberRole(conn,memberId);
		close(conn);
		return memberRole;
	}


	public int deleteGroupMember(int studyGroup, String memberId) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = groupDao.deleteGroupMember(conn,studyGroup,memberId);
			result = groupDao.deleteMemberGroupNo(conn,memberId);
			result = groupDao.deleteNowMember(conn,studyGroup);
			
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		return result;
	}


	public int updateNowMember(int studyGroup) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = groupDao.updateNowMember(conn,studyGroup);
		
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}
		finally {
			close(conn);
			
		}
		return result;
		
	}

}
