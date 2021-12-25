<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
	String mainMenu = request.getParameter("mainMenu");
	String sideMenu = request.getParameter("sideMenu");
	String drinkMenu = request.getParameter("drinkMenu");
	
	request.getAttribute("main", main);
	request.getAttribute("mainprice", mainprice);
	request.getAttribute("side", side);
	request.getAttribute("sideprice", sideprice);
	request.getAttribute("drink", drink);
	request.getAttribute("drinkprice", drinkprice);
	
	request.getAttribute("style", style);
	request.getAttribute("style2", style2);
	request.getAttribute("style3", style3);
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>