package com.kh.spring.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author 빈등록
 *
 */
@Component
public class Person {
	
	@Autowired
	private Pet pet;
	
	/**
	 * Pet타입의 빈을 자동으로 찾아서 의존주입
	 * 1. 생성자
	 * 2. setter
	 * 3. field
	 * @param pet
	 */
	//@Autowired
	public Person(Pet pet) {
		this.pet = pet;
	}
	
	//@Autowired
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	public void helloPet() {
		pet.helloPerson();
	}
	
}
