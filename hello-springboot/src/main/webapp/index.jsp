﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu - RestAPI</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- bootstrap js: jquery load 이후에 작성할것.-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<!-- 사용자작성 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" />
<style>
div.menu-test{width:50%; margin:0 auto; text-align:center;}
div.result{width:70%; margin:0 auto;}
</style>
</head>
<body>
<div id="container">
	<header>
		<div id="header-container">
			<h2>Menu - RestAPI</h2>
		</div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">
				<img src="${pageContext.request.contextPath }/resources/images/logo-spring.png" alt="스프링로고" width="50px" />
			</a>
		  	<!-- 반응형으로 width 줄어들경우, collapse버튼관련 -->
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
		  	</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				
			 </div>
		</nav>
	</header>
	<section id="content">
		<div id="menu-container" class="text-center">
			<!-- 1. GET /menus-->
	        <div class="menu-test">
	            <h4>전체메뉴조회(GET)</h4>
	            <input type="button" class="btn btn-block btn-outline-success btn-send" id="btn-menus" value="전송" />
	        </div>
	        <div class="result" id="menus-result"></div>
	        <script>
	        $("#btn-menus").click((e) => {
	        	
	        	$.ajax({
	        		url: "${pageContext.request.contextPath}/menus",
	        		method: "GET", 
	        		success(resp){
	        			console.log(resp);
	        			
	        			displayTable("#menus-result", resp);
	        		},
	        		error: console.log
	        	});
	        	
	        });
	        
	        const displayTable = (selector, data) => {
	        	const $target = $(selector);
	        	let table = `<table class='tabe'>
	        		<tr>
	        			<th>번호</th>
	        			<th>음식점</th>
	        			<th>메뉴명</th>
	        			<th>가격</th>
	        			<th>타입</th>
	        			<th>맛</th>
	        		</tr>`
	        		
	        		if(data.length){
	        			$(data).each((i, menu) => {
	        				console.log(i, menu);
	        				const {id, restaurant, name, price, type, taste} = menu;
	        				table += `<tr>
	        					<td>\${id}</td>
	        					<td>\${restaurant}</td>
	        					<td>\${name}</td>
	        					<td>￦\${price.toLocaleString()}</td>
	        					<td>\${type}</td>
	        					<td>\${taste}</td>
	        				</tr>`;
	        				
	        			});
	        		}
	        		else{
	        			table += `<tr><td colspan='6'>검색된 결과가 없습니다.</td></tr>`;
	        		}
	        		
	        		table += `</table>`;
	        		
	        		$target.html(table);
	        };
	        
	        </script>




			<div class="menu-test">
				<h4>추천메뉴(GET)</h4>
				<form id="menuRecommendationFrm">
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" name="type" id="get-no-type" value="all" checked>
						<label for="get-no-type" class="form-check-label">모두</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="get-kr" value="kr">
						<label for="get-kr" class="form-check-label">한식</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="get-ch" value="ch">
						<label for="get-ch" class="form-check-label">중식</label>&nbsp;
						<input type="radio" class="form-check-input" name="type" id="get-jp" value="jp">
						<label for="get-jp" class="form-check-label">일식</label>&nbsp;
					</div>
					<br />
					<div class="form-check form-check-inline">
						<input type="radio" class="form-check-input" name="taste" id="get-no-taste" value="all" checked>
						<label for="get-no-taste" class="form-check-label">모두</label>&nbsp;
						<input type="radio" class="form-check-input" name="taste" id="get-hot" value="hot" checked>
						<label for="get-hot" class="form-check-label">매운맛</label>&nbsp;
						<input type="radio" class="form-check-input" name="taste" id="get-mild" value="mild">
						<label for="get-mild" class="form-check-label">순한맛</label>
					</div>
					<br />
					<input type="submit" class="btn btn-block btn-outline-success btn-send" value="전송" >
				</form>
			</div>
			<div class="result" id="menuRecommendation-result"></div>
			<script>
			$(menuRecommendationFrm).submit((e)=>{
				e.preventDefault();
				
				const type = $(e.target).find("[name=type]:checked").val();
				const taste = $(e.target).find("[name=taste]:checked").val();
				console.log(type,taste);
				
				$.ajax({
					url: `${pageContext.request.contextPath}/menus/\${type}/\${taste}`,
					method: "GET",
					success(resp){
						console.log(resp,resp.length);
						displayTable("#menuRecommendation-result", resp);
					},
					error: console.log
				});
			});
			
			</script>
			
		<div class="menu-test">
				<h4>실습문제 한건조회</h4>
				<form id="selectOneFrm">
					<input type="text" id="selectOne"/>
					<label for="selectOne">선택</label>
					<input type="submit" class="btn btn-block btn-outline-success btn-send" value="전송" >
				</form>
			</div>
			<div class="result" id="selectOneFrm-result"></div>
			<script>
			$(selectOneFrm).submit((e) => {
				e.preventDefault();
				
				const one = $(e.target).find("#selectOne").val();
				console.log(one);
				
				$.ajax({
					url: `${pageContext.request.contextPath}/menu/\${one}`,
					method:"GET",
					success(resp){
						console.log(resp);
			        	let table = `<table class='tabe'>
			        		<tr>
			        			<th>번호</th>
			        			<th>음식점</th>
			        			<th>메뉴명</th>
			        			<th>가격</th>
			        			<th>타입</th>
			        			<th>맛</th>
			        		</tr>`
			        		
        				const {id, restaurant, name, price, type, taste} = resp;
        				table += `<tr>
        					<td>\${id}</td>
        					<td>\${restaurant}</td>
        					<td>\${name}</td>
        					<td>￦\${price.toLocaleString()}</td>
        					<td>\${type}</td>
        					<td>\${taste}</td>
        				</tr>`;
        				


        		
        		table += `</table>`;
        		
        		$("#selectOneFrm-result").html(table);
					},
					error:console.log
				});
			});
			</script>
			
			
    <!-- 2.POST /menu -->
	<div class="menu-test">
		<h4>메뉴 등록하기(POST)</h4>
		<form id="menuEnrollFrm">
			<input type="text" name="restaurant" placeholder="음식점" class="form-control" value="맘스터치"/>
			<br />
			<input type="text" name="name" placeholder="메뉴" class="form-control" value="싸이버거"/>
			<br />
			<input type="number" name="price" placeholder="가격" class="form-control" value="8500"/>
			<br />
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" name="type" id="post-kr" value="kr" checked>
				<label for="post-kr" class="form-check-label">한식</label>&nbsp;
				<input type="radio" class="form-check-input" name="type" id="post-ch" value="ch">
				<label for="post-ch" class="form-check-label">중식</label>&nbsp;
				<input type="radio" class="form-check-input" name="type" id="post-jp" value="jp">
				<label for="post-jp" class="form-check-label">일식</label>&nbsp;
			</div>
			<br />
			<div class="form-check form-check-inline">
				<input type="radio" class="form-check-input" name="taste" id="post-hot" value="hot" checked>
				<label for="post-hot" class="form-check-label">매운맛</label>&nbsp;
				<input type="radio" class="form-check-input" name="taste" id="post-mild" value="mild">
				<label for="post-mild" class="form-check-label">순한맛</label>
			</div>
			<br />
			<input type="submit" class="btn btn-block btn-outline-success btn-send" value="등록" >
		</form>
	</div>
	<script>
	$(menuEnrollFrm).submit((e) => {
		e.preventDefault();
		
		const obj = {
			restaurant : $("[name=restaurant]", e.target).val(),
			name : $("[name=name]", e.target).val(),
			price : $("[name=price]", e.target).val(),
			type : $("[name=type]:checked", e.target).val(),
			taste : $("[name=taste]:checked", e.target).val(),
		};
		
		console.log(obj); // javascript객체
		const jsonStr = JSON.stringify(obj); // json문자열로 바꿔줌
		console.log(jsonStr); // json 문자열
		
		$.ajax({
			url: `${pageContext.request.contextPath}/menu`,
			method: "POST",
			data: jsonStr,
			contentType: "application/json; charset=utf-8",
			success(resp){
				console.log(resp)
				alert(resp.msg);
			},
			error: console.log
		});
	});
	</script>
	
	
		<!-- #3.PUT -->
		<div class="menu-test">
			<h4>메뉴 수정하기(PUT)</h4>
			<p>메뉴번호를 사용해 해당메뉴정보를 수정함.</p>
			<form id="menuSearchFrm">
				<input type="text" name="id" placeholder="메뉴번호" class="form-control" /><br />
				<input type="submit" class="btn btn-block btn-outline-primary btn-send" value="검색" >
			</form>
		
			<hr />
			<form id="menuUpdateFrm">
				<!-- where조건절에 사용할 id를 담아둠 -->
				<input type="hidden" name="id" />
				<input type="text" name="restaurant" placeholder="음식점" class="form-control" />
				<br />
				<input type="text" name="name" placeholder="메뉴" class="form-control" />
				<br />
				<input type="number" name="price" placeholder="가격" step="1000" class="form-control" />
				<br />
				<div class="form-check form-check-inline">
					<input type="radio" class="form-check-input" name="type" id="put-kr" value="kr" checked>
					<label for="put-kr" class="form-check-label">한식</label>&nbsp;
					<input type="radio" class="form-check-input" name="type" id="put-ch" value="ch">
					<label for="put-ch" class="form-check-label">중식</label>&nbsp;
					<input type="radio" class="form-check-input" name="type" id="put-jp" value="jp">
					<label for="put-jp" class="form-check-label">일식</label>&nbsp;
				</div>
				<br />
				<div class="form-check form-check-inline">
					<input type="radio" class="form-check-input" name="taste" id="put-hot" value="hot" checked>
					<label for="put-hot" class="form-check-label">매운맛</label>&nbsp;
					<input type="radio" class="form-check-input" name="taste" id="put-mild" value="mild">
					<label for="put-mild" class="form-check-label">순한맛</label>
				</div>
				<br />
				<input type="submit" class="btn btn-block btn-outline-success btn-send" value="수정" >
			</form>
		</div>
		
		<script>
		$(menuSearchFrm).submit((e)=>{
			e.preventDefault();
			
			const id = $("[name=id]", e.target).val();
			console.log(id);
			
			$.ajax({
				url: `${pageContext.request.contextPath}/menu/\${id}`,
				method: "GET",
				success(resp){
					console.log(resp);
					const {id, restaurant, name, price, type, taste} = resp;
					const $frm = $(menuUpdateFrm);
					$frm.find("[name=id]").val(id);
					$frm.find("[name=restaurant]").val(restaurant);
					$frm.find("[name=name]").val(name);
					$frm.find("[name=price]").val(price);
					
					$frm.find(`[name=type][value=\${type}]`).prop("checked",true);
					$frm.find(`[name=taste][value=\${taste}]`).prop("checked",true);
					
					
				},
				error(xhr, textStatus, err){
					if(xhr.status == 404)
						alert("조회한 메뉴는 존재하지 않습니다.");
					else
						console.log(xhr, textStatus, err);
				}
			});
		});
		
		$(menuUpdateFrm).submit((e) => {
			e.preventDefault();
			
			const obj = {
				//id : $("#menuUpdateFrm [name=id]").val(),
				id : $("[name=id]", e.target).val(),
				restaurant : $("[name=restaurant]", e.target).val(),
				name : $("[name=name]", e.target).val(),
				price : $("[name=price]", e.target).val(),
				type : $("[name=type]:checked", e.target).val(),
				taste : $("[name=taste]:checked", e.target).val(),
			};
			
			
			console.log("obj = ",obj); // javascript객체
			const jsonStr = JSON.stringify(obj); // json문자열로 바꿔줌
			console.log(jsonStr); // json 문자열
			
			$.ajax({
				url: `${pageContext.request.contextPath}/menu`,
				method: "PUT",
				data: jsonStr,
				contentType: "application/json; charset=utf-8",
				success(resp){
					console.log(resp)
					alert(resp.msg);
				},
				error: console.log,
				complte(){
					// #menuUpdateFrm.reset()
					$(e.target)[0].reset();
					$(menuSearchFrm)[0].reset();
				}
			});
		});
		</script>
		
	<div class="menu-test">
    	<h4>메뉴 삭제하기(DELETE)</h4>
    	<p>메뉴번호를 사용해 해당메뉴정보를 삭제함.</p>
    	<form id="menuDeleteFrm">
    		<input type="text" name="id" placeholder="메뉴번호" class="form-control" /><br />
    		<input type="submit" class="btn btn-block btn-outline-danger btn-send" value="삭제" >
    	</form>
    </div>
    <script>
    $(menuDeleteFrm).submit((e)=> {
    	e.preventDefault();
    	
    	const id = $(e.target).find("[name=id]").val();
    	
    	$.ajax({
    		url : `${pageContext.request.contextPath}/menu/\${id}`,
    		method: "DELETE",
    		success(resp){
    			console.log(resp);
    			alert(resp.msg);
    		},
    		error(xhr, statusText, err){
    			switch(xhr.status){
    			case 404: alert("삭제 실패"); break;
    			default: console.log(xhr, statusText, err);
    			}
    		}
    	});
    	
    });
    </script>
				
			
		</div>
	</section>
	<footer>
		<p>&lt;Copyright 2017. <strong>KH정보교육원</strong>. All rights reserved.&gt;</p>
	</footer>
	
</div>
</body>
</html>
