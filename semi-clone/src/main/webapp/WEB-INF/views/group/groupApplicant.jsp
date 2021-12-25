<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/leader.css" />
<%
	List<Member> list = (List<Member>) request.getAttribute("list");
%>
<div id = "board-container">
	<h2>그룹 관리</h2>
	<table id="tbl-board">
			<tr>
				<th>이름</th>
				<th>아이디</th>
				<th>성별</th>
				<th>관심언어</th>
				<th>이메일</th>
				<th>핸드폰</th>
				<th>주소</th>
				<th colspan = "2">가입</th>
				
			</tr>
			
			
	<%		
		for(Member member : list){
	%>
			<tr>
				<td><%= member.getMember_name() %></td>
				<td><%= member.getMember_id() %></td>
				<td><%= member.getGender() %></td>
				<td><%= member.getLanguage() %></td>
				<td><%= member.getEmail() %></td>
				<td><%= member.getPhone() %></td>
				<td><%= member.getAddress() %></td>
				<td><button type ="button" value = "<%= member.getMember_id()%>" class="btn btn-success" id = "btn-accept">
				<svg xmlns="http://www.w3.org/2000/svg" id = "check" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
 				 	<path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
					</svg>
				</button></td>
				
				
				<td><button type="button" value = "<%= member.getMember_id() %>" class="btn btn-danger" id = "btn-refuse"><svg xmlns="http://www.w3.org/2000/svg" id = "refuse" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
</svg></button></td>
			</tr>
				
			<%} %>
	</table>
</div>
	
<form
	name = "applicantAcceptFrm"
	method = "POST" 
	action="<%= request.getContextPath() %>/studygroup/applicantCheck">
	<input type="hidden" name = "temp" value = "1" />
</form>
<form
	name = "applicantRefuseFrm"
	method = "POST" 
	action="<%= request.getContextPath() %>/studygroup/applicantCheck">
	<input type="hidden" name = "temp" value = "0" />
	</form>
<script>

$("#btn-accept").click((e) => {
	 const memberId = $(e.target).val();
	 console.log(memberId);
	 const inputId = `<input type="hidden" name="memberId" value="\${memberId}" />`;
	 $(document.applicantAcceptFrm)
	 					.append(inputId)
	 					.submit();
	 
});

$("#btn-refuse").click((e) => {
	 const memberId = $(e.target).val();
	 const inputId = `<input type="hidden" name="memberId" value="\${memberId}" />`;
	 $(document.applicantRefuseFrm)
	 					.append(inputId)
	 					.submit();
});


</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>