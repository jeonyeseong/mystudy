package com.kh.admin.vo;

import java.sql.Date;

import com.kh.member.model.vo.Member;

public class Statistics extends Member{
	
	private String stat; //통계
	private int count;
	private int rank;
	public Statistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Statistics(String member_id, String password, String member_name, String member_role, String gender,
			String language, String email, String phone, String address, Date enroll_date, int study_group) {
		super(member_id, password, member_name, member_role, gender, language, email, phone, address, enroll_date, study_group);
		// TODO Auto-generated constructor stub
	}
	public Statistics(String member_id, String password, String member_name, String member_role, String gender,
			String language, String email, String phone, String address, Date enroll_date, int study_group, String stat,
			int count, int rank) {
		super(member_id, password, member_name, member_role, gender, language, email, phone, address, enroll_date,
				study_group);
		this.stat = stat;
		this.count = count;
		this.rank = rank;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "Statistics [stat=" + stat + ", count=" + count + ", rank=" + rank + "]";
	}
	
	

	
	

	
	
	
	

}
