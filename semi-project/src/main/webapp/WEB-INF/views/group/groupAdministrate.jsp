<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.kh.studygroup.model.vo.StudyGroupMember"%> 
<%@ page import = "java.util.List" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/leader.css" />

<%
	
	List<StudyGroupMember> list = (List<StudyGroupMember>) request.getAttribute("MemberList");
%>

<div id = "board-container">
<h2>그룹 관리</h2>
	<table id="tbl-board">
	
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>회원 퇴장</th>

			</tr>
		</thead>
		
		
		<tbody>
			<% for(StudyGroupMember member : list){ %>
				<tr>
					<td><%= member.getGroupMemberId() %></td>
					<td><%= member.getGroupMemberName() %></td>
					
					<td><button type="button" value = "<%= member.getGroupMemberId() %>" class="btn btn-danger" id = "btn-accept">그룹 탈퇴</button></td>
				</tr>	
			<%} %>	
		
		</tbody>
	

	
	</table>
</div>
<form
	name = "AdministrateFrm"
	method = "POST" 
	action="<%= request.getContextPath() %>/studygroup/administrate">
	<input type="hidden" name = "studyGroup" value = "<%= loginMember.getStudy_group() %>" />
</form>

<script>

$("#btn-accept").click((e) => {
	console.log("추방");
	
	 const memberId = $(e.target).val();
	 const inputId = `<input type="hidden" name="memberId" value="\${memberId}" />`;
	 var returnValue = confirm("정말로 추방하시겠습니까?");
	 console.log(returnValue);
	 if(returnValue){
	
		 $(document.AdministrateFrm)
		 					.append(inputId)
		 					.submit();
		 
	 }
	 else{
		 return;
	 }
	 
});
</script>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>