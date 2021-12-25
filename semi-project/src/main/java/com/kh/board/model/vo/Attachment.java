package com.kh.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int frontboardNo;
	private String originalFilename; 	// 사용자가 업로드한 파일명
	private String renamedFilename;		// 서버컴퓨터 저장된 파일명
	private Date regDate;
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachment(int frontboardNo, String originalFilename, String renamedFilename, Date regDate) {
		super();
		this.frontboardNo = frontboardNo;
		this.originalFilename = originalFilename;
		this.renamedFilename = renamedFilename;
		this.regDate = regDate;
	}

	public int getFrontboardNo() {
		return frontboardNo;
	}

	public void setFrontboardNo(int frontboardNo) {
		this.frontboardNo = frontboardNo;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getRenamedFilename() {
		return renamedFilename;
	}

	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Attachment [frontboardNo=" + frontboardNo + ", originalFilename=" + originalFilename
				+ ", renamedFilename=" + renamedFilename + ", regDate=" + regDate + "]";
	}
	
	
	
}