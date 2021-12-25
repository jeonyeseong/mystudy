<%@page import="com.kh.studygroup.model.vo.StudyGroup"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.kh.studygroup.model.vo.StudyGroupMember"%> 
<%@ page import = "java.util.List" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/studygroup.css" />
<%
	
	List<StudyGroupMember> list = (List<StudyGroupMember>) request.getAttribute("MemberList");
	String memberRole = (String) request.getAttribute("memberRole");
	StudyGroup group = (StudyGroup) request.getAttribute("group");
%>



<% if(loginMember.getStudy_group() != 0) {%>
	<h1 id = "groupTitle"><%= group.getGroup_name() %></h1>
	<hr />
	<ul id = "memberList">
	
		<% for(StudyGroupMember member : list){ %>
			<li><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-lightbulb-fill" viewBox="0 0 16 16">
  <path d="M2 6a6 6 0 1 1 10.174 4.31c-.203.196-.359.4-.453.619l-.762 1.769A.5.5 0 0 1 10.5 13h-5a.5.5 0 0 1-.46-.302l-.761-1.77a1.964 1.964 0 0 0-.453-.618A5.984 5.984 0 0 1 2 6zm3 8.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1l-.224.447a1 1 0 0 1-.894.553H6.618a1 1 0 0 1-.894-.553L5.5 15a.5.5 0 0 1-.5-.5z"/>
</svg> <%= member.getGroupMemberName() %></li>
		<%} %>		

	</ul>
	
	<hr />
	
	<table id = "rankingTable">
		<thead>
			<tr>
				<th>랭킹</th>
				<th>이름</th>
				<th>공부시간</th>
			</tr>
		</thead>
		
		<tbody>
			
			<% for(int i = 0; i < list.size(); i++){ StudyGroupMember member = list.get(i); %>
			
			<tr>
				<td><%= i+1 %></td>
				<td><%= member.getGroupMemberName() %></td>
				<td><%= member.getStudyTime() %></td>
			</tr>
			<%} %>
			
		</tbody>
	</table>
	<br />
	<div id = "act-btn">
		<button type="button" value = "공부 시작!" onclick="location.href='<%= request.getContextPath() %>/studygroup/stopwatch'" class="btn btn-primary">공부 시작</button>
		<button type="button" onclick="location.href='<%= request.getContextPath() %>/studygroup/chat'" class="btn btn-primary">그룹 채팅</button>
		<button type="button" onclick="location.href='<%= request.getContextPath() %>/studygroup/boardlist'" class="btn btn-primary">그룹 게시판</button>
		
		<% if(memberRole.equals("A")){ %>
		
		<button type="button" onclick="location.href='<%= request.getContextPath() %>/studygroup/applicant'" class="btn btn-primary">신청자 현황</button>
		<button type="button" onclick="location.href='<%= request.getContextPath() %>/studygroup/administrate'" class="btn btn-primary">스터디 멤버 관리</button>
		
	
	</div>
		
		
		
	<% } %>
<%} else{ %>

	<div id = "createMsg">
		
		<h2>소속된 스터디 그룹이 없습니다 그룹을 생성하세요.</h2>
		<br />
		<button type="button" onclick="location.href='<%= request.getContextPath() %>/studygroup/create'" class=" btn btn-info">스터디 그룹 생성</button>
	
	</div>
	
	<%} %>





<br /><br /><br />


<%@ include file="/WEB-INF/views/common/footer.jsp" %>