<%@page import="com.kh.community.model.vo.Questionboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	List<Questionboard> list = (List<Questionboard>) request.getAttribute("list"); 
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/freeboard.css" />
<style>
div#search-container {margin:0 0 10px 0; padding:3px; }
div#search-writer {display: <%= "writer".equals(searchType) ? "inline-block" : "none" %>;}
div#search-title {display: <%= searchType == null || "title".equals(searchType) ? "inline-block" : "none" %>;}
div#search-content {display: <%= "content".equals(searchType) ? "inline-block" : "none" %>;}
}
</style>
<section id="board-container">
	<h2>Q&A 게시판 </h2>
<% if(loginMember != null){ %>	
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%= request.getContextPath() %>/community/questionboardForm'"/>
<% } %>	
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th><%--첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
		
<% 
	for(Questionboard questionboard : list){
%>
		<tr>
			<td><%= questionboard.getNo() %></td>
			<td>
				<a href="<%= request.getContextPath() %>/community/questionboardView?no=<%= questionboard.getNo() %>"><%= questionboard.getTitle() %></a>
				<%= questionboard.getCommentCount() > 0 ? "(" + questionboard.getCommentCount() + ")" : "" %>
			</td>
			<td><%= questionboard.getWriter() %></td>
			<td><%= questionboard.getRegDate() %></td>
			<td>
<% 	if(questionboard.getAttachCount() > 0){ %>
				<img alt="" src="<%= request.getContextPath() %>/images/attachment-g5d16ffa11_640.png" width="16px" height="14px">	
<%	}	%>
			</td>
			<td><%= questionboard.getReadCount() %></td>
			<td><%= questionboard.getLikeCount() %></td>
		</tr>
	
<%			
	}
%>		
	</table>
	<div id='pageBar'><%= request.getAttribute("pagebar") %>
		
	</div>
	
<div id="search-container">
        <select id="searchType">	
            <option value="title" <%= "title".equals(searchType) ? "selected" : "" %>>제목</option>
            <option value="content" <%= "content".equals(searchType) ? "selected" : "" %>>내용</option>
            <option value="writer" <%= "writer".equals(searchType) ? "selected" : "" %>>작성자</option>	
        </select>
        <div id="search-writer" class="search-type">
            <form action="<%=request.getContextPath()%>/community/questionboardFinder">
                <input type="hidden" name="searchType" value="writer"/>
                <input type="text" name="searchKeyword" value="<%= "writer".equals(searchType) ? searchKeyword : "" %>" size="25"/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-title" class="search-type">
            <form action="<%=request.getContextPath()%>/community/questionboardFinder">
                <input type="hidden" name="searchType" value="title"/>
                <input type="text" name="searchKeyword" value="<%= "title".equals(searchType) ? searchKeyword : "" %>" size="25"/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-content" class="search-type">
            <form action="<%=request.getContextPath()%>/community/questionboardFinder">
                <input type="hidden" name="searchType" value="content"/>
                <input type="text" name="searchKeyword" value="<%= "content".equals(searchType) ? searchKeyword : "" %>" size="25"/>
                <button type="submit">검색</button>			
            </form>	
        </div>
        </div>
		
	
</section>


<script>
/**
 * 검색 div 노출
 */
$(searchType).change((e) => {
	$(".search-type").hide();
	
	const v = $(e.target).val();
	$("#search-" + v).css("display", "inline-block");
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>