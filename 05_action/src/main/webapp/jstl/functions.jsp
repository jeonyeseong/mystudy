<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>functions</h1>
	<c:set var="str1">Hello world, Hello jstl</c:set>
	<c:set var="str2">Hello EL</c:set>
	
	<ul>
		<li>${fn:toUpperCase(str1)}</li>
		<li>${fn:toLowerCase(str1)}</li>
		<li>${fn:length(str1)} ${fn:length(str1) > 0}</li>
		<c:if test="${fn:length(str1) > 0}">
			<li>
				str1은 빈문자열이 아닙니다.
			</li>
		</c:if>
		<c:if test="${fn:length(str1) eq 0}">
			<li>
				str1은 빈문자열입니다.
			</li>
		</c:if>
		<li>${fn:contains(str1, 'world') ? "world 있다" : "world 없다"}</li>
		<li>${fn:indexOf(str1, 'world')} ${fn:indexOf(str1, 'lotteworld')}</li>
		<li>${fn:replace(str1, "Hello", "Goodbye")} ${str1}</li>
		
		<!-- 인자는 3개 모두 필수 -->
		<li>${fn:substring(str1, 6, 11)}</li>
		
		<!-- + 연산은 하지말고 이 방법으로 붙여라 -->
		<li>${str1}${str2} ${str1.concat(str2)}</li>
		<c:forEach items="${fn:split(str1, ',')}" var="str">
			<li>${str}</li>
		</c:forEach>
		<c:set var="xssStr"><script>alert("메롱!");</script></c:set>
		<%-- ${xssStr} --%>
		<li>${fn:escapeXml(xssStr)}</li>
		<li><c:out value="${xssStr}" escapeXml="true"></c:out></li>
	</ul>
	
	<img src="${pageContext.request.contextPath}/images/hwang.jpg" alt="" />
	<img src='<c:url value="/images/hwang.jpg"></c:url>' alt="" />
	
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>















