package com.gaji.mini.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.gaji.mini.item.model.vo.Item;
import com.gaji.mini.lib.ScreenClear;
import com.gaji.mini.lib.TextColors;
import com.gaji.mini.member.controller.MemberManager;
import com.gaji.mini.member.model.vo.Buyer;
import com.gaji.mini.member.model.vo.Member;
import com.gaji.mini.member.model.vo.Seller;
import com.gaji.mini.post.controller.Board;
import com.gaji.mini.post.model.vo.Post;

public class BoardMenu {
	Scanner sc = new Scanner(System.in);
	private Board board = new Board();
	private MemberManager memberManager = new MemberManager();
	private Member currentUser;
	private Seller currentSeller;
	private Buyer currentBuyer;
	String menu = "==============\n1. 게시판 조회\n" + "2. 상품 OO\n" + "3. 정보 조회\n" + "4. XX\n" + "5. 상품 삭제\n" + "0. 돌아가기";

	public BoardMenu(Member logInMember) {
		this.currentUser = logInMember;

		if (currentUser instanceof Seller) {
			this.currentSeller = (Seller) logInMember;
			menu = menu.replace("OO", "등록");
			menu = menu.replace("XX", "인출");
			// menu = "==============\n1. 게시판 조회\n" + "2. 상품 등록\n" + "3. 정보 조회\n" + "4. 상품
			// 생산";
		}
		if (currentUser instanceof Buyer) {
			this.currentBuyer = (Buyer) logInMember;
			menu = menu.replace("OO", "구매");
			menu = menu.replace("XX", "충전");
			menu = menu.replace("상품 삭제", "포인트 충전");
			// menu = "==============\n1. 게시판 조회\n" + "2. 상품 구매\n" + "3. 정보 조회\n" + "4. 포인트
			// 벌기";
		}

		this.boardMenu();
	}

