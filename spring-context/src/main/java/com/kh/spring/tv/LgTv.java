package com.kh.spring.tv;

public class LgTv implements Tv {
	
	private RemoteControl remocon;
	
	public LgTv() {
		System.out.println("LgTv객체를 생성합니다.");
	}
	
	public void changeChannel(int no) {
		remocon.changeChannel(no);
	}
	
	
//	public LgTv(RemoteControl remocon) {
//		System.out.println("LgTv채널을 변경합니다." + remocon);
//		this.remocon = remocon;
//	}
	
	public void setRemocon(RemoteControl remocon) {
		System.out.println("remocon의존을 주입 : " + remocon);
		this.remocon = remocon;
	}
	

	@Override
	public void powerOn() {
		System.out.println("LgTv의 전원을 켰습니다.");
	}

}
