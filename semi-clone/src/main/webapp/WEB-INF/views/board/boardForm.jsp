<%@page import="com.kh.studygroup.model.vo.StudyGroup"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>  
<%
	
	StudyGroup studyGroup = (StudyGroup) request.getAttribute("studyGroup");


%>

<script>
/**
* boardEnrollFrm 유효성 검사
*/
function boardValidate(){
	const $title = $("[name=title]");
	const $content = $("[name=content]");
	
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test($title.val())){
		alert("제목을 입력하세요.");
		return false;
	}
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	// \n는 .에 포함되지 않는다.
	if(!/^(.|\n)+$/.test($title.val())){
		alert("내용을 입력하세요.");
		return false;
	}
	
	return true;
}

//off-line 선택하면 지역 선택창 뜸
$(document).ready(function(){
	$('#area').change(function() {
		var state = $('#area option:selected').val();
		
		if( state =='Off-line'){
			$('#location').show();
		}else{
			$('#location').hide();
		}
	})	
})
	

$(document.boardEnrollFrm).submit(boardValidate);

</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<style>
#location {
	display: none;
}
table {
    margin-left:auto; 
    margin-right:auto;
}
body{
	text-align: center;
}
form{
	padding: 10px;
}

.input-group{
	margin: 20px;
	
}
.card-header{
	font-size: 2rem;
	font-weight: 400;
	margin-left: 0;
}
</style>
<section id="board_container">
<h2>게시판 작성</h2>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/board/boardEnroll" 
	method="post">
	<table id="tbl-board-view">
	<tr>
		<th ><label class="input-group-text">제 목</label></th>
		<td><input type="text" name="title" class="form-control" required></td>
	</tr>
	<tr>
		<th><label class="input-group-text">작성자</label></th>
		<td >
			<input type="text" name="writer" class="form-control"value="<%= loginMember.getMember_id() %> " readonly/>
			
		</td>
	</tr>
	<tr>
		<th colspan="2" >
		<div class="row justify-content-md-center">
				<div class="col-sm-12">
            <div class="card">
  			<div class="card-header "><%= studyGroup.getGroup_name() %></div>
 			 <div class="card-body">스터디 언어 : <%= studyGroup.getLanguage() %>
 			 <br />
 			 인원 현황 : <%= studyGroup.getNow_member() / studyGroup.getMax_member() %>
 			 <br />
 			 온/오프라인 : <%= studyGroup.getOn_off() %>
 			 <br />
 			 지역 : <%= studyGroup.getArea() %>
 			 <br />
 			 모집 상태 : <%= studyGroup.getStatus() %>
 			 </div>
			</div>
		</div>
		</div>
		</th>
		<td>		</td>
	</tr>
	<tr>
		<th colspan="2" ><textarea name="content" class="form-control" id="p_content"></textarea>
                  <script type="text/javascript">
                  CKEDITOR.replace('p_content'
                                                  , {height: 500                                             
                                                  });
                  </script></th>
                  
	</tr>
	<tr>
		<th colspan="2">
			<button type="submit" class="btn btn-outline-danger" style="width: 20%; font-weight: bold; margin-top: 30px;">
             등   록          
            </button>
		</th>
	</tr>
</table>
</form>


</section>
      
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
</section>
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>

