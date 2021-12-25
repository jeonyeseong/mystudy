package com.gaji.mini.lib;

public class ScreenClear {
	public static void clearScreen(long millis, int lines) {
		try {
			Thread.sleep(millis);
			for (int i = 0; i < lines; i++)
				System.out.println();

		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	public static void clearScreen(long millis) {
		try {
			Thread.sleep(millis);
			System.out.print("\033[H\033[2J");
			System.out.flush();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
