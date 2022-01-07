<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.spring.member.model.vo.Member, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%
	//security 관리하는 인증객체 가져오기
	Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String[] hobby = member.getHobby();
	List<String> hobbyList = hobby != null ? Arrays.asList(hobby) : null;
	pageContext.setAttribute("hobbyList", hobbyList);
	
%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="회원정보" name="title"/>
</jsp:include>

<!-- 인증객체의 principal속성을 pageContext 속성으로 저장 -->
<sec:authentication property="principal" var="loginMember" />

<style>
div#update-container{width:400px; margin:0 auto; text-align:center;}
div#update-container input, div#update-container select {margin-bottom:10px;}
</style>
<div id="update-container">
	<form
		name="memberUpdateFrm" 
		action="${pageContext.request.contextPath}/member/memberUpdate.do" 
		method="post">
		<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="id" id="id" value='<sec:authentication property="principal.username"/>' readonly required/>
		<input type="text" class="form-control" placeholder="이름" name="name" id="name" value='<sec:authentication property="principal.name"/>' required/>
		<input type="date" class="form-control" placeholder="생일" name="birthday" id="birthday" value='<fmt:formatDate value="${loginMember.birthday}" pattern="yyyy-MM-dd"/>'/>
		<input type="email" class="form-control" placeholder="이메일" name="email" id="email" value="${loginMember.email}" required/>
		<input type="tel" class="form-control" placeholder="전화번호 (예:01012345678)" name="phone" id="phone" value="${loginMember.phone}" maxlength="11" required/>
		<input type="text" class="form-control" placeholder="주소" name="address" id="address" value="${loginMember.address}"/>
		<select class="form-control" name="gender" required>
		  <option value="" disabled selected>성별</option>
		  <option value="M" ${loginMember.gender eq 'M' ? 'selected' : ''}>남</option>
		  <option value="F" ${loginMember.gender eq 'F' ? 'selected' : ''}>여</option>
		</select>
		<div class="form-check-inline form-check">
			취미 : &nbsp; 
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby0" value="운동" ${hobbyList.contains('운동') ? 'checked' : '' }>
			<label for="hobby0" class="form-check-label" >운동</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby1" value="등산" ${hobbyList.contains('등산') ? 'checked' : '' }>
			<label for="hobby1" class="form-check-label" >등산</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby2" value="독서" ${hobbyList.contains('독서') ? 'checked' : '' }>
			<label for="hobby2" class="form-check-label" >독서</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby3" value="게임" ${hobbyList.contains('게임') ? 'checked' : '' }>
			<label for="hobby3" class="form-check-label" >게임</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="hobby" id="hobby4" value="여행" ${hobbyList.contains('여행') ? 'checked' : '' }>
			<label for="hobby4" class="form-check-label" >여행</label>&nbsp;
		</div>
		<div class="form-check-inline form-check">
			권한 : &nbsp;
			<input type="checkbox" class="form-check-input" name="authorities" id="authority1" value="ROLE_USER" <sec:authorize access="hasRole('USER')">checked</sec:authorize> >
			<label for="authority1" class="form-check-label" >일반사용자</label>&nbsp;
			<input type="checkbox" class="form-check-input" name="authorities" id="authority2" value="ROLE_ADMIN" <sec:authorize access="hasRole('ADMIN')">checked</sec:authorize>>
			<label for="authority2" class="form-check-label" >관리자</label>&nbsp;
		</div>
		<br />
		<input type="submit" class="btn btn-outline-success" value="수정" >&nbsp;
		<input type="reset" class="btn btn-outline-success" value="취소">
	</form>
</div>
<script>
$(document.memberUpdateFrm).submit((e)=>{
	e.preventDefault(); // 제출금지
	
	const csrfHeader = "${_csrf.headerName}";
	const csrfToken = "${_csrf.token}";
	const headers = {};
	headers[csrfHeader] = csrfToken;
	
	$.ajax({
		url : "${pageContext.request.contextPath}/member/memberUpdate.do",
		data : $(e.target).serialize(),
		headers: headers,
		method: "POST",
		success(resp){
			console.log(resp);
		},
		error: console.log
	});
	
});
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
