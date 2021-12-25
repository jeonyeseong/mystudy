package com.gaji.mini.member.controller;

import java.io.*;
import java.util.*;
import java.util.Scanner;

import com.gaji.mini.member.model.io.MemberIO;
import com.gaji.mini.member.model.vo.*;

public class MemberManager {
	private Map<String, Member> members = new HashMap<>();
	private MemberIO mio = new MemberIO();

	public Member addMember(Member m) {
		Member oldMember;
		members = mio.readFile();
		oldMember = members.put(m.getID(), m);
		mio.writeFile(members);

		return oldMember;
	}

	public Member removeMember(String id, String pw) {
		members.putAll(mio.readFile());
		if (UserExists(id)) {
			if (checkPassword(members.get(id), pw)) {
				Member temp = members.remove(id);
				mio.writeFile(members);
				return temp;
			}
		}
		return null;
	}

	public boolean checkPassword(Member m, String password) {
		if (password.equals(m.getPw())) {
			System.out.println(m.getPw());
			return true;
		}
		return false;
	}

	// 멤버를 리스트로 반환하는 게 아니라 멤버의 목록(list)을 보여주는 함수
	public Map<String, Member> listMembers() throws NullPointerException {
		members.putAll(mio.readFile());
		return members;
	}

	public boolean UserExists(String id) {
		members.putAll(mio.readFile());
		return members.containsKey(id);
	}

	public Member getMember(String id) {
		members.putAll(mio.readFile());
		return members.get(id);
	}

	// 인자로 넣은 멤버값이 판매자면 "buyer" 구매자면 "seller" 리턴
	public String checkMemberType(String id) {
		if (UserExists(id)) {
			if (members.get(id) instanceof Buyer) {
				System.out.println("++++it's buyer++++");
				return "buyer";
			} else if (members.get(id) instanceof Seller) {
				System.out.println("++++it's seller++++");
				return "seller";
			}
		}
		System.out.println("null");
		return null;
	}

	public void charge(Member m, int money) {
		// m.charge(money);
		members.putAll(mio.readFile());
		Member target = members.get(m.getID());
		target.setMoney(target.getMoney() + money);
		addMember(target);
	}

	public void withdraw(Member m, int money) {
		members.putAll(mio.readFile());
		Member target = members.get(m.getID());
		target.setMoney(target.getMoney() - money);
		addMember(target);
	}

	public int getMoney(Member m) {
		members.putAll(mio.readFile());
		return members.get(m.getID()).getMoney();
	}

	// Scanner sc = new Scanner(System.in);
	// final String dir = "database/";
	// final String file = "board.txt";
	// Member member = new Member();

	// Map<Integer, Member> board = new HashMap<>(); // 게시글
	// {
	// board.put(new board(1), new board(new Item(item), );
	// board.put(2, new board());
	// board.put(3, new board());
	// board.put(4, new board());
	// }

	// Map<String, Member> seller = new HashMap<>(); // 판매자
	// {
	// seller.put("123", new Member());
	// seller.put("234", new Member());
	// seller.put("456", new Member());
	// }

	// Map<String, Member> buyer = new HashMap<>(); // 구매자
	// {
	// buyer.put("123", new Member());
	// buyer.put("234", new Member());
	// buyer.put("456", new Member());
	// }

	// /*
	// * 컨트롤러
	// *
	// * 게시판 보여주기 게시글등록()
	// *
	// * 회원등록 인출() 충전() 구매()
	// *
	// *
	// *
	// * 흥정?()
	// */

	// // 회원 등록 //구매자와 판매자 나눔
	// public void memberRegi() {
	// while (true) {
	// System.out.println("판매자와 구매자 선택 : ");
	// String sellbuy = sc.next();
	// if ("구매자".equals(sellbuy)) {
	// System.out.println("id 등록 : ");
	// String id = sc.next();

	// buyer.put(id, new Member());

	// System.out.println("등록 완료");
	// break;
	// }

	// else if ("판매자".equals(sellbuy)) {
	// System.out.println("id 등록 : ");
	// String id = sc.next();

	// seller.put(id, new Member());

	// System.out.println("등록 완료");
	// break;
	// } else {
	// System.out.println("잘못 입력하셨습니다.");
	// continue;
	// }
	// }
	// }

	// // 게시글 보기
	// public void fileview() {
	// Map<Integer, Member> boardview = null;

	// try {
	// ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new
	// FileInputStream(dir + file)));
	// boardview = (Map<Integer, Member>) ois.readObject();
	// for (int i = 0; i < boardview.size(); i++) {
	// System.out.println(boardview.toString());
	// }

	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// }

	// // 게시글 쓰기 //
	// public void filewrite() {
	// ​
	// try(
	// FileWriter fw = new FileWriter(file, true);
	// ObjectOutputStream oos = new ObjectOutputStream(new
	// FileOutputStream(dir+file));
	// ){

	// oos.writeObject(board);
	// System.out.println(file+"저장 완료");
	// // 실제파일에 쓰기
	// } catch (IOException e) {
	// e.printStackTrace();

	// }
	// }

	// // 충전
	// public int chargingMoney() {

	// System.out.println("판매자와 구매자 선택 : ");
	// String sellbuy= sc.next();
	// System.out.println("id 입력 : ");
	// String id = sc.next();

	// if("구매자".equals(sellbuy)) {

	// for(String key : buyer.keySet())
	// if(id.contains(buyer.get(key).getID())) {
	// return buyer.get(key).getMoney() += 1000;
	// }

	// System.out.println("충전 완료");
	// break;
	// }

	// else if("판매자".equals(sellbuy)) {
	// System.out.println("id 등록 : ");
	// String id = sc.next();

	// seller.put(id, new Member());

	// System.out.println("등록 완료");
	// break;
	// }
	// else{
	// System.out.println("잘못 입력하셨습니다.");
	// continue;
	// }

	// // for(int i = 0 ; i<member.memberList.size(); i++) {
	// // if(id.equals(member.memberList.get(i).getID())) {
	// // return member.memberList.get(i).setMoney() += 1000; //1000원 충전
	// // }
	// // }

	// }

	// // 구매 // 구매 후 게시글했던 글 삭제?
	// public void buy(String str) {

	// for (int i = 0; i < member.memberList.size(); i++) {
	// if (str.equals(member.memberList.get(i).getItem())) {
	// System.out.println(
	// member.memberList.get(i).getID() + " 의" + member.memberList.get(i).getItem()
	// + "판매 완료");
	// member.memberList.get(i).getItem() = null;
	// }
	// }
	// // TODO: 판매자 돈 + , 구매자 돈 -

	// }

	// // 인출
	// public int withdraw(String id, int money) {

	// for (int i = 0; i < member.memberList.size(); i++) {
	// if (id.equals(member.memberList.get(i).getID())) {
	// return member.memberList.get(i).setMoney() -= money; // 1000원 충전
	// }
	// }

	// }

}
