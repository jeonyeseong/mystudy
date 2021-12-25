package com.kh.studygroup.board.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;



public class StudyGroupBoard_Entity implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private int boardNo;
	private int study_Group_No;
	private String title;
	private String writer;
	private String content;
	private int read_count;
	private Date reg_Date;
	
	
	public StudyGroupBoard_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudyGroupBoard_Entity(int boardNo, int study_Group_No, String title, String writer, String content,
			int read_count) {
		super();
		this.boardNo = boardNo;
		this.study_Group_No = study_Group_No;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.read_count = read_count;
	}
	public StudyGroupBoard_Entity(int boardNo, int study_Group_No, String title, String writer, String content,
			int read_count, Date reg_Date) {
		super();
		this.boardNo = boardNo;
		this.study_Group_No = study_Group_No;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.read_count = read_count;
		this.reg_Date = reg_Date;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getStudy_Group_No() {
		return study_Group_No;
	}
	public void setStudy_Group_No(int study_Group_No) {
		this.study_Group_No = study_Group_No;
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
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	public Date getReg_Date() {
		return reg_Date;
	}
	public void setReg_Date(Date reg_Date) {
		this.reg_Date = reg_Date;
	}
	@Override
	public String toString() {
		return "StudyGroupBoard_Entity [boardNo=" + boardNo + ", study_Group_No=" + study_Group_No + ", title=" + title
				+ ", writer=" + writer + ", content=" + content + ", read_count=" + read_count + ", reg_Date="
				+ reg_Date + "]";
	}
	

	
	

	

	
}
