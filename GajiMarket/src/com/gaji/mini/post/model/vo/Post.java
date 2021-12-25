package com.gaji.mini.post.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.gaji.mini.item.model.vo.Item;
import com.gaji.mini.member.model.vo.Seller;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int postNo; // Key Value
	private Item item;
	private String postedBy;
	private String title;
	private String content;
	private boolean isSold;
	private LocalDateTime postedAt;

	// public Post(Item item, Seller postedBy, String title, String content) {
	// this.item = item;
	// this.postedBy = postedBy;
	// this.title = title;
	// this.content = content;
	// }

	public Post(int postNo, Item item, String postedBy, String title, String content) {
		this.postNo = postNo;
		this.item = item;
		this.postedBy = postedBy;
		this.title = title;
		this.content = content;
	}

	public int getPostNo() {
		return this.postNo;
	}

	// ! postNo는 controller에 의해서 결정되고 key값이기 때문에 set은 불필요
	// public void setPostNo(int postNo) {
	// this.postNo = postNo;
	// }

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getPostedBy() {
		return this.postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSold() {
		return this.isSold;
	}

	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}

	public String getPostedAt() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
		String formatted = this.postedAt.format(formatter);
		return formatted;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	@Override
	public String toString() {
		return postNo + ". " + title + postedBy;
	}

}
