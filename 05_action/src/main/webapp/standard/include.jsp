<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include</title>
</head>
<body>
	<h1>include</h1>
	<!-- 끌어다 쓰려면 -->
	<h2>오늘은 <%@ include file="/standard/today.jsp" %>입니다.</h2>
	
	<!-- 출력만 하려면 -->
	<h2>오늘은 <jsp:include page="/standard/today.jsp"/>입니다.</h2>
	
</body>
</html>