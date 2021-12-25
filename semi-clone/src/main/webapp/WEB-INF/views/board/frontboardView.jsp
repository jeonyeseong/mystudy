<%@page import="com.kh.board.model.vo.Frontboard"%>
<%@page import="com.kh.board.model.vo.FrontboardComment"%>
<%@page import="com.kh.studygroup.model.vo.StudyGroup"%>
<%@page import="com.kh.member.model.vo.Member" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Frontboard frontboard = (Frontboard) request.getAttribute("frontboard");

%>
<style>
.btn-outline-primary {
    color: #0d6efd;
    border-color: #0d6efd;
    margin: 1rem;
    margin-left: 42.3rem;
}

#redbutton1{
	margin: 1rem;
	margin-left: 41rem;

}

#redbutton2{
	margin: 1rem;


}
.comment-editor{
	margin-left: 20rem;
}


</style>
<link rel="stylesheet" href="bootstrap.css" />
<section id="board-container">
	<h2 class="text-primary text-center">게시판</h2>
	<table id="tbl-board-view" class="table table-striped">
		<tr class="table-warning">
			<th>글번호</th>
			<td><%= frontboard.getNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= frontboard.getTitle() %></td>
		</tr>
		<tr class="table-warning">
			<th>작성자</th>
			<td><%= frontboard.getWriter() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= frontboard.getReadCount() %></td>
		</tr>
		
		<tr class="table-warning">
			<th>내 용</th>
			<td><div id="content2"></div>
				<script>
				var text ="<%= frontboard.getContent() %>"; 
				text = text.replaceAll("&lt;", "<");
				text = text.replaceAll("&gt;", ">");
				text = text.replaceAll("&amp;lt;", "<");
				text = text.replaceAll("&amp;gt;", ">");
				text = text.replaceAll("amp;nbsp;", " ");
				text = text.replaceAll("&amp;amp;", "&");
				document.getElementById('content2').innerHTML=text;
				</script>
			</td>
		</tr>
		<tr>
			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
			<th  colspan="2">
				<input id="redbutton1" class="btn btn-outline-danger btn-xs" type="button" value="수정하기" onclick="updateBoard()">
				<input id="redbutton2" class="btn btn-outline-danger btn-xs" type="button" value="삭제하기" onclick="deleteBoard()">
			</th>
		</tr>
	</table>
	
	<br />
	<input type="button" class="btn btn-outline-primary btn-xs" value="스터디그룹 참가신청" onclick="groupApply()"/>


	
	</section>
	<section>
	<div class="comment-container">
        <div class="comment-editor">
            <form 
            	action="<%=request.getContextPath()%>/board/frontboardCommentEnroll" 
            	method="post" 
            	name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%= frontboard.getNo() %>" />
                <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMember_id() : "" %>" />
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
				<textarea name="content" cols="60" rows="3"></textarea>
                <button type="submit" id="btn-comment-enroll1">등록</button>
            </form>
            
            <!--table#tbl-comment-->
<% 
	List<FrontboardComment> commentList = (List<FrontboardComment>) request.getAttribute("commentList"); 
	if(commentList != null && !commentList.isEmpty()){
%>
		<table id="tbl-comment">
<%
		for(FrontboardComment bc : commentList){
			boolean removable = 
					loginMember != null && 
					(
					  loginMember.getMember_id().equals(bc.getWriter())
					  || MemberService.ADMIN_ROLE.equals(loginMember.getMember_role())
					);

			if(bc.getCommentLevel() == 1){
%>
			<tr class="level1">
				<td>
					<sub class="comment-writer"><%= bc.getWriter() %></sub>
					<sub class="comment-date"><%= bc.getRegDate() %></sub>
					<br />
					<%-- 댓글내용 --%>
					<%= bc.getContent() %>
					<br />
					<button class="btn-reply" value="<%= bc.getNo() %>">답글</button>
<% if(removable){ %>
					<button class="btn-delete" value="<%= bc.getNo() %>">삭제</button>
<% } %>
				</td>
			
			</tr>
<%
			} else {
%>
			<tr class="level2">
				<td>
					<sub class="comment-writer"><%= bc.getWriter() %></sub>
					<sub class="comment-date"><%= bc.getRegDate() %></sub>
					<br />
					<%-- 대댓글내용 --%>
					<%= bc.getContent() %>
					<br />
<% if(removable){ %>
					<button class="btn-delete" value="<%= bc.getNo() %>">삭제</button>
<% } %>
				</td>
		
			</tr>
<%
			}
		}
%>
		</table>
<%
	}
