package com.gaji.mini.run;

import com.gaji.mini.lib.ScreenClear;
import com.gaji.mini.view.GajiMenu;

public class GajiRun {

	public static void main(String[] args) {
		ScreenClear.clearScreen(0);
		new GajiMenu().mainMenu();
	}
}
