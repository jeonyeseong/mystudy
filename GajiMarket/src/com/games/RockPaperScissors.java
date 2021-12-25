package com.games;

import java.util.Scanner;

public class RockPaperScissors {
	Scanner sc = new Scanner(System.in);
	int compoint = 0;
	int userpoint = 0;

	public int Start() {

		System.out.println("3판2선 시작!");

		while (true) {
			System.out.println("====가위 바위 보 게임 ===");
			System.out.print("숫자를 선택하세요(1.가위 / 2.바위 / 3.보) : ");
			int user = sc.nextInt();
			int com = (int) (Math.random() * 3) + 1; // == rnd.nextInt(3) + 1

			if (user >= 1 && user <= 3) {
				String user1;
				String com1;
				user1 = user == 1 ? "가위" : user == 2 ? "바위" : user == 3 ? "보" : "1,2,3중에 선택하세요";
				com1 = com == 1 ? "가위" : com == 2 ? "바위" : com == 3 ? "보" : "1,2,3중에 선택하세요";

				System.out.println("=============결과================");
				System.out.printf("당신은 %s를 냈습니다.%n컴퓨터는 %s를 냈습니다.%n", user1, com1);
				System.out.println("===============================");

				if (user == 1 && com == 3) {
					System.out.println("당신이 이겼습니다.");
					userpoint++;
				} else if (user == 2 && com == 1) {
					System.out.println("당신이 이겼습니다.");
					userpoint++;
				} else if (user == 3 && com == 2) {
					System.out.println("당신이 이겼습니다.");
					userpoint++;
				} else if (user == 1 && com == 2 || user == 2 && com == 3 || user == 3 && com == 1) {
					System.out.println("당신이 졌습니다.");
					compoint++;
				} else if (user == com) {
					System.out.println("비겼습니다.");
				}
			} else {
				System.err.println("1 ~ 3 사이의 수를 입력하셔야 합니다.");
			}

			System.out.println();

			if (compoint == 2)
				break;
			else if (userpoint == 2)
				break;

		}

		// 컴퓨터가 이기면 0 , 유저가 이기면 1
		if (compoint == 2) {
			System.out.println("컴퓨터 승리");
			return 0;
		} else {
			System.out.println("유저 승리");
			return 10;
		}

	}
}
