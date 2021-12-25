package com.games;

import java.util.Scanner;

public class UpDown {
	Scanner sc = new Scanner(System.in);
	// private int point = 0;
	int rnd;
	boolean result = false;
	int com_min = 1;
	int com_max = 100;

	public int Start() {

		rnd = (int) (Math.random() * 100 + 1);

		System.out.println("2명이 번갈아가면서 게임을 진행하고 먼저 맞춘사람이 이기는 게임!");

		while (true) {
			int userNum = userNum();
			// point++;

			if (NumCheck(userNum) == 0) {
				System.out.println("user1이 맞췄습니다. user1 승리!");
				return userNum;
			} else if (NumCheck(userNum) == -1)
				System.out.println("DOWN");
			else if (NumCheck(userNum) == 1)
				System.out.println("UP");

			int comNum = comNum();
			System.out.println("com============> " + comNum);
			if (NumCheck(comNum) == 0) {
				System.out.println("com이 맞췄습니다. com 승리!");
				return 0;
			} else if (NumCheck(comNum) == -1)
				System.out.println("DOWN");
			else if (NumCheck(comNum) == 1)
				System.out.println("UP");

		}
	}

	private int NumCheck(int num) {
		if (rnd == num)
			return 0;
		else if (rnd < num) {
			com_max = num;
			return -1;
		} else if (rnd > num) {
			com_min = num;
			return 1;
		}

		return 0;
	}

	private int comNum() {
		return (int) (Math.random() * (com_max - com_min - 1) + com_min + 1);
	}

	private int userNum() {
		int user = 0;

		while (true) {
			try {

				System.out.print("user1 ==============> ");
				user = sc.nextInt();
				return user;

			} catch (Exception e) {
				sc.nextLine();

				System.out.println("올바른 정수를 입력하세요");

			}
		}
	}
}
