package com.kh.spring.board.model.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BoardEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int attachCount; // 게시물별 첨부파일 수

	public Board(int no, String title, String memberId, String content, Date regDate, int readCount, int attachCount) {
		super(no, title, memberId, content, regDate, readCount);
		this.attachCount = attachCount;
	}

	
	
	
}
