package com.gaji.mini.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// import com.gaji.mini.game.RockPaperScissors;
// import com.gaji.mini.game.UpDown;
import com.gaji.mini.item.model.vo.Item;
import com.gaji.mini.lib.ScreenClear;
import com.gaji.mini.lib.TextColors;
import com.gaji.mini.member.controller.MemberManager;
import com.gaji.mini.member.model.vo.*;
import com.gaji.mini.post.model.vo.Post;
import com.games.Game_369;
import com.games.RockPaperScissors;
import com.games.UpDown;
import com.games.gugudan;

public class GajiMenu {
	Scanner sc = new Scanner(System.in);
	private MemberManager memberManager = new MemberManager();

	public void mainMenu() {
		String menu = "==============\n1. 구매자 회원가입\n" + "2. 사업자 회원가입\n" + "3. 회원 탈퇴\n" + "4. 로그인\n" + "9. 전체 회원\n"
				+ "0. 프로그램 종료\n==============";

		ScreenClear.clearScreen(500);
		while (true) {
			System.out.println(menu);
			System.out.print("> 입력: ");
			int choice = sc.nextInt();

			try {

				switch (choice) {
					case 1:
						memberManager.addMember(inputMember("buyer"));
						break;

					case 2:
						memberManager.addMember(inputMember("seller"));
						break;

					case 3:
						System.out.println(memberManager.removeMember(inputID(), inputPW()));
						break;

					case 4:
						// TODO: 로그인 후 화면 만들기
						String id = inputID();
						String pw = inputPW();
						String memberType;
						Member logInMember = memberManager.getMember(id);
						System.out.println("___________________________");
						System.out.println(logInMember);
						// memberType = memberManager.checkMemberType(id);
						if (memberManager.checkPassword(logInMember, pw))
							new BoardMenu(logInMember);
						// if ("buyer".equals(memberType)) {
						// System.out.println(memberManager.getMember(id));
						// } else if ("seller".equals(memberType)) {
						// seller = (Seller) memberManager.getMember(id);
						// }

						break;

					case 9:
						sort(memberManager.listMembers());
						// ScreenClear.clearScreen(3000);

						break;

					case 0:
						return;

					default:
						// System.out.println(TextColors.colorText("잘못 입력하셨습니다.", TextColors.CYAN));
						System.out.println(TextColors.colorText("잘못 입력하셨습니다.", 3, 5, TextColors.CYAN));

						// System.out.println("잘못 입력하셨습니다.");
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

	private String inputID() {
		System.out.print("ID: ");
		return sc.next();
	}

	private String inputPW() {
		System.out.print("PW: ");
		return sc.next();
	}

	private String inputName() {
		System.out.print("Name: ");
		return sc.next();
	}

	private Member inputMember() {
		String id;
		String pw;
		ScreenClear.clearScreen(0);
		System.out.println("==== 회원탈퇴 ====");
		id = inputID();
		pw = inputPW();

		return new Buyer(id, pw, null);
	}

	private Member inputMember(String memberType) {
		String id;
		String pw;
		String name;

		ScreenClear.clearScreen(0);
		System.out.println("==== " + memberType + " 회원가입 ====");
		id = inputID();
		/**
		 * TODO: ID 같으면 여기서 중복체크 바로 하고 중복 시 return; * id.equals(member.getID() 하지 말고
		 * key값으로 비교하기)
		 */

		pw = inputPW();
		name = inputName();
		ScreenClear.clearScreen(0);
		// 사용자 입력을 받아서 Member객체 만들고, 리턴
		// return new Member();
		if ("buyer".equals(memberType))
			return new Buyer(id, pw, name);
		else if ("seller".equals(memberType))
			return new Seller(id, pw, name);
		else
			return null; // error

	}

	private void sort(Map<String, Member> m) {

		Map<String, Member> keymap = new TreeMap<>(m);
		ScreenClear.clearScreen(0);
		System.out.println(TextColors.GREEN + "회원유형\tID\t\t이름\t\t암호" + TextColors.RESET);
		System.out.println("=======================================================");
		for (String key : keymap.keySet()) {
			Member temp = keymap.get(key);
			String type = temp.getClass().getSimpleName();
			if ("Seller".equals(type))
				type = TextColors.colorText(type, TextColors.BLUE);
			else if ("Buyer".equals(type))
				type = TextColors.colorText(type, TextColors.RED);

			System.out.printf("%s\t\t%s\t\t%s\t%s%n", type, temp.getID(), temp.getName(),
					TextColors.YELLOW + temp.getPw() + TextColors.RESET);
			// System.out.println(TextColors.colorText(keymap.get(key).toString(),
			// TextColors.GREEN));

		}

	}

	public int GameMenu() {
		int point = 0;
		int total = 0;
		while (true) {
			String gamemenu = "==============\n1. 가위바위보\n" + "2. 업다운\n" + "3. 369게임\n" + "4. 구구단게임\n"
					+ "0. 돌아가기\n==============";
			ScreenClear.clearScreen(500);

			System.out.println(gamemenu);
			System.out.print("> 입력: ");
			int choice = sc.nextInt();

			switch (choice) {
				case 1:
					ScreenClear.clearScreen(0);
					point = new RockPaperScissors().Start() * 100;
					System.out.println(point + "점입니다.");
					total += point;

					break;

				case 2:
					ScreenClear.clearScreen(0);
					point = new UpDown().Start() * 100;
					System.out.println(point + "점입니다.");
					total += point;
					break;

				case 3:
					ScreenClear.clearScreen(0);
					point = new Game_369().Start() * 100;
					System.out.println(point + "점입니다.");
					total += point;
					break;

				case 4:
					ScreenClear.clearScreen(0);
					point = new gugudan().Start() * 100;
					System.out.println(point + "점입니다.");
					total += point;
					break;

				case 0:
					System.out.println("총 점수: " + total);
					return total;
				// break;

				default:
					System.out.println("잘못 입력하셨습니다.");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
			}
		}
	}
}
