package com.kh.spring.tv;

public class SamsungTv implements Tv {
	
	private RemoteControl remocon;
	
	
	public SamsungTv() {
		System.out.println("SamsungTv객체를 생성합니다.");
	}
	
//	public SamsungTv(RemoteControl remocon) {
//		System.out.println("SamsungTv채널을 변경합니다." + remocon);
//		this.remocon = remocon;
//	}
	
	public void setRemocon(RemoteControl remocon) {
		System.out.println("remocon의존을 주입 : " + remocon);
		this.remocon = remocon;
	}
	


	@Override
	public void powerOn() {
		System.out.println("SamsungTv의 전원을 켰습니다.");
	}

	public RemoteControl getRemocon() {
		return remocon;
	}


	@Override
	public void changeChannel(int no) {
		remocon.changeChannel(no);
		
	}


}
