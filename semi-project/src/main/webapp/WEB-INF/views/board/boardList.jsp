<%@page import="com.kh.board.model.vo.Frontboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	List<Frontboard> list = (List<Frontboard>) request.getAttribute("list"); 
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard.css" />

<section id="board-container">
	<h2>스터디 모집 게시판</h2><br />
<% if(loginMember != null){ %>	
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%= request.getContextPath() %>/board/boardForm'"/>
<% } %>	
<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
<% 
	for(Frontboard board : list){
%>
		<tr>
			<td><%= board.getNo() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/board/boardView?no=<%= board.getNo() %>"><%= board.getTitle() %></a>
				
			</td>
			<td><%= board.getWriter() %></td>
			<td><%= board.getRegDate() %></td>
			<td><%= board.getReadCount() %></td>
		</tr>
<%
	}
%>		
	</table>
	<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
	
	
	
	
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />