package com.kh._enum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class EnumMain {

	public static void main(String[] args) {
		EnumMain main = new EnumMain();
		//main.test0();
		//main.test1();
		main.test2();

	}
	
	/**
	 * enum name과 value가 다른경우
	 */
	private void test2() {
		Dev dev1 = new Dev("홍길동", DevType.WEB);
		Dev dev2 = new Dev("윤봉준", DevType.IOS);
		Dev dev3 = new Dev("논개", DevType.ANDROID);
		System.out.println(dev1.getDevType().getValue());
		System.out.println(dev2);
		System.out.println(dev3);
		
		// value값 -> enum처리
		String v = "W";
		Dev dev4 = new Dev("세종", DevType._valueOf(v));
		System.out.println(dev4);
	}

	/**
	 * - enum 
	 * - namespace를 가진 상수
	 * - compile time에 타입체크가 가능하다.
	 */
	private void test1() {
		Member honggd = new Member("honggd", Gender.M, Role.M);
		Member sinsa = new Member("honggd", Gender.F, Role.A);
		
		System.out.println(honggd);
		System.out.println(sinsa);
		
	}

//	private void test0() {
//		Member honggd = new Member("honggd", Member.GENDER_MALE, Member.ROLE_MEMBER);
//		Member sinsa = new Member("sinsa", Member.GENDER_FEMALE, Member.ROLE_ADMIN);
//		
//		System.out.println(honggd);
//		System.out.println(sinsa);
//		
//	}

}

@Data
@AllArgsConstructor
class Dev {
	private String name;
	private DevType devType;
}

enum DevType {
	WEB("W"), IOS("I"), ANDROID("A");
	
	@Getter
	private String value;
	
	/**
	 * enum의 생성자는 private이다.
	 * enum class 내부에서만 사용가능하다.
	 * 
	 */
	DevType(String value){
		this.value = value;
	}
	
	/**
	 * value -> enum반환
	 * 
	 */
	public static DevType _valueOf(String value) {
		switch(value) {
		case "W" : return WEB;
		case "I" : return IOS;
		case "A" : return ANDROID;
		default:
			throw new AssertionError("Unknown Type Error : " + value);
		}

	}
	
}


enum Gender {
	M, F;
}
enum Role {
	M, A;
}

@Data
@AllArgsConstructor
class Member {
	
//	public static final String GENDER_MALE = "M";
//	public static final String GENDER_FEMALE = "F";
//	public static final String ROLE_MEMBER = "M";
//	public static final String ROLE_ADMIN = "A";
	
	private String id;
	private Gender gender; // M, F
	private Role role;   // M, A
}


