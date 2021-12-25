package com.kh.member.model.vo;

import java.sql.Date;

public class StudyGroupAlarm extends Member{
	
	private String groupleader;

	public StudyGroupAlarm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudyGroupAlarm(String member_id, String member_name, int study_group) {
		super(member_id, member_name, study_group);
		// TODO Auto-generated constructor stub
	}

	public StudyGroupAlarm(String member_id, String password, String member_name, String member_role, String gender,
			String language, String email, String phone, String address, Date enroll_date, int study_group) {
		super(member_id, password, member_name, member_role, gender, language, email, phone, address, enroll_date, study_group);
		// TODO Auto-generated constructor stub
	}

	public StudyGroupAlarm(String member_id, String member_name, int study_group, String groupleader) {
		super(member_id, member_name, study_group);
		this.groupleader = groupleader;
	}

	public String getGroupleader() {
		return groupleader;
	}

	public void setGroupleader(String groupleader) {
		this.groupleader = groupleader;
	}

	@Override
	public String toString() {
		return "StudyGroupAlarm [groupleader=" + groupleader + "]";
	}

	

	
	

}
