package com.gaji.mini.item.controller;

import java.util.ArrayList;
import java.util.List;

import com.gaji.mini.item.model.vo.Item;

public class ItemManager {
	/**
	 * 다른 controller 클래스를 참고하여 작성하기 다만 키값이 없고 중복이 허용되므로 해시맵 사용 x => list
	 * 
	 * 아이템 추가 아이템 삭제 아이템 리스트 보여주기 (모든 추가된 아이템 리스트)
	 * 
	 * 
	 */

	private List<Item> items = new ArrayList<>();

	public boolean addItem(Item i) {
		return items.add(i);
	}

	public boolean removeItem(Item i) {
		// for(int j=0; j<items.size(); j++) {
		// if(items.equals(i))) {
		// return items.remove(i);
		// }
		//
		// }
		return false;
	}

	public List<Item> listItems() {
		return items;
	}
}