%>
		
	</div>
</section>
<form
	name="groupApplyFrm"
	method="POST" 
	action="<%= request.getContextPath() %>/group/groupApply" >
	<input type="hidden" name="writer" value="<%= frontboard.getWriter()  %>" />
</form>	
<form
	name="boardDelFrm"
	method="POST" 
	action="<%= request.getContextPath() %>/board/frontboardDelete" >
	<input type="hidden" name="no" value="<%= frontboard.getNo() %>" />
</form>	
<form 
	action="<%= request.getContextPath() %>/board/frontboardCommentDelete" 
	name="boardCommentDelFrm"
	method="POST">
	<input type="hidden" name="no" />
	<input type="hidden" name="boardNo" value="<%= frontboard.getNo() %>"/>
</form>

<script>
$(".btn-delete").click(function(){
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.boardCommentDelFrm);
		var no = $(this).val();
		$frm.find("[name=no]").val(no);
		$frm.submit();
	}
});	

/**
 * 대댓글 입력
 * 이벤트발생객체 e.target -> button.btn-reply
 */
$(".btn-reply").click((e) => {
<% if(loginMember == null){ %>
	loginAlert();
	return;
<% } %>

	const commentRef = $(e.target).val();
	console.log(commentRef);
	
	const tr = `<tr>
	<td colspan="2" style="text-align:left">
		<form 
			action="<%=request.getContextPath()%>/board/frontboardCommentEnroll" 
			method="post">
		    <input type="hidden" name="boardNo" value="<%= frontboard.getNo() %>" />
		    <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMember_id() : "" %>" />
		    <input type="hidden" name="commentLevel" value="2" />
		    <input type="hidden" name="commentRef" value="\${commentRef}" />    
			<textarea name="content" cols="60" rows="2"></textarea>
		    <button type="submit" class="btn-comment-enroll2">등록</button>
		</form>
	</td>
</tr>`;
	console.log(tr);
	
	// e.target인 버튼태그의 부모tr을 찾고, 다음 형제요소로 추가
	const $baseTr = $(e.target).parent().parent();
	const $tr = $(tr);
	
	$tr.insertAfter($baseTr)
		.find("form")
		.submit((e) => {
			const $content = $("[name=content]", e.target);
			if(!/^(.|\n)+$/.test($content.val())){
				alert("댓글을 작성해주세요.");
				e.preventDefault();
			}
		});
		
	
	// 클릭이벤트핸들러 제거!
	$(e.target).off("click");
	
	
	


});



$("[name=content]", document.boardCommentFrm).focus((e) => {

<% if(loginMember == null){ %>
	loginAlert();
	return;
<% } %>

});

$(document.boardCommentFrm).submit((e) => {
<% if(loginMember == null){ %>
	loginAlert();
//	e.preventDefault();
//	return;
	return false;
<% } %>

	const $content = $("[name=content]", e.target);
	if(!/^(.|\n)+$/.test($content.val())){
		alert("댓글을 작성해주세요.");
		e.preventDefault();
	}

});

const loginAlert = () => {
	alert("로그인후 사용가능합니다.");
	$(memberId).focus();
};

const NoAuthority = () => {
	alert("권한이 없습니다.");
	e.preventDefault();
}
const deleteBoard = () => {
	if(confirm("이 게시물을 정말 삭제하시겠습니까?")){
		$(document.boardDelFrm).submit();		
	}
};

const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/board/frontboardUpdate?no=<%= frontboard.getNo() %>";
};

const groupApply = () => {
	<% if(loginMember == null){ %>
		alert("로그인후 신청 가능합니다.");
		return;
	
	<% }else{ %>
		$(document.groupApplyFrm).submit();
	
	<% } %>
};

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>