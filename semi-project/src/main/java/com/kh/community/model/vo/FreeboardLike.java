package com.kh.community.model.vo;

public class FreeboardLike {
	
	private int bno;
	private String checkUser;
	private int likeFlag;
	
	public FreeboardLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FreeboardLike(int bno, String checkUser, int likeFlag) {
		super();
		this.bno = bno;
		this.checkUser = checkUser;
		this.likeFlag = likeFlag;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public int getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(int likeFlag) {
		this.likeFlag = likeFlag;
	}

	@Override
	public String toString() {
		return "FreeboardLike [bno=" + bno + ", checkUser=" + checkUser + ", likeFlag=" + likeFlag + "]";
	}

	
}
