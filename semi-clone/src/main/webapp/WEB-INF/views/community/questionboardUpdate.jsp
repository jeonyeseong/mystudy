<%@page import="com.kh.community.model.vo.Attachment"%>
<%@page import="com.kh.community.model.vo.Questionboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Questionboard questionboard = (Questionboard) request.getAttribute("questionboard");
%>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard.css" />
<section id="board-container">
<h2>글 수정</h2>
<form
	name="boardUpdateFrm"
	action="<%=request.getContextPath() %>/community/questionboardUpdate" 
	method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="no" value="<%= questionboard.getNo() %>" />
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" value="<%= questionboard.getTitle() %>" required style="border-color:#e9ecef; width:100%; height:35px;"></td>
	</tr>
	<tr style="border-top:1px solid #e9ecef; border-bottom:1px solid #e9ecef;">
		<th>작성자</th>
		<td>
			<input type="text" name="writer" value="<%= questionboard.getWriter() %>" readonly style="border:0;"/>
		</td>
	</tr>
<%
	List<Attachment> attachments = questionboard.getAttachments();
	if(attachments != null && !attachments.isEmpty()){ 
%>
	<tr>
		<th>기존 첨부파일</th>
		<td>
<%
		for(Attachment attach : attachments){
%>			
			<div style="padding:0; margin:3px;">
				<img src="<%= request.getContextPath() %>/images/attachment-g5d16ffa11_640.png" width="16px" height="13px"/>
				<%= attach.getOriginalFilename() %>
				<label for="delFile<%= attach.getNo() %>">제거</label>
				<input 
					type="checkbox" 
					name="delFile" 
					id="delFile<%= attach.getNo() %>" 
					value="<%= attach.getNo() %>" />
			</div>

<%
		}
%>
		</td>
	</tr>
<% 
	} 
%>
	<tr style=" border-bottom:1px solid #e9ecef;">
		<th>첨부파일 추가</th>
		<td>			
			<input type="file" name="upFile1">
			<br>
			<input type="file" name="upFile2">
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td style="padding:15px 0 25px 15px;">
		<textarea rows="5" cols="40" name="content" style="border-color:#e9ecef; width:100%; height:160px;"><%= questionboard.getContent() %></textarea>
		</td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="수정하기">
		</th>
	</tr>
</table>
</form>
</section>
<script>
/**
* boardUpdateFrm 유효성 검사
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

$(document.boardUpdateFrm).submit(boardValidate);

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
