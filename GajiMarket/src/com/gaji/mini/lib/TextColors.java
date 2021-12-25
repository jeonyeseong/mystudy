package com.gaji.mini.lib;

public class TextColors {
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	public static String colorText(String str, int beginIndex, int endIndex, String color) {
		return str.replace(str.substring(beginIndex, endIndex), color + str.substring(beginIndex, endIndex) + RESET);
	}

	public static String colorText(String str, String color) {
		return str.replace(str, color + str + RESET);
	}

	public static String colorText(String str, int beginIndex, String color) {
		return str.replace(str.substring(beginIndex), color + str.substring(beginIndex) + RESET);
	}
}