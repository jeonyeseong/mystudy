<%@page import="com.kh.board.model.vo.Frontboard"%>
<%@page import="com.kh.admin.vo.Statistics"%>
<%@page import="com.kh.member.model.service.MemberService"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	
	List<Member> list = (List<Member>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	
%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<style>
div#search-container {margin:0 0 10px 0; padding:3px; background-color: rgba(211, 211, 211, 0.6); text-align: center;}
div#search-memberId {display: <%= searchType == null || "memberId".equals(searchType) ? "inline-block" : "none" %>;}
div#search-memberName{display: <%= "memberName".equals(searchType) ? "inline-block" : "none" %>;}
div#search-gender{display: <%= "gender".equals(searchType) ? "inline-block" : "none" %>;}
.nav nav-pills{text-align: right;}
</style>
<script>
$(() => {

<% if(msg != null){ %>	

	alert("<%= msg %>");
	
<% } %>
});
</script>



<section id="memberList-container">
	<h2 align="center">관리자페이지</h2>
<ul class="nav nav-pills">
  <li role="presentation" class="active"><a class="statview" href="<%= request.getContextPath() %>/admin/memberStatistics">통계 확인</a></li>
</ul>

	<div id="search-container">
        <label for="searchType">검색타입 :</label> 
        <select id="searchType">
            <option value="memberId" <%= "memberId".equals(searchType) ? "selected" : "" %>>아이디</option>		
            <option value="memberName" <%= "memberName".equals(searchType) ? "selected" : "" %>>회원명</option>
            <option value="gender" <%= "gender".equals(searchType) ? "selected" : "" %>>성별</option>

        </select>
       
        <div id="search-memberId" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="memberId"/>
                <input type="text" name="searchKeyword" value="<%= "memberId".equals(searchType) ? searchKeyword : "" %>" size="25" placeholder="검색할 아이디를 입력하세요."/>
                <button type="submit">검색</button>			
            </form>	
        </div>

        <div id="search-memberName" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="memberName"/>
                <input type="text" name="searchKeyword" value="<%= "memberName".equals(searchType) ? searchKeyword : "" %>" size="25" placeholder="검색할 이름을 입력하세요."/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-gender" class="search-type">
            <form action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="gender"/>
                <input type="radio" name="searchKeyword" value="M" <%= "gender".equals(searchType) && "M".equals(searchKeyword) ? "checked" : "" %>> 남
                <input type="radio" name="searchKeyword" value="F" <%= "gender".equals(searchType) && "F".equals(searchKeyword) ? "checked" : "" %>> 여
                <button type="submit">검색</button>
            </form>
        </div>
    </div>
	

	
	<table class="table">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>회원권한</th>
				<th>성별</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>선호언어</th>
				<th>가입일</th>
			</tr>
		</thead>
		<tbody>
<%  
	for(Member member  : list){
%>
			<tr>
				<td><%= member.getMember_id() %></td>
				<td><%= member.getMember_name() %></td>
				<td>
					<form 
						name="memberRoleUpdateFrm"
						action="<%= request.getContextPath() %>/admin/memberRoleUpdate"
						method="POST">
						<input type="hidden" name="memberId" value="<%= member.getMember_id() %>" />
						<select name="memberRole" class="member-role">
							<option value="<%= MemberService.USER_ROLE %>" <%= MemberService.USER_ROLE.equals(member.getMember_role()) ? "selected" : "" %>>일반</option>
							<option value="<%= MemberService.ADMIN_ROLE %>" <%= MemberService.ADMIN_ROLE.equals(member.getMember_role()) ? "selected" : "" %>>관리자</option>
						</select>
					</form>
				</td>
				<td><%= member.getGender() %></td>
				<td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
				<td><%= member.getPhone() %></td>
				<td><%= member.getAddress() != null ? member.getAddress() : "" %></td>
				<td><%= member.getLanguage() != null ? member.getLanguage() : "" %></td>
				<td><%= member.getEnroll_date() %></td>
			</tr>

<%
	}
%>	
		</tbody>
	</table>
<nav>
  <ul class="pager" >
  	<li><%= request.getAttribute("pagebar") %></li>
  </ul>
</nav>

</section>
<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
<script>
/**
 * 검색 div 노출
 */
$(searchType).change((e) => {
	$(".search-type").hide();
	
	const v = $(e.target).val();
	$("#search-" + v).css("display", "inline-block");
});


$(".member-role").change((e) => {
	const $select = $(e.target);
	const memberRole = $select.val();
	console.log(memberRole);
	if(confirm(`회원의 권한을 [\${memberRole}]로 변경하시겠습니까?`)){
		const $frm = $select.parent();
		$frm.submit();
	}
	else {
		// selected 초기값으로 복원
		$select.children("[selected]").prop("selected", true);
	}
});
</script>








