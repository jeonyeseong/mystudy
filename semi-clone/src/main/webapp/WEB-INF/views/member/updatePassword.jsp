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
      margin-left: 300px;
      }
      .div-container{
      text-align: center;
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="form-validation.css" rel="stylesheet">
  </head>
  <body>
    
    <div class="py-5 text-center">
      <h2>비밀번호 변경</h2>
    </div>
<div class="container">

    

      <div class="col-md-7 col-lg-8">
        <form 
        	name="updatePwdFrm"
			method="post">
          <div class="row g-3">
            <div class="col-12">
              <label for="username" class="form-label">현재 비밀번호</label>
              <div class="input-group has-validation">
                <input type="password" class="form-control" name="oldPassword" id="oldPassword" required>
              </div>
            </div>
            
            <div class="col-12">
              <label for="username" class="form-label">변경할 비밀번호</label>
              <div class="input-group has-validation">
                <input type="password" class="form-control"  name="newPassword" id="newPassword" required>
              </div>
            </div>
            
            <div class="col-12">
              <label for="username" class="form-label">비밀번호 확인</label>
              <div class="input-group has-validation">
                <input type="password" class="form-control" id="newPasswordCheck" required>
              </div>
            </div>
      
          </div>
          <hr class="my-4">
<div class="div-container">
          <input class="w-50 btn btn-primary btn-lg" type="button" onclick="updateMember();" value="변경"/>
</div>
        </form>
      </div>
    </div>

<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

    <script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

      <script src="form-validation.js"></script>
  </body>
</html>
<script>
const updateMember = () => {
	// 폼의 action값을 할당후 제출!
	$("[name=updatePwdFrm]")
		.attr("action", "<%=request.getContextPath()%>/member/updatePassword")
		.submit();
};

/**
 * 폼제출 유효성검사
 */ 
$("[name=updatePwdFrm]").submit(function(){
	var $oldPassword = $("#oldPassword");
	var $newPassword = $("#newPassword");
	
	if(!passwordValidate()){
		return false;
	}
	
	if($oldPassword.val() == $newPassword.val()){
		alert("기존비밀번호와 신규비밀번호는 같을 수 없습니다.");
		$oldPassword.select();
		return false;
	}
	
	return true;	

});

/**
 * 
 */ 
$("#newPasswordCheck").blur(passwordValidate);

/**
 * 신규비밀번호 일치 검사 
 */ 
function passwordValidate(){
	var $newPassword = $("#newPassword");
	var $newPasswordCheck = $("#newPasswordCheck");
	if($newPassword.val() != $newPasswordCheck.val()){
		alert("입력한 비밀번호가 일치하지 않습니다.");
		$newPassword.select();
		return false;
	}
	return true;	
}

</script>
