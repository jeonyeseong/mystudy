package com.gaji.mini.member.model.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gaji.mini.item.model.vo.Item;
import com.gaji.mini.post.controller.Board;
import com.gaji.mini.post.model.vo.Post;

public class Seller extends Member {

	private float manner = 36.5f;
	private List<Item> sellList;
	private List<Integer> postedList = new ArrayList<>();

	public Seller(String ID, String pw, String name) {
		super(ID, pw, name);
	}

	public List<Item> getSellList() {
		return sellList;
	}

	public List<Integer> getPostedList() {
		return postedList;
	}

	public void addToSellList(Item item) {
		// addToSellList(item);
		sellList.add(item);
	}

	public void addToPostedList(int postNo) {
		postedList.add(postNo);
	}

	public void removeFromPostedList(int postNo) {
		postedList.remove(Integer.valueOf(postNo));
	}

	public int withDraw() {
		// Long drawMoney = sc.nextInt();
		// return this.getMoney() - drawMoney;
		return 0;
	}
}
