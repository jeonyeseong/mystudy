<%@page import="com.kh.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>  
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Checkout example · Bootstrap v5.1</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/checkout/">

    

    <!-- Bootstrap core CSS -->
<link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      .container{
      margin-left: 230px;
      }
      .div-container{
      margin-left: 200px;
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="form-validation.css" rel="stylesheet">
  </head>
  <body>
    
    <div class="py-5 text-center">
      <h2>마이페이지</h2>
    </div>
<div class="container">

    

      <div class="col-md-7 col-lg-8">
        <form class="needs-validation" id="memberUpdateFrm" method="post" novalidate>
          <div class="row g-3">
            <div class="col-12">
              <label for="username" class="form-label">아이디</label>
              <div class="input-group has-validation">
                <span class="input-group-text">@</span>
                <input type="text" class="form-control" id="username" name="memberId" value="<%= loginMember.getMember_id() %>" readonly>
              </div>
            </div>

            <div class="col-12">
              <label for="username" class="form-label">이름</label>
              <div class="input-group has-validation">
                <input type="text" class="form-control" id="username" name="memberName" value="<%= loginMember.getMember_name() %>" required>
              </div>
            </div>

            <div class="col-12">
              <label for="email" class="form-label">이메일</label>
              <input type="email" class="form-control" id="email" name="email" value="<%= loginMember.getEmail() %>">
            </div>

            <div class="col-12">
              <label for="address" class="form-label">핸드폰</label>
              <input type="text" class="form-control" name="phone" id="phone" maxlength="11" value="<%= loginMember.getPhone() %>"required>
            </div>
            
            <div class="col-12">
              <label for="address" class="form-label">주소</label>
              <input type="text" class="form-control" id="address" name="address" value="<%= loginMember.getAddress() %>" required>
            </div>

          <h4 class="mb-3">성별</h4>
          <div class="my-3">
            <div class="form-check">
              <input id="credit" name="gender" type="radio" class="form-check-input" value="M" <%= "M".equals(loginMember.getGender()) ? "checked" : "" %>>
              <label class="form-check-label">남</label>
            </div>
            <div class="form-check">
              <input id="credit" type="radio" class="form-check-input" name="gender" value="F" <%= "F".equals(loginMember.getGender()) ? "checked" : "" %>>
              <label class="form-check-label">여</label>
            </div>
          </div>
          
                    <h4 class="mb-3">선호 언어</h4>
          <div class="my-3">
            <div class="form-check">
              <input id="credit" type="radio" class="form-check-input" name="language" value="c" <%= loginMember.getLanguage().contains("c") ? "checked" : "" %>>
              <label class="form-check-label" for="credit">C</label>
            </div>
            <div class="form-check">
              <input id="debit" type="radio" class="form-check-input" name="language" value="c++" <%= loginMember.getLanguage().contains("c++") ? "checked" : "" %>>
              <label class="form-check-label" for="debit">C++</label>
            </div>
            <div class="form-check">
              <input id="paypal" type="radio" class="form-check-input" name="language" value="java" <%= loginMember.getLanguage().contains("java") ? "checked" : "" %>>
              <label class="form-check-label" for="paypal">Java</label>
            </div>
            <div class="form-check">
              <input id="paypal" type="radio" class="form-check-input" name="language" value="javaScript" <%= loginMember.getLanguage().contains("javaScript") ? "checked" : "" %>>
              <label class="form-check-label" for="paypal">JavaScript</label>
            </div>
            <div class="form-check">
              <input id="paypal" type="radio" class="form-check-input" name="language" value="Spring" <%= loginMember.getLanguage().contains("Spring") ? "checked" : "" %>>
              <label class="form-check-label" for="paypal">Spring</label>
            </div>
            <div class="form-check">
              <input id="paypal" type="radio" class="form-check-input" name="language" value="Python" <%= loginMember.getLanguage().contains("Python") ? "checked" : "" %>>
              <label class="form-check-label" for="paypal">Python</label>
            </div>
          </div>

      
          </div>
          <hr class="my-4">
<div class="div-container">
          <input class="w-40 btn btn-primary btn-lg" type="button" onclick="updateMember();" value="정보수정"/>
          <input class="w-40 btn btn-primary btn-lg" type="button" onclick="updatePassword();" value="비밀번호변경"/>
          <input class="w-40 btn btn-primary btn-lg" type="button" onclick="deleteMember();" value="탈퇴"/>
</div>
        </form>
      </div>
    </div>

<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

    <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

      <script src="form-validation.js"></script>
  </body>
</html>


<!-- 회원탈퇴폼 : POST /member/memberDelete 전송을 위해 시각화되지 않는 폼태그 이용 -->
<form name="memberDelFrm" action="<%= request.getContextPath() %>/member/memberDelete" method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMember_id() %>" />
</form>

<script>
const updatePassword = () => location.href = "<%= request.getContextPath() %>/member/updatePassword";
const deleteMember = () => {
	if(confirm("정말로 탈퇴하시겠습니까?")){
		$(document.memberDelFrm).submit();
	}
}
const updateMember = () => {
	// 폼의 action값을 할당후 제출!
	$(memberUpdateFrm)
		.attr("action", "<%= request.getContextPath() %>/member/memberUpdate")
		.submit();
};
</script>
