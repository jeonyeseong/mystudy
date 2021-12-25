package com.kh.studygroup.chat.ws;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(value="/chat/websocket", configurator = HelloWebSocketConfig.class)
public class HelloWebSocket {
	
	public static Map<String, Session> clients = new HashMap<>();
	String memberId;
	
	public void clientsLog() {
		System.out.println("현재접속자수(" + clients.size() + ") : " + clients.keySet());
	}
	
	public String msgToJson(String type, String msg, String sender) {
		
			Map<String, Object> map = new HashMap<>();
			map.put("type", type);
			map.put("msg", msg);
			map.put("sender", sender);
			map.put("time", System.currentTimeMillis());
			String jsonStr = new Gson().toJson(msg);
				
			return new Gson().toJson(map);
		
	}
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		System.out.println("[open]");
		Map<String, Object> userProp = config.getUserProperties();
		
		this.memberId = (String) userProp.get("memberId");
		
		clients.put(memberId, session);
		clientsLog();
		
		
		// Session 내부 UserProperties맵에 memberId 저장 - onClose메소드에서 사용
		Map<String, Object> sessionUserProp = session.getUserProperties();
		sessionUserProp.put("memberId", memberId);
		
		
		//웰컴메시지 전송
		String msg = msgToJson("welcome","님이 입장했습니다.",memberId);
		onMessage(msg, session);
		


		
	}
	@OnMessage
	public void onMessage(String msg, Session session) {
		System.out.println("[message]");
		
		String[] arr = msg.split(",");
		int lastIndex = arr.length-1;
		System.out.println(msg);
		
		if(arr[lastIndex].contains("welcome") || arr[lastIndex].contains("bye")) {
			synchronized(clients) {
				// 클라이언트에서 세션 목록들을 가져옴
				Collection<Session> sessionList = clients.values();
				for(Session sess : sessionList) {
						Basic basic = sess.getBasicRemote();
						try {
							basic.sendText(msg);
						} catch (IOException e) {
							e.printStackTrace();
						}
					
				}
				
			}
		}
		else {
			msg = msg.replaceAll("msg", "self");
			
			msg = msg.replaceFirst("self", "msg");
			System.out.println("else문의 msg 값: " + msg);
			synchronized(clients) {
				// 클라이언트에서 세션 목록들을 가져옴
				Collection<Session> sessionList = clients.values();
				for(Session sess : sessionList) {
					if(clients.get(memberId).equals(sess)) {
						Basic basic = sess.getBasicRemote();
						try {
							basic.sendText(msg);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
			
		}

		/**
		 * 
		 * Session 관련 처리를 하는중 clients 맵에 변경이 일어나서는 안되므로,
		 * 멀티쓰레드에 대한 동기화처리해야 한다.
		 */
		

	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	@OnClose
	public void onClose(Session session) {
		System.out.println("[close]");
		
		// clients에서 해당 사용자 session제거
		// 세션에 담아둔걸로 사용자 세션제거
		Map<String, Object> sessionUserProp = session.getUserProperties();
		String memberId = (String) sessionUserProp.get("memberId");
		clients.remove(memberId);
		
		clientsLog();
		
		// 다른 사용자에게 알림
		String msg = msgToJson("bye","님이 퇴장했습니다.", memberId);
		onMessage(msg, session);
		
	}

}
