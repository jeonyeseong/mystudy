package com.kh.studygroup.board.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class StudyGroupBoard extends StudyGroupBoard_Entity implements Serializable {

	
	private int attachCount;	// 첨부 파일수
	private List<Attachment> attachments;
	private int commentCount;
	
	
	public StudyGroupBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudyGroupBoard(int boardNo, int study_Group_No, String title, String writer, String content, int read_count) {
		super(boardNo, study_Group_No, title, writer, content, read_count);
		// TODO Auto-generated constructor stub
	}
	public StudyGroupBoard(int boardNo, int study_Group_No, String title, String writer, String content, int read_count,
			Date reg_Date) {
		super(boardNo, study_Group_No, title, writer, content, read_count, reg_Date);
		// TODO Auto-generated constructor stub
	}
	public StudyGroupBoard(int boardNo, int study_Group_No, String title, String writer, String content, int read_count,
			Date reg_Date, int attachCount, List<Attachment> attachments, int commentCount) {
		super(boardNo, study_Group_No, title, writer, content, read_count, reg_Date);
		this.attachCount = attachCount;
		this.attachments = attachments;
		this.commentCount = commentCount;
	}
	public int getAttachCount() {
		return attachCount;
	}
	public void setAttachCount(int attachCount) {
		this.attachCount = attachCount;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "StudyGroupBoard [attachCount=" + attachCount + ", attachments=" + attachments + ", commentCount="
				+ commentCount + "]";
	}
	
	
	
	
}
