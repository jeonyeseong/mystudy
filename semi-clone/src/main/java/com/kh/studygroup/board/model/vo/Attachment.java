package com.kh.studygroup.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int no;
	private int study_Group_No;
	private int board_No;
	private String original_filename;
	private String renamed_filename;
	private Date reg_Date;
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Attachment(int no, int study_Group_No, int board_No, String original_filename, String renamed_filename,
			Date reg_Date) {
		super();
		this.no = no;
		this.study_Group_No = study_Group_No;
		this.board_No = board_No;
		this.original_filename = original_filename;
		this.renamed_filename = renamed_filename;
		this.reg_Date = reg_Date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getStudy_Group_No() {
		return study_Group_No;
	}

	public void setStudy_Group_No(int study_Group_No) {
		this.study_Group_No = study_Group_No;
	}

	public int getBoard_No() {
		return board_No;
	}

	public void setBoard_No(int board_No) {
		this.board_No = board_No;
	}

	public String getOriginal_filename() {
		return original_filename;
	}

	public void setOriginal_filename(String original_filename) {
		this.original_filename = original_filename;
	}

	public String getRenamed_filename() {
		return renamed_filename;
	}

	public void setRenamed_filename(String renamed_filename) {
		this.renamed_filename = renamed_filename;
	}

	public Date getReg_Date() {
		return reg_Date;
	}

	public void setReg_Date(Date reg_Date) {
		this.reg_Date = reg_Date;
	}

	@Override
	public String toString() {
		return "Attachment [no=" + no + ", study_Group_No=" + study_Group_No + ", board_No=" + board_No
				+ ", original_filename=" + original_filename + ", renamed_filename=" + renamed_filename + ", reg_Date="
				+ reg_Date + "]";
	}
	
	
	
	
	
	
}
