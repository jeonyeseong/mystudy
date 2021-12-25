package com.kh.studygroup.model.vo;

public class StudyGroup {
	
	//StudyGroup(group_name,max_member,1,status,on_off,area,language);
	
	private int group_number;
	private String group_name;	
	private int max_member;
	private int now_member;
	private String status;
	private String area;
	private String language;
	private String on_off;
	
	
	public StudyGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public StudyGroup(String group_name, int max_member, int now_member, String status, String area, String language,
			String on_off) {
		super();
		this.group_name = group_name;
		this.max_member = max_member;
		this.now_member = now_member;
		this.status = status;
		this.area = area;
		this.language = language;
		this.on_off = on_off;
	}




	public StudyGroup(int group_number, String group_name, int max_member, int now_member, String status, String area,
			String language, String on_off) {
		super();
		this.group_number = group_number;
		this.group_name = group_name;
		this.max_member = max_member;
		this.now_member = now_member;
		this.status = status;
		this.area = area;
		this.language = language;
		this.on_off = on_off;
	}
	public int getGroup_number() {
		return group_number;
	}
	public void setGroup_number(int group_number) {
		this.group_number = group_number;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public int getMax_member() {
		return max_member;
	}
	public void setMax_member(int max_member) {
		this.max_member = max_member;
	}
	public int getNow_member() {
		return now_member;
	}
	public void setNow_member(int now_member) {
		this.now_member = now_member;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOn_off() {
		return on_off;
	}
	public void setOn_off(String on_off) {
		this.on_off = on_off;
	}
	@Override
	public String toString() {
		return "StudyGroup [group_number=" + group_number + ", group_name=" + group_name + ", max_member=" + max_member
				+ ", now_member=" + now_member + ", status=" + status + ", area=" + area + ", language=" + language
				+ ", on_off=" + on_off + "]";
	}
	
	
	
}
