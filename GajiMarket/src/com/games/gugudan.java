package com.games;

import java.util.Random;
import java.util.Scanner;

public class gugudan {
	// 구구단 문제를 랜덤으로 제시
	// 5회 반복해서 정답 맞춘 횟수 출력
	public int Start() {
		Random rnd = new Random();
		Scanner sc = new Scanner(System.in);

		int count = 0;
		int result;
		int num1; // (1~9)
		int num2; // (1~9)

		System.out.println("구구단 게임");

		while (true) {
			num1 = rnd.nextInt(8) + 1;
			num2 = rnd.nextInt(8) + 1;
			System.out.println(num1 + "x" + num2 + "=");

			result = sc.nextInt();

			if (result == num1 * num2) {
				System.out.println("정답입니다.");
				count++;
				continue;
			} else {
				System.out.println("틀렸습니다, 정답은" + num1 * num2 + "입니다.");
				break;
			}

		}
		return count;

	}

}