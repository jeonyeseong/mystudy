package com.gaji.mini.member.model.vo;

import java.util.*;

import com.gaji.mini.item.model.vo.Item;

public class Buyer extends Member {
	private List<Item> buyList = new ArrayList<>();;

	public Buyer(String ID, String pw, String name) {
		super(ID, pw, name);
		this.money = 10000;
	}

	// TODO: setSellList 쪽 참고
	public List<Item> getBuyList() {
		return buyList;
	}

	public void addToBuyList(Item item) {
		buyList.add(item);
	}

	public void charge(int money) {
		this.money += money;
	}

	public void buy() {

	}

}
