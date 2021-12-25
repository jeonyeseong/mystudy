<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	

	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	//쿠키처리
	Cookie[] cookies = request.getCookies();
	String saveMemberId = null;
	if(cookies != null){
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			String value = cookie.getValue();
			System.out.println(name + " = " + value);
			if("saveId".equals(name)){
				saveMemberId = value;
			}
		}
	}
	System.out.println("saveMemberId@header.jsp = " + saveMemberId);
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
$(() => {
	<% if(msg != null){ %>
	alert("<%= msg %>");
	
	<%} %>
});
</script>
<!-- 타이틀 폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Exo:wght@600&family=IBM+Plex+Sans+KR&family=Secular+One&display=swap" rel="stylesheet">
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
      }: center!important;
}
body {
    display: flex;
    align-items: center;
    padding-top: 40px;
    padding-bottom: 40px;
}
form {
    display: block;
    width: 400px;
    margin: 0 auto;
	margin-top: 100px;
	background-color: white;
}
	#cola{
		width: 40px;
	}
	
	/*로그인 글씨*/
	.mb-3{
		margin-top: 20px;
		margin-botton: 20px;
		font-family: 'Exo', sans-serif;
	}
    </style>

    
    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
</head>
 <body class="text-center">
    
<main class="form-signin">

  <form
	id="loginFrm" 
	action="<%= request.getContextPath() %>/member/login"
	method="GET">
	<a href="<%= request.getContextPath() %>">
    	<img id="cola" src="<%= request.getContextPath() %>/images/cola.png" alt="" />
	</a>
    <h1 class="h3 mb-3 fw-normal">Login</h1>

    <div class="form-floating">
      <input type="id" class="form-control" id="floatingInput" name="memberId" placeholder="ID" tabindex="1" value="<%= saveMemberId != null ? saveMemberId : "" %>">
      <label for="floatingInput">ID</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" name="password" placeholder="Password" tabindex="2">
      <label for="floatingPassword">Password</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" name="saveId" id="saveId" <%= saveMemberId != null ? "checked" : "" %>> Remember me
      </label>
    </div>
    <button class="w-100 btn btn-lg btn-danger" type="submit" tabindex="3">Sign in</button>
  </form>
</main>
  </body>
</html>
