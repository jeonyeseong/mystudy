package com.kh.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menuorder
 */
@WebServlet("/menu.do")
public class menuservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//1.요청처리 : 사용자입력값 -> 자바변수
		String mainMenu = request.getParameter("mainMenu");
		String sideMenu = request.getParameter("sideMenu");
		String drinkMenu = request.getParameter("drinkMenu");
		
		System.out.println("mainMenu = "+ mainMenu);
		System.out.println("sideMenu = "+ sideMenu);
		System.out.println("drinkMenu = "+ drinkMenu);
		
		String main = "";
		String style = "";
		int mainprice = 0;
		switch(main) {
		case "한우버거" : mainprice = 5000; style="red"; break;
		case "밥버거" : mainprice = 4500; style="yellow"; break;
		case "치즈버거" : mainprice = 4000; style="green"; break;
		}
		
		String side = "";
		String style2 = "";
		int sideprice = 0;
		switch(side) {
		case "감자튀김" : sideprice = 1500; style2="blue"; break;
		case "어니언링" : sideprice = 1700; style2="purple"; break;
		}
		
		String drink = "";
		String style3 = "";
		int drinkprice = 0;
		switch(drink) {
		case "콜라" : drinkprice= 1000; style3="black"; break;
		case "사이다" : drinkprice= 1000; style3="gray"; break;
		case "커피" : drinkprice= 1500; style3="blue"; break;
		case "밀크쉐이크" : drinkprice= 2500; style3="orange"; break;
		}
		
		
		//3.응답처리 : jsp 위임(RequestDispatcher객체의 forward)
		//jsp에서 사용할 데이터를 속성으로 저장
		request.setAttribute("main", main);
		request.setAttribute("mainprice", mainprice);
		request.setAttribute("side", side);
		request.setAttribute("sideprice", sideprice);
		request.setAttribute("drink", drink);
		request.setAttribute("drinkprice", drinkprice);
		
		request.setAttribute("style", style);
		request.setAttribute("style2", style2);
		request.setAttribute("style3", style3);
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/menuresult.jsp");
		requestDispatcher.forward(request, response);
		
	}
	

}
