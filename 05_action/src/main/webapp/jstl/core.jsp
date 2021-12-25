<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
	jstl 사용절차
	1. jstl.jar를 프로젝트에 연결(WebApp Libraries)
	2. taglib 지시어등록
		uri : tld파일의 uri속성을 가리킴. url 형식을 띤 문자열식별자.
		prefix : 해당태그를 사용하기위한 namespace설정(접두사)
 --%>
 <!-- scope="page or session or request"등 담기 -->
<c:set var="no1" value="123" scope="page"></c:set>
<c:set var="no2">123</c:set>
<c:set var="x"></c:set>
<c:set var="y"></c:set>
<%
	//pageContext.setAttribute("no1", 123);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>core</title>
</head>
<body>
	<h1>core</h1>
	<h2>set | out</h2>
	<ul>
		<li><c:out value="${no1}"/></li>
		<li><c:out value="${no2}"/></li>
		<li><c:out value="${no1 + no2}"/></li>
		
		<li>${no1}</li>
		<li>${no2}</li>
		<li>${no1 + no2}</li>
	</ul>
		
	<h2>if</h2>
	<c:if test="${no1 > no2}">
		${no1}은 ${no2}보다 큽니다.
	</c:if>
	<c:if test="${no1 < no2}">
		${no1}은 ${no2}보다 작습니다.
	</c:if>
	<c:if test="${no1 eq no2}">
		${no1}은 ${no2}보다 같습니다.
	</c:if>
	
	<h2>param(core.jsp?x=23&y=32) if</h2>
	<li><c:out value="${param.x}"/></li>
	<li><c:out value="${param.y}"/></li>
	<li><c:out value="${param.x + param.y}"/></li>
	<c:if test="${Integer.parseInt(param.x) > Integer.parseInt(param.y)}">
		${param.x}은 ${param.y}보다 큽니다.
	</c:if>
	<c:if test="${Integer.parseInt(param.x) < Integer.parseInt(param.y)}">
		${param.x}은 ${param.y}보다 작습니다.
	</c:if>
	<c:if test="${Integer.parseInt(param.x) eq Integer.parseInt(param.y)}">
		${param.x}은 ${param.y}보다 같습니다.
	</c:if>

	<h2>choose</h2>
	<c:set var="rnum" value="<%= new Random().nextInt(100) %>"></c:set>
	${rnum}
	<c:choose>
		<c:when test="${rnum % 5 == 0}">
			인형을 뽑았습니다.
		</c:when>
		<c:when test="${rnum % 5 == 1}">
			권총라이터를 뽑았습니다.
		</c:when>
		<c:otherwise>
			꽝입니다. 다음 기회에...
		</c:otherwise>
	</c:choose>
	
	<h2>forEach</h2>
	
	<hr />
	<!-- 
		var변수명 step 1++ 2++ 
		begin은 end보다 작아야됨
	-->
	<c:forEach begin="1" end="6" step="1" var="i">
		<h${7 - i}>안녕</h${7 - i}>
	</c:forEach>
	
	<hr />
	
	
	
	
	
	
	
	
</body>
</html>