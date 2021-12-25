package com.kh.studygroup.chat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;
import com.kh.studygroup.chat.ws.HelloWebSocket;
import com.kh.studygroup.model.vo.StudyGroupMember;

/**
 * Servlet implementation class GroupChatSendServlet
 */
@WebServlet("/chat/groupchat")
public class GroupChatSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String msg = request.getParameter("msg");
		Map<String, Object> map = new Gson().fromJson(msg, Map.class);
		
		System.out.println("[GroupChatSendServlet] map = " + map);
		
		
		HttpSession session = request.getSession(true);
		// 2. 업무로직 : clients에서 해당 reiciver 조회
		Map<String, Object> resultMap = new HashMap<>();
		
		// 리시버를 통한 스터디그룹 멤버 아이디 배열로 변환
		String receiver = (String) map.get("receiver");
		String[] arrReceiver = receiver.split(",");
		
		
		Member loginMember = (Member) session.getAttribute("loginMember");	
		
		Set<String> studyGroupSet = HelloWebSocket.clients.keySet();
		

		
		
		for(int i = 0 ; i < arrReceiver.length ; i++) {
			
			if(studyGroupSet.contains(arrReceiver[i])) {
				if(arrReceiver[i].equals(loginMember.getMember_id())){
					continue;
				}
				Session sess = HelloWebSocket.clients.get(arrReceiver[i]);
				sess.getBasicRemote().sendText(msg);	//json문자열로 전송
				resultMap.put("result","성공적으로 메세지를 전송했습니다");
			}
			else {
				resultMap.put("result", "메세지 전송 실패");
			}
		}

		/*
		if(studyGroupSet.contains(receiver)) {
			Session sess = HelloWebSocket.clients.get(receiver);
			sess.getBasicRemote().sendText(msg);	//json문자열로 전송
			resultMap.put("result","성공적으로 메세지를 전송했습니다");
		}
		else {
			resultMap.put("result", "메세지 전송 실패");
		}*/
		
		// 3. 응답작성
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(resultMap, response.getWriter());
		
		
		
		
	}

}
