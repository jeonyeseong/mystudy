package com.kh.community.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Questionboard extends QuestionboardEntity implements Serializable {

	private int attachCount; // 첨부파일수
	private List<Attachment> attachments;
	private int commentCount;
	
	public Questionboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Questionboard(int no, String title, String writer, String content, int readCount, Date regDate, String ask,
			int likeCount) {
		super(no, title, writer, content, readCount, regDate, ask, likeCount);
		// TODO Auto-generated constructor stub
	}
	

	public Questionboard(int no, String title, String writer, String content, int readCount, Date regDate, String ask,
			int likeCount, int attachCount, List<Attachment> attachments, int commentCount) {
		super(no, title, writer, content, readCount, regDate, ask, likeCount);
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
		return "Questionboard [" + super.toString() 
		     + ", attachCount=" + attachCount 
		     + ", commentCount=" + commentCount 
		     + ", attachments=" + attachments + "]";
	}
	
	
	
}

