<%@page import="com.kh.member.model.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@page import="com.kh.member.model.vo.Member"%> 
    
<%
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	Member loginMember = (Member) session.getAttribute("loginMember");
	
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Kola !</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!-- 타이틀 폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Exo:wght@600&family=IBM+Plex+Sans+KR&family=Secular+One&display=swap" rel="stylesheet">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
<script>
$(() => {
	<% if(msg != null){ %>
	alert("<%= msg %>");
	
	<%} %>
	fn_alramList();
});
function noLogin_writing_btn(){
	alert('로그인 후 이용해주세요.'); 
}
function nogroup_writing_btn(){
	alert('스터디그룹 생성 후 이용해주세요.'); 
}
const logout = () => {
	location.href="<%= request.getContextPath() %>/member/logout";
};
</script>


<style>
	header{
		height: 30vh;
		text-align: center;
		margin: 0 auto;
		padding: 0 auto;
		font-size: x-large;
	}
	
	.wrapper{
		width: 80%;
		margin: 0 auto;
		padding: 0 auto;
		font-family: 'InfinitySans-RegularA1';
	}
	#title{
	font-size: 4rem;
	color: black;
	font-family: 'Exo', sans-serif;
	text-decoration-line : none;
	}
	#title span{
	color: #eb4b3f;
	font-family: 'Secular One', sans-serif;
	}
	#cola{
		width: 40px;
		
	}
	#header_title{
		font-size: 3rem;
	color: black;
	font-family: 'Exo', sans-serif;
	text-decoration-line : none;
	}
	#header_title span{
	color: #eb4b3f;
	font-family: 'Secular One', sans-serif;
	}
	.headbadge {
	  display: inline-block;
	  min-width: 10px;
	  padding: 3px 7px;
	  font-size: 12px;
	  font-weight: bold;
	  line-height: 1;
	  color: #fff;
	  text-align: center;
	  white-space: nowrap;
	  vertical-align: baseline;
	  background-color: #777;
	  border-radius: 10px;
	}
	
</style>

</head>
<body>
<div class="wrapper">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
 
 <a id="header_title" href='<%= request.getContextPath() %>'><img id="cola" src="<%= request.getContextPath() %>/images/cola.png" alt="" />Kola <span id="header_title!">!</span></a>

	
        
<!-- 커뮤니티 드롭다운 -->
        <div class="dropdown text-end">
          <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
            커뮤니티
          </a>
          <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
            <li><a id="gathere_study_board" class="dropdown-item" href="<%= request.getContextPath() %>/board/boardList">스터디그룹 모집</a></li>
            <li><a id="free_board" class="dropdown-item" href="<%= request.getContextPath() %>/community/freeboardList">자유 게시판</a></li>
            <li><a id="Q&A_board" class="dropdown-item" href="<%= request.getContextPath() %>/community/questionboardList">Q&A 게시판</a></li>
          </ul>
        </div>
<% if(loginMember == null){ %>
					<!-- 로그인폼 시작 -->
					
      <div class="col-md-3 text-end">
        <button type="button" class="btn btn-outline-danger me-2" onclick="location.href='<%= request.getContextPath() %>/member/memberLogin';">Login</button>
        <button type="button" class="btn btn-danger" value="회원가입" onclick="location.href='<%= request.getContextPath() %>/member/memberEnroll';">Sign-up</button>
      </div>
					
<%} else { %>
					

<!-- 마이페이지 드롭다운 -->
		<span>
		<%= loginMember.getMember_name() %>님, 열공합시다!
		<a href="<%= request.getContextPath() %>/studygroup/applicant">
		<span class="headbadge"></span>
		</a>
		</span>
        <div class="dropdown text-end">
          <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
            
            <img src="<%=request.getContextPath() %>/images/spy.png" width="50">
          </a>
          <ul id="sub_menu" class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
            <li><a class="dropdown-item" href="<%= request.getContextPath() %>/member/memberView">마이페이지</a></li>
            <li><a class="dropdown-item" href="<%= request.getContextPath() %>/studygroup/view">내 스터디그룹</a></li>
            <li><a class="dropdown-item" href="#" onclick="myboardlist();">내 작성글</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" onclick="location.href='<%= request.getContextPath() %>/member/logout'">로그아웃</a></li>

<% if(loginMember != null && MemberService.ADMIN_ROLE.equals(loginMember.getMember_role())){ %>	

            <li><hr class="dropdown-divider"></li>
			<li id="admin_page"><a class="dropdown-item" href="<%= request.getContextPath() %>/admin/memberList">관리자 페이지</a></li>
          </ul>
        </div>
<% } %>
        <% } %>

    </header>

<% if(loginMember != null){ %>
    <form 
    name="myboardListFrm"
    action="<%= request.getContextPath() %>/board/MyBoardList"
    method="GET">
    <input type="hidden" name="memberId" value="<%= loginMember.getMember_id() %>" />
    <input type="hidden" name="studyNo" value="<%= loginMember.getStudy_group() %>" />
    </form>
<% } %>
		<section id="content">
<script>
const myboardlist = () => {
		$(document.myboardListFrm).submit();

}

function fn_alramList(){
	var memberId = {
			loginMember:"<%= (loginMember != null) ? loginMember.getMember_id() : null %>"
	}
	$.ajax({
		url : "<%= request.getContextPath() %>/member/alramList.do",
		data : memberId,
		type : "post",
		dataType: "json",
		success : function(data){
			//console.log("alramList:"+data);
			var count = data.length;
			//console.log(count);
			if(count != 0)
				$(".headbadge").text(count)


		},
		error : function(jqxhr, textStatus, errorThrown){
			console.log("ajax 처리 실패");
			//에러로그
			console.log(jqxhr);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
	
	//3초마다 리스트 갱신
	setTimeout(function() {
		fn_alramList();	
	}, 3000);
}

</script>

