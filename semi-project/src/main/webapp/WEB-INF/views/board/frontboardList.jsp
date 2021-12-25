<%@page import="com.kh.board.model.vo.Frontboard"%>
<%@page import="com.kh.studygroup.model.vo.StudyGroup"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/frontboard.css" />


<script>
$(function(){
	var clicks = 1;
	$('.libanner1').click(function() {
		if(clicks%2 != 0){
		  $(this).css('opacity', '0.3');
		  $('.javaScript').hide();
		  
		} else{
		  $(this).css('opacity', '1');
		  $('.javaScript').show();
		}
		++clicks;
	});
});

$(function(){
	var clicks = 1;
	$('.libanner2').click(function() {
		if(clicks%2 != 0){
		  $(this).css('opacity', '0.3');
		  $('.java').hide();
		} else{
		  $(this).css('opacity', '1');
		  $('.java').show();
		}
		++clicks;
	});
});

$(function(){
	var clicks = 1;
	$('.libanner3').click(function() {
		if(clicks%2 != 0){
		  $(this).css('opacity', '0.3');
		  $('.c쁠쁠').hide();
		} else{
		  $(this).css('opacity', '1');
		  $('.c쁠쁠').show();
		}
		++clicks;
	});
});

$(function(){
	var clicks = 1;
	$('.libanner4').click(function() {
		if(clicks%2 != 0){
		  $(this).css('opacity', '0.3');
		  $('.Python').hide();
		} else{
		  $(this).css('opacity', '1');
		  $('.Python').show();
		}
		++clicks;
	});
});

$(function(){
	var clicks = 1;
	$('.libanner5').click(function() {
		if(clicks%2 != 0){
		  $(this).css('opacity', '0.3');
		  $('.Spring').hide();
		 
		} else{
		  $(this).css('opacity', '1');
		  $('.Spring').show();
		}
		++clicks;
	});
});

$(function(){
	var clicks = 1;
	$('.libanner6').click(function() {
		if(clicks%2 != 0){
		  $(this).css('opacity', '0.3');
		  $('.c').hide();
		  
		 
		} else{
		  $(this).css('opacity', '1');
		  $('.c').show();
		}
		++clicks;
	});
});



$(function(){
	
	$('.bannerright1').click(function() {
		
		  $(this).css('opacity', '1');
		  $('.bannerright2').css('opacity', '0.3');
		  $('.bannerleft1').css('opacity', '0.3');
		  $('.bannerleft2').css('opacity', '0.3');
		  $('.ultest2').hide();
		  $('.ultest3').hide();
		  $('.ultest4').hide();
		  $('.ultest').show();
		
	});
});

$(function(){
	
	$('.bannerright2').click(function() {
		
		  $(this).css('opacity', '1');
		  $('.bannerright1').css('opacity', '0.3');
		  $('.bannerleft1').css('opacity', '0.3');
		  $('.bannerleft2').css('opacity', '0.3');
		  $('.ultest').hide();
		  $('.ultest3').hide();
		  $('.ultest4').hide();
		  $('.ultest2').show();
		  
		 
		
		
	});
});

$(function(){
	
	$('.bannerleft1').click(function() {
		
		  $(this).css('opacity', '1');
		  $('.bannerleft2').css('opacity', '0.3');
		  $('.bannerright1').css('opacity', '0.3');
		  $('.bannerright2').css('opacity', '0.3');
		  $('.ultest').hide();
		  $('.ultest2').hide();
		  $('.ultest4').hide();
		  $('.ultest3').show();
		  
		 
		
		
	});
});

$(function(){
	
	$('.bannerleft2').click(function() {
		
		  $(this).css('opacity', '1');
		  $('.bannerleft1').css('opacity', '0.3');
		  $('.bannerright1').css('opacity', '0.3');
		  $('.bannerright2').css('opacity', '0.3');
		  $('.ultest').hide();
		  $('.ultest2').hide();
		  $('.ultest3').hide();
		  $('.ultest4').show();
		  
		 
		
		
	});
});





</script>
<section id="board-container">
<div class="title">
<br />
<br />
	<p id="title2">스터디와 사이드 프로젝트를 찾는 가장 쉬운 방법</p>
	<p id="title">Kola <span>!</span></p>
	</div>
<!-- 글쓰기 버튼 -->
<br />
<% if(loginMember == null){ %>	
	<input type="button" value="글쓰기" id="writing-btn" class="btn btn-dark"onclick="javascript:noLogin_writing_btn()"/> <!-- 로그인 안하고 글쓰기 누를시 -->
<%} else { %>
	<% if(loginMember.getStudy_group() < 1){ %>	
		<input type="button" value="글쓰기" id="writing-btn" class="btn btn-dark" onclick="javascript:nogroup_writing_btn()"/>
		<% } else {%>
		<input type="button" value="글쓰기" id="writing-btn" class="btn btn-dark" onclick="location.href='<%= request.getContextPath() %>/board/boardForm'"/>
					
	<% } %>	
<% } %>		


