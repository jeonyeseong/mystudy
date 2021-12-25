package com.kh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kh.member.model.service.MemberService;
import com.kh.studygroup.model.vo.Alram;





/**
 * Servlet implementation class AlramServlet
 */
@WebServlet("/member/alramList.do")
public class AlramServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.요청 인코딩처리
		request.setCharacterEncoding("utf-8");
		
		//2.파라미터값  가져오기
		String memberId = request.getParameter("loginMember");
		//System.out.println("memberId = " + memberId);

		//3.비지니스로직처리
		List<Alram> alramlist = new MemberService().selectAllAlram(memberId);
		//System.out.println("alramlist@servlet = " + alramlist);
		
		JsonArray jsonArr = new JsonArray();
		for(Alram alram: alramlist){
//				JsonObject jsonSmart = new JsonObject();
				jsonArr.add(alram.getMember_id());
		}
		//System.out.println("alramList="+jsonArr);
		
		
		//4.응답객체에 출력
		response.setContentType("application/json; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.print(jsonArr.toString());
		
	}
	


}
