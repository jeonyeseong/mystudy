<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>  
<%@page import="com.kh.studygroup.board.model.vo.StudyGroupBoard"%> 
<%@ page import = "java.util.List" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard.css" />

<%
	List<StudyGroupBoard> list = (List<StudyGroupBoard>) request.getAttribute("list");

%>

<section id="board-container">
	<h2>그룹게시판 </h2>
		<% if(loginMember != null) { %>
	<input type="button" value="글쓰기" id = "btn-add" onclick="location.href='<%= request.getContextPath() %>/studygroup/boardForm';"/>
	<%} %>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
			<th>조회수</th>
		</tr>
		
		
<%		
	for(StudyGroupBoard board : list){
%>
		<tr>
			<td><%= board.getBoardNo() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/studygroup/boardView?no=<%= board.getBoardNo() %>" onclick = boardNo()>
				<%= board.getTitle() %>
				<%= board.getCommentCount() > 0 ? "(" + board.getCommentCount() + ")" : ""%>
				</a>
			</td>
			<td><%= board.getWriter() %></td>
			<td><%= board.getReg_Date() %></td>
			<td>
			<% if(board.getAttachCount() > 0){ %>
				<img src="<%= request.getContextPath() %>/images/cola.png" alt="" width = "16px"/>
			<% } %>
			</td>
			<td><%= board.getRead_count() %></td>
		</tr>

		<%} %>
	</table>

	<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>  