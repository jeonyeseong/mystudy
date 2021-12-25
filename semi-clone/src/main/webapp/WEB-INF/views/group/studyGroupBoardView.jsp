<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard.css" />
<%@page import="com.kh.studygroup.board.model.vo.StudyGroupBoard"%>
<%@page import="com.kh.studygroup.board.model.vo.Attachment"%> 
<%@ page import = "java.util.List" %>

<%
	StudyGroupBoard board = (StudyGroupBoard) request.getAttribute("board");
%>
<section id="board-container">
	<h4><a href="<%= request.getContextPath() %>/studygroup/boardlist">그룹게시판</a></h4>
	<table id="tbl-board-view">
		<tr>
			<td><h2><%= board.getTitle() %></h2></td>
		</tr>
		<tr style="border-top:1px solid #e9ecef; border-bottom:1px solid #e9ecef;">
		<td><%= board.getWriter() %> &nbsp; &nbsp;<%= board.getReg_Date() %><td style="text-align=rignt;">조회 <%= board.getRead_count() %></td>
		</tr>
		
<%
	List<Attachment> attachments = board.getAttachments();
	if(attachments != null && !attachments.isEmpty()){
		for(int i = 0; i<attachments.size(); i++ ){
			Attachment attach = attachments.get(i);
	
%>
			<tr>
			<td style="padding-top:3px">
				<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/cola.png" width=16px>
				<a href="<%= request.getContextPath() %>/studygroup/fileDownload?no=<%= attach.getNo() %>"><%= attach.getOriginal_filename() %></a>
			</td>
			</tr>
			<%
			}
		}
			%>
		
		<tr style="border-top:1px solid #e9ecef;" >
			<td style="padding:15px 0 25px 15px;">
			<%= board.getContent() %> 
		</tr>
		<tr>
			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
			<th colspan="2">
				<input type="button" value="수정하기" onclick="updateBoard()">
				<input type="button" value="삭제하기" onclick="deleteBoard()">
			</th>
		</tr>
	</table>
</section>

<form 
	name = "GroupBoardDelFrm"
	method = "POST"
	action="<%= request.getContextPath() %>/studygroup/boardDel">
	
	<input type="hidden" name = "no" value ="<%= board.getBoardNo() %>"/>
	

</form>
<script>
	const deleteBoard = () => {
		if(confirm("이 게시물을 정말 삭제하시겠습니까?")){
			$(document.GroupBoardDelFrm).submit();
		}
	};
	
	const updateBoard = () => {
		location.href = "<%= request.getContextPath() %>/studygroup/boardUpdate?no=<%= board.getBoardNo() %>";
	};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
