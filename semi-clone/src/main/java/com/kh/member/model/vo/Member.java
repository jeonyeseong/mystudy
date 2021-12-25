package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
	
	private String member_id;
	private String password;
	private String member_name;
	private String member_role;
	private String gender;
	private String language;
	private String email;
	private String phone;
	private String address;
	private Date enroll_date;
	private int study_group;
	
	
	public Member(String member_id, String member_name) {
		super();
		this.member_id = member_id;
		this.member_name = member_name;
	}
	
	
	public Member(String member_id, String member_name,int study_group) {
		super();
		this.member_id = member_id;
		this.member_name = member_name;
		this.study_group = study_group;
	}
	
	public Member(String member_id, String password, String member_name, String member_role, String gender,
			String language, String email, String phone, String address, Date enroll_date, int study_group) {
		super();
		this.member_id = member_id;
		this.password = password;
		this.member_name = member_name;
		this.member_role = member_role;
		this.gender = gender;
		this.language = language;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.enroll_date = enroll_date;
		this.study_group = study_group;
	}
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_role() {
		return member_role;
	}
	public void setMember_role(String member_role) {
		this.member_role = member_role;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getEnroll_date() {
		return enroll_date;
	}
	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}
	public int getStudy_group() {
		return study_group;
	}
	public void setStudy_group(int study_group) {
		this.study_group = study_group;
	}
	@Override
	public String toString() {
		return "Member [member_id=" + member_id + ", password=" + password + ", member_name=" + member_name
				+ ", member_role=" + member_role + ", gender=" + gender + ", language=" + language + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", enroll_date=" + enroll_date + ", study_group="
				+ study_group + "]";
	}
	
	
	
	
	

}
