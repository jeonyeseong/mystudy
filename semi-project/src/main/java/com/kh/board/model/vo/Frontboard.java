package com.kh.board.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class Frontboard extends FrontboardEntity implements Serializable{
	

	private int comment_count;
	private List<Attachment> attachments;
	private String language;
	private String area;
	private int max_member;
	private int now_member;
	private String recruitment_status;
	private int group_no_1;
	private String group_name;
	private String on_off;
	public Frontboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Frontboard(int no, String title, String writer, String content, int readCount, Date regDate, int group_no) {
		super(no, title, writer, content, readCount, regDate, group_no);
		// TODO Auto-generated constructor stub
	}
	public Frontboard(int comment_count, List<Attachment> attachments, String language, String area, int max_member,
			int now_member, String recruitment_status, int group_no_1, String group_name, String on_off) {
		super();
		this.comment_count = comment_count;
		this.attachments = attachments;
		this.language = language;
		this.area = area;
		this.max_member = max_member;
		this.now_member = now_member;
		this.recruitment_status = recruitment_status;
		this.group_no_1 = group_no_1;
		this.group_name = group_name;
		this.on_off = on_off;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public String getRecruitment_status() {
		return recruitment_status;
	}
	public void setRecruitment_status(String recruitment_status) {
		this.recruitment_status = recruitment_status;
	}
	public int getGroup_no_1() {
		return group_no_1;
	}
	public void setGroup_no_1(int group_no_1) {
		this.group_no_1 = group_no_1;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getOn_off() {
		return on_off;
	}
	public void setOn_off(String on_off) {
		this.on_off = on_off;
	}
	@Override
	public String toString() {
		return "Frontboard [comment_count=" + comment_count + ", attachments=" + attachments + ", language=" + language
				+ ", area=" + area + ", max_member=" + max_member + ", now_member=" + now_member
				+ ", recruitment_status=" + recruitment_status + ", group_no=" + group_no_1 + ", group_name="
				+ group_name + ", on_off=" + on_off + "]";
	}
	
	
	
	
	
	
	
	
	
	
}