<div id="banner">
	<ul class="ulbanner">
	<li class="libannerall">
		<li class="libanner1" style="margin: 1.5rem; transition: all 0.25s;"><img alt="첨부파일" src="<%=request.getContextPath() %>/images/javascript.png" width=100rem; height=100rem;></li>
		<li class="libanner2" style="margin: 1.5rem; transition: all 0.25s;"><img alt="첨부파일" src="<%=request.getContextPath() %>/images/java.png" width=100rem; height=100rem;></li>
		<li class="libanner3" style="margin: 1.5rem; transition: all 0.25s;"><img alt="첨부파일" src="<%=request.getContextPath() %>/images/c++.png" width=100rem; height=100rem;></li>
		<li class="libanner4" style="margin: 1.5rem; transition: all 0.25s;"><img alt="첨부파일" src="<%=request.getContextPath() %>/images/python.png" width=100rem; height=100rem;></li>
		<li class="libanner5" style="margin: 1.5rem; transition: all 0.25s;"><img alt="첨부파일" src="<%=request.getContextPath() %>/images/spring.png" width=100rem; height=100rem;></li>
		<li class="libanner6" style="margin: 1.5rem; transition: all 0.25s;"><img alt="첨부파일" src="<%=request.getContextPath() %>/images/c.png" width=110rem; height=110rem;></li>
	</li>
	</ul>
</div>

<div class="banner2">
	<section class="secbanner">
							<div class="bannerright">
								<div class="bannerright1">
									&#128467;
									<p style= "font-family: 'InfinitySans-RegularA1';">최신순</p>
								</div>
								<div class="bannerright2">	
									&#128293;
									<p style= "font-family: 'InfinitySans-RegularA1';">인기순</p>
								</div>
							</div>
							
	</section>
	<div class="bannerleft">
							<div class="bannerleft1">
							&#127962;
							<p style= "font-family: 'InfinitySans-RegularA1';">온라인</p>	
							</div>
							<div class="bannerleft2">	
							&#128106;
							<p style= "font-family: 'InfinitySans-RegularA1';">오프라인</p>
							</div>
	</div>
</div>

<div id="tbl-board">
		<ul class="ultest">
