package com.kh.studygroup.model.vo;

public class Alram {
	private String group_leader_id;
	private String member_id;
	public Alram() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Alram(String group_leader_id, String member_id) {
		super();
		this.group_leader_id = group_leader_id;
		this.member_id = member_id;
	}
	public String getGroup_leader_id() {
		return group_leader_id;
	}
	public void setGroup_leader_id(String group_leader_id) {
		this.group_leader_id = group_leader_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Override
	public String toString() {
		return "Alram [group_leader_id=" + group_leader_id + ", member_id=" + member_id + "]";
	}
	
	

}
