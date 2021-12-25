package com.kh.studygroup.model.vo;

public class StudyGroupMember {
	
	private int groupNum;
	private String groupMemberId;
	private String groupMemberName;
	private String studyTime;
	private String MemberRole;
	public StudyGroupMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudyGroupMember(int groupNum, String groupMemberId, String groupMemberName, String studyTime,
			String memberRole) {
		super();
		this.groupNum = groupNum;
		this.groupMemberId = groupMemberId;
		this.groupMemberName = groupMemberName;
		this.studyTime = studyTime;
		this.MemberRole = memberRole;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public String getGroupMemberId() {
		return groupMemberId;
	}
	public void setGroupMemberId(String groupMemberId) {
		this.groupMemberId = groupMemberId;
	}
	public String getGroupMemberName() {
		return groupMemberName;
	}
	public void setGroupMemberName(String groupMemberName) {
		this.groupMemberName = groupMemberName;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
	public String getMemberRole() {
		return MemberRole;
	}
	public void setMemberRole(String memberRole) {
		MemberRole = memberRole;
	}
	
	
	

}