<% 
	List<Frontboard> list = (List<Frontboard>) request.getAttribute("list"); 
	for(Frontboard frontboard : list){
%>   


			<li id="<%= frontboard.getArea() %>" class="<%= frontboard.getLanguage() %>" onclick="location.href='<%= request.getContextPath() %>/board/frontboardView?no=<%= frontboard.getNo() %>';" 
			style="width: 15rem; height: 15rem; margin:2rem; padding: 1.5rem; background: white; box-shadow:0px 5px 25px rgb(0 0 0 / 15%); border-radius:1.5rem; position: relative; cursor: pointer;
					transition: all 0.5s;">
			<h5 style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getTitle() %></h5>
				<ul class="ultest1">
					
					<% 
						if(frontboard.getLanguage().equals("javaScript"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/javascript.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">javascript</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("java"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/java.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">java</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("Python"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/python.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">python</p>
					
							
							<% 
						}
					%> 
					<% 
						if(frontboard.getLanguage().equals("c쁠쁠"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/c++.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">c++</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("Spring"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/spring.png" width=50rem; height=50rem;>
							<p>spring</p>
							
						
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("c"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/nodejs.png" width=50rem; height=50rem;>
							<p>node.js</p>
					
							
							<% 
						}
					%>    
					
							<section class="toptext">
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getNow_member() %>명&nbsp;/&nbsp;</p>
							<p style= "font-family: 'InfinitySans-RegularA1';"> <%= frontboard.getMax_member() %>명</p>
							</section>
							<section class="undertext">
							&#128064;
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getReadCount() %></p>	
							&#128172;
								<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getComment_count() %></p>
							
							</section>
							</li>
					
					
					
				
				
				</ul>
		
			</li>
	
		
<%} %>
</ul>

<ul class="ultest2" style="display:none;">
<% 
	List<Frontboard> watchlist = (List<Frontboard>) request.getAttribute("watchlist"); 
	for(Frontboard frontboard : watchlist){
%>   


			<li id="<%= frontboard.getArea() %>" class="<%= frontboard.getLanguage() %>" onclick="location.href='<%= request.getContextPath() %>/board/frontboardView?no=<%= frontboard.getNo() %>';" 
			style="width: 15rem; height: 15rem; margin:2rem; padding: 1.5rem; background: white; box-shadow:0px 5px 25px rgb(0 0 0 / 15%); border-radius:1.5rem; position: relative; cursor: pointer;
					transition: all 0.5s;">
			<h5 style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getTitle() %></h5>
				<ul class="ultest1">
					
					<% 
						if(frontboard.getLanguage().equals("javaScript"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/javascript.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">javascript</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("java"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/java.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">java</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("Python"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/python.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">python</p>
					
							
							<% 
						}
					%> 
					<% 
						if(frontboard.getLanguage().equals("c쁠쁠"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/c++.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">c++</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("Spring"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/spring.png" width=50rem; height=50rem;>
							<p>spring</p>
							
						
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("c"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/nodejs.png" width=50rem; height=50rem;>
							<p>node.js</p>
					
							
							<% 
						}
					%>    
					
							<section class="toptext">
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getNow_member() %>명&nbsp;/&nbsp;</p>
							<p style= "font-family: 'InfinitySans-RegularA1';"> <%= frontboard.getMax_member() %>명</p>
							</section>
							<section class="undertext">
							&#128064;
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getReadCount() %></p>	
							&#128172;
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getComment_count() %></p>
							
							</section>
							</li>
					
					
					
				
				
				</ul>
		
			</li>
	
		
<%} %>
</ul>

<ul class="ultest3" style="display:none;">
<% 
	List<Frontboard> listonline = (List<Frontboard>) request.getAttribute("listonline"); 
	for(Frontboard frontboard : listonline){
%>   


			<li id="<%= frontboard.getArea() %>" class="<%= frontboard.getLanguage() %>" onclick="location.href='<%= request.getContextPath() %>/board/frontboardView?no=<%= frontboard.getNo() %>';" 
			style="width: 15rem; height: 15rem; margin:2rem; padding: 1.5rem; background: white; box-shadow:0px 5px 25px rgb(0 0 0 / 15%); border-radius:1.5rem; position: relative; cursor: pointer;
					transition: all 0.5s;">
			<h5 style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getTitle() %></h5>
				<ul class="ultest1">
					
					<% 
						if(frontboard.getLanguage().equals("javaScript"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/javascript.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">javascript</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("java"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/java.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">java</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("Python"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/python.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">python</p>
					
							
							<% 
						}
					%> 
					<% 
						if(frontboard.getLanguage().equals("c쁠쁠"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/c++.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">c++</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("Spring"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/spring.png" width=50rem; height=50rem;>
							<p>spring</p>
							
						
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("c"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/nodejs.png" width=50rem; height=50rem;>
							<p>node.js</p>
					
							
							<% 
						}
					%>    
					
							<section class="toptext">
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getNow_member() %>명&nbsp;/&nbsp;</p>
							<p style= "font-family: 'InfinitySans-RegularA1';"> <%= frontboard.getMax_member() %>명</p>
							</section>
							<section class="undertext">
							&#128064;
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getReadCount() %></p>	
							&#128172;
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getComment_count() %></p>
						
							</section>
							</li>
					
					
					
				
				
				</ul>
		
			</li>
	
		
<%} %>
</ul>

<ul class="ultest4" style="display:none;">
<% 
	List<Frontboard> listoffline = (List<Frontboard>) request.getAttribute("listoffline"); 
	for(Frontboard frontboard : listoffline){
%>   


			<li id="<%= frontboard.getArea() %>" class="<%= frontboard.getLanguage() %>" onclick="location.href='<%= request.getContextPath() %>/board/frontboardView?no=<%= frontboard.getNo() %>';" 
			style="width: 15rem; height: 15rem; margin:2rem; padding: 1.5rem; background: white; box-shadow:0px 5px 25px rgb(0 0 0 / 15%); border-radius:1.5rem; position: relative; cursor: pointer;
					transition: all 0.5s;">
			<h5 style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getTitle() %></h5>
				<ul class="ultest1">
					
					<% 
						if(frontboard.getLanguage().equals("javaScript"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/javascript.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">javascript</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("java"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/java.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">java</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("Python"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/python.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">python</p>
					
							
							<% 
						}
					%> 
					<% 
						if(frontboard.getLanguage().equals("c쁠쁠"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/c++.png" width=50rem; height=50rem;>
							<p style= "font-family: 'InfinitySans-RegularA1';">c++</p>
						
							
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("Spring"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/spring.png" width=50rem; height=50rem;>
							<p>spring</p>
							
						
							<% 
						}
					%>
					<% 
						if(frontboard.getLanguage().equals("nodejs"))
						{
							%>  
							<li class="litest1">
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/nodejs.png" width=50rem; height=50rem;>
							<p>node.js</p>
					
							
							<% 
						}
					%>    
					
							<section class="toptext">
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getNow_member() %>명&nbsp;/&nbsp;</p>	
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getMax_member() %>명</p>
							</section>
							<section class="undertext">
							&#128064;
							<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getReadCount() %></p>	
							&#128172;
								<p style= "font-family: 'InfinitySans-RegularA1';"><%= frontboard.getComment_count() %></p>
						
							</section>
							</li>
					
					
					
				
				
				</ul>
		
			</li>
	
		
<%} %>
</ul>
		
	</div>
</section>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>