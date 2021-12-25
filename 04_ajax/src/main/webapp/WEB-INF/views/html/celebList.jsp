<%@page import="java.util.List"%>
<%@page import="com.kh.ajax.celeb.model.vo.Celeb" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Celeb> celebList = (List<Celeb>) request.getAttribute("celebList");
%>
<table>
	<tr>
		<td>이름</td>
		<td>전화번호</td>
		<td>나이</td>
		<td>프로필</td>
		<td>결혼여부</td>
	</tr>
<%
	for(Celeb celeb : celebList){
%>
	<tr>
		<td><%= celeb.getName() %></td>
		<td><%= celeb.getPhone() %></td>
		<td><%= celeb.getAge() %></td>
		<td>
			<img src="<%= request.getContextPath() %>/images/<%= celeb.getProfile() %>" alt="" />
		</td>
		<td>
			<input type="checkbox" <%= celeb.isMarried() ? "checked" : "" %>/>
		</td>
	</tr>
	
<%
	}
%>
</table>