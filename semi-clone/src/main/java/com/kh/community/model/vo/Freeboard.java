package com.kh.community.model.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Freeboard extends FreeboardEntity implements Serializable {

	private int attachCount; // 첨부파일수
	private List<Attachment> attachments;
	private int commentCount;
	
	public Freeboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Freeboard(int no, String title, String writer, String content, int readCount, Date regDate,
			int likeCount) {
		super(no, title, writer, content, readCount, regDate, likeCount);
		// TODO Auto-generated constructor stub
	}
	

	public Freeboard(int no, String title, String writer, String content, int readCount, Date regDate,
			int likeCount, int attachCount, List<Attachment> attachments, int commentCount) {
		super(no, title, writer, content, readCount, regDate, likeCount);
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
		return "freeboard [" + super.toString() 
		     + ", attachCount=" + attachCount 
		     + ", commentCount=" + commentCount 
		     + ", attachments=" + attachments + "]";
	}
	
	
	
}
