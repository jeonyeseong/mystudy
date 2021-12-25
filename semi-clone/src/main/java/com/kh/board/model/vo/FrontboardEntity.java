package com.kh.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class FrontboardEntity implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private int no;
	private String title;
	private String writer;
	private String content;
	private int readCount;
	private Date regDate;
	private int group_no;
	public FrontboardEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FrontboardEntity(int no, String title, String writer, String content, int readCount, Date regDate,
			int group_no) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.readCount = readCount;
		this.regDate = regDate;
		this.group_no = group_no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	@Override
	public String toString() {
		return "FrontboardEntity [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", readCount=" + readCount + ", regDate=" + regDate + ", group_no=" + group_no + "]";
	}
	
	



}
