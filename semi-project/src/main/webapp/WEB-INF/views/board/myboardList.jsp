<%@page import="com.kh.studygroup.board.model.vo.StudyGroupBoard"%>
<%@page import="com.kh.community.model.vo.Questionboard"%>
<%@page import="com.kh.community.model.vo.Freeboard"%>
<%@page import="com.kh.board.model.vo.Frontboard"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/views/common/header.jsp" %> 

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/myboardList.css" />
<title>MyBoardList</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>
<body>
	<h1 align="center">내 작성글 보기</h1>
	<hr />
	<h3 align="center">스터디모집</h3>
<% 
	List<Frontboard> list = (List<Frontboard>) request.getAttribute("list");
	if(list.size() != 0){ 
%>
	<div id="tbl-board">
		<ul class="ultest">
<% } %>
<% 
	if(list.size() != 0){
	for(Frontboard frontboard : list){
%>   


			<li id="<%= frontboard.getArea() %>" class="<%= frontboard.getLanguage() %>" onclick="location.href='<%= request.getContextPath() %>/board/frontboardView?no=<%= frontboard.getNo() %>';" 
			style="width: 15rem; height: 15rem; margin:2rem; padding: 1.5rem; background: white; box-shadow:0px 5px 25px rgb(0 0 0 / 15%); border-radius:1.5rem; position: relative; cursor: pointer;
					transition: all 0.5s;">
			<h4 style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getTitle() %></h4>
				<ul class="ultest1">
					
					<% 
						if(frontboard.getLanguage().contains("jscript"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/javascript.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">javascript</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().contains("java"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/java.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">java</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().contains("python"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/python.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">python</p>
					
							
							<% 
						}
					%> 
					<% 
						if(frontboard.getLanguage().contains("c쁠쁠"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/c++.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">c++</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().contains("spring"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/spring.png" width=50rem; height=50rem;>
							<p>spring</p>
							
						
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().contains("nodejs"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/nodejs.png" width=50rem; height=50rem;>
							<p>node.js</p>
					
							
							<% 
						}
					%>    
					
							<section class="toptext">
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getNow_member() %>명&nbsp;/&nbsp;</p>
							<p style= "font-family: 'InfinitySans-RegularA1';"> <%= frontboard.getMax_member() %>명</p>
							</section>
							<section class="undertext">
							&#128064;
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getReadCount() %></p>	
							&#128172;
								<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getComment_count() %></p>
							
							</section>
							</li>
					
					
					
				
				
				</ul>
		
			</li>
	
		
<% 
	}}else{
%>
	<div class="alert alert-danger" role="alert">
	  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  <span class="sr-only">스터디그룹</span>
	  게시글이 없습니다
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%= request.getContextPath() %>/board/boardForm'"/>
	</div>
<% } %>
<% 
	if(list.size() != 0){ 
%>
</ul>
	</div>
<% } %>
	<br /><br />
	<h3 align="center">자유게시판</h3>
<% 
	List<Freeboard> free = (List<Freeboard>) request.getAttribute("free"); 
	if(free.size() != 0){ 
%>
<section id="board-container">
	<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>추천수</th>
			</tr>
<%	}	%>
<% 
	if(free.size() != 0){
	for(Freeboard freeboard : free){
%>
		<tr>
			<td><%= freeboard.getNo() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/community/freeboardView?no=<%= freeboard.getNo() %>"><%= freeboard.getTitle() %></a>
				<%= freeboard.getCommentCount() > 0 ? "(" + freeboard.getCommentCount() + ")" : "" %>
			</td>
			<td><%= freeboard.getWriter() %></td>
			<td><%= freeboard.getRegDate() %></td>
			<td><%= freeboard.getReadCount() %></td>
			<td><%= freeboard.getLikeCount() %></td>
		</tr>
<%	}	%>
	</table>

<% 
	}else{
%>
	<div class="alert alert-danger" role="alert">
	  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  <span class="sr-only">자유</span>
	  게시글이 없습니다
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%= request.getContextPath() %>/community/freeboardForm'"/>
	</div>
<% } %>
</section>
<br /><br /><br /><br />
<h3 align="center">QnA게시판</h3>
<%
	List<Questionboard> QnA = (List<Questionboard>) request.getAttribute("QnA");
	if(QnA.size() != 0){
%>
	<section id="board-container">
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th><%--첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
				<th>조회수</th>
				<th>추천수</th>
			</tr>
<% } %>
<% 
	if(QnA.size() != 0){
	for(Questionboard questionboard : QnA){
%>
		<tr>
			<td><%= questionboard.getNo() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/community/questionboardView?no=<%= questionboard.getNo() %>"><%= questionboard.getTitle() %></a>
				<%= questionboard.getCommentCount() > 0 ? "(" + questionboard.getCommentCount() + ")" : "" %>
			</td>
			<td><%= questionboard.getWriter() %></td>
			<td><%= questionboard.getRegDate() %></td>
			<td>
<% 	if(questionboard.getAttachCount() > 0){ %>
				<img alt="" src="<%= request.getContextPath() %>/images/attachment-g5d16ffa11_640.png" width="16px" height="14px">	
<%	}	%>
			</td>
			<td><%= questionboard.getReadCount() %></td>
			<td><%= questionboard.getLikeCount() %></td>
		</tr>
<%	}	%>
	</table>
	
<%			
	}else{
%>	
	<div class="alert alert-danger" role="alert">
	  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  <span class="sr-only">QnA</span>
	  게시글이 없습니다
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%= request.getContextPath() %>/community/questionboardForm'"/>
	</div>
<% } %>

</section>
<br /><br /><br /><br />
<h3 align="center"><%= loginMember.getStudy_group()==0 ? "study 게시판":"내 "+loginMember.getStudy_group()+"번 study게시판" %></h3>
<% 
	List<StudyGroupBoard> studyboard = (List<StudyGroupBoard>) request.getAttribute("studyboard");
	if(studyboard.size() != 0){
%>
<section id="board-container">
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
	for(StudyGroupBoard board : studyboard){
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
<%			
	}else if(studyboard.size() == 0 && loginMember.getStudy_group() == 0){
%>	
	<div class="alert alert-danger" role="alert">
	  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  <span class="sr-only">스터디그룹</span>이 없습니다.
	<input type="button" value="스터디 그룹 생성" id = "btn-add" onclick="location.href='<%= request.getContextPath() %>/studygroup/create';">
	</div>
<% }else{ %>
	<div class="alert alert-danger" role="alert">
	  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  <span class="sr-only">스터디그룹</span>게시글이 없습니다.
	<input type="button" value="글쓰기" id = "btn-add" onclick="location.href='<%= request.getContextPath() %>/studygroup/boardForm';"/>
	</div>
<%} %>
</section>

<br /><br /><br /><br /><br /><br />
</body>
</html>