	public void boardMenu() {

		ScreenClear.clearScreen(500);
		while (true) {
			System.out.println(menu);
			System.out.print("> 입력: ");
			int choice = sc.nextInt();

			try {

				switch (choice) {
					case 1:
						sort(board.listPosts());
						// Map<Integer, Post> posts = board.listPosts();
						// for (Integer key : posts.keySet()) {
						// Post p = posts.get(key);
						// System.out.println("번호: " + p.getPostNo() + "\t" + "제목: " + p.getTitle() +
						// "\t" + "상품이름: "
						// + p.getItem().getName());

						// }
						// 게시글 내용 조회
						while (true) {

							int postNo = selectPostNo();
							if (postNo == 0) {
								break;
							}
							showPost(postNo);
							sort(board.listPosts());
						}
						break;

					case 2:
						if (currentSeller != null)
							board.createPost(currentSeller, writePost(currentSeller));
						// 상품 등록
						else if (currentBuyer != null) {
							System.out.print("구매할 상품의 글 번호: ");
							board.buyItem(currentBuyer, sc.nextInt());
						}
						break;

					case 3:
						ScreenClear.clearScreen(0);
						showUserInfo(currentUser);
						System.out.println("소지금: " + memberManager.getMoney(currentUser) + "원\n");
						// 로그인 유저 정보 출력

						if (currentSeller != null) {
							// System.out.println(currentSeller.getPostedList());
							for (Integer postKey : currentSeller.getPostedList()) {
								Post temp = board.getPost(Integer.valueOf(postKey));
								System.out.println(temp.getPostNo() + ". " + temp.getTitle() + ":\t" + temp.getItem());
								// System.out.println(board.getPost(Integer.valueOf(postKey)));

							}
						}

						// if (currentBuyer != null)
						// System.out.println(membermManager.getMoney(currentUser) + "원");

						break;

					case 4:
						if (currentBuyer != null) {

							System.out.print("충전할 금액: ");
							memberManager.charge(currentUser, sc.nextInt());
							System.out.println("현재 금액: " + memberManager.getMoney(currentUser) + "원");
						} else if (currentSeller != null) {
							System.out.println("인출할 금액: ");
							memberManager.withdraw(currentUser, sc.nextInt());
							System.out.println("현재 금액: " + memberManager.getMoney(currentUser) + "원");
						}
						break;

					case 5:
						if (currentSeller != null) {
							System.out.print("postNo: ");
							int selectedNo = sc.nextInt();
							System.out.println(board.deletePost((Seller) currentUser, selectedNo));
							// 삭제
						} else if (currentBuyer != null) {
							int point = new GajiMenu().GameMenu();
							memberManager.charge(currentUser, point);
						}
						break;

					case 0:
						currentBuyer = null;
						currentSeller = null;
						ScreenClear.clearScreen(0);
						return;

					default:
						System.out.println("잘못 입력하셨습니다.");
						try {

							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			} catch (NullPointerException e) {

			}
		}

	}

	private void sort(Map<Integer, Post> p) {
		Map<Integer, Post> pMap = new TreeMap<>(p);
		ScreenClear.clearScreen(0);

		// System.out.printf(TextColors.GREEN + "번호\t 제목 \t\t작성자\t\t등록 시각\t판매여부\n"
		System.out.printf(TextColors.GREEN + "%s\t%15s\t\t%s\t\t%s\t\t%s%n", "번호", "제목", "작성자", "등록 시각",
				"판매여부" + "\n----------------------------------------------------------------------------------------"
						+ TextColors.RESET);
		for (Integer key : pMap.keySet()) {
			Post post = pMap.get(key);
			String soldStat = "판매중";
			if (post.isSold())
				soldStat = TextColors.RED + "판매완료";

			// System.out.println(post.getPostNo() + "\t" + post.getTitle() + "\t" +
			// post.getPostedBy() + "\t\t"
			// + post.getPostedAt() + "\t" + soldStat + TextColors.RESET);
			System.out.printf("%s\t%-15s\t\t%s\t\t%s\t\t%s%n", post.getPostNo(), post.getTitle(), post.getPostedBy(),
					post.getPostedAt(), soldStat + TextColors.RESET);
			// else
			// System.out.println(post.getPostNo() + "\t" + post.getTitle() + "\t" +
			// post.getPostedBy() + "\t\t"
			// + post.getPostedAt() + "\t" + "판매중");
			// TODO: postedby의 toSTring 수정
		}

	}

	private void sort2(Map<Integer, Post> p) {
		Map<Integer, Post> pMap = new TreeMap<>(p);
		for (Integer key : pMap.keySet()) {
			Post post = pMap.get(key);
			if (post.isSold())
				System.out.println(post.getPostNo() + ". " + post.getTitle() + " " + post.getPostedBy() + " "
						+ post.getPostedAt() + " " + TextColors.colorText("판매완료", TextColors.PURPLE));
			else
				System.out.println(post.getPostNo() + ". " + post.getTitle() + " " + post.getPostedBy() + " "
						+ post.getPostedAt() + " " + "판매중");
			// TODO: postedby의 toSTring 수정
		}
	}

	private void showPost(int postNo) {
		while (true) {

			ScreenClear.clearScreen(0);
			Post temp = board.getPost(postNo);
			if (temp == null) {
				break;
			}
			// 여기서 보여주고 사용자가 돌아가기(ex -1 등을 누르면 게시판 메뉴로 가기)
			System.out.println(TextColors.GREEN + "글 번호: " + temp.getPostNo() + "\t작성 시각: " + temp.getPostedAt() + "\n"
					+ "제목: " + temp.getTitle() + "\n==========================\n" + TextColors.YELLOW
					+ temp.getItem().getName() + " (" + temp.getItem().getPrice() + "원)\n\n" + TextColors.RESET
					+ "내용: \n" + temp.getContent());

			System.out.print("\n> 게시판으로 돌아가기(0): ");
			if (sc.nextInt() == 0) {
				break;
			}
			// TODO: 여기 고치기
		}

	}

	private int selectPostNo() {
		// 스캐너로 입력받고 입력 받은 값을 리턴
		while (true) {

			System.out.println("\n돌아가기: (0)\t게시글 선택(No)");
			System.out.print("> 입력: ");

			return sc.nextInt();
		}
	}

	private void showUserInfo(Member m) {
		System.out.println(memberManager.getMember(m.getID()));
		// System.out.println(m);
	}

	private Post writePost(Seller s) {
		int postNo;
		String itemName;
		int itemPrice = 0;
		Item item;
		String title;
		String content;

		ScreenClear.clearScreen(0);
		System.out.println("+++++ 상품 등록 ++++");

		System.out.print("글 번호: ");
		postNo = sc.nextInt();
		sc.nextLine();

		System.out.print("아이템 이름: ");
		itemName = sc.nextLine();
		System.out.print("아이템 가격: ");
		itemPrice = sc.nextInt();
		item = new Item(itemName, itemPrice);

		sc.nextLine();
		System.out.print("제목: ");
		title = sc.nextLine();

		System.out.println("내용: (입력이 끝나면 exit)");
		String str = "";
		while (true) {
			content = sc.nextLine();
			if ("exit".equals(content)) {
				System.out.println("내용 입력 끝!");
				break;
			}
			str += content + "\n";
		}
		content = str;

		s.addToPostedList(postNo);
		System.out.println("_______++++++++++++++____________________");
		System.out.println(s.getPostedList());
		System.out.println("_______++++++++++++++____________________");
		return new Post(postNo, item, s.getID(), title, content);

	}

}
