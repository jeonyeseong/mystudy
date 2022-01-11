package com.kh.builder;

import java.util.Date;

public class PersonMain {

	public static void main(String[] args) {
		Person person =
				Person.builder()
					.id("honggd")
					.name("홍길동")
					.birthday(new Date())
					.build();
		System.out.println(person);
	}

}
