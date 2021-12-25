<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>  
<%@page import="com.kh.studygroup.model.vo.StudyGroupMember"%> 
<%@ page import = "java.util.List" %>

<%
	
	String arrId = (String) request.getAttribute("arrId");

%>

<script>
const host = location.host;
const ws = new WebSocket(`ws://\${host}<%= request.getContextPath() %>/chat/websocket`);

ws.onopen = (e) => {
	console.log("open",e);
};
ws.onmessage = (e) => {
	console.log("message",e);
	const {data} = e;
	const {type, msg, sender, time} = JSON.parse(data);
	console.log(type, msg, sender, time);
	
	let html = "";
	switch(type){
	case "welcome":
	case "bye": html = `<li class = "center"><span class = "badge">\${sender}</span>\${msg}</li>`; break;
	case "msg": html = `<li><span class = "badge">\${sender}</span>\${msg}</li>`; break;
	case "self" : html = `<li class = "selfli"><span class = "self">\${msg}</span></li>`; break;
	}
	$("#msg-container ul").append(html);
	
	// 스크롤 처리
	const $msgContainer = $("#msg-container");
	const scrollHegiht = $("#msg-container").prop("scrollHeight");
	$msgContainer.scrollTop(scrollHegiht);
	
};
ws.onerror = (e) => {
	console.log("error",e);
};
ws.onclose = (e) => {
	console.log("close",e);
};


$(() => {
	/**
	*	websocket message전송
	*/
	$(send).click((e) => {
		const o = {
				
				sender: "<%= loginMember.getMember_id() %>",
				receiver: "<%= arrId %>",
				msg : $(msg).val(),
				time: Date.now(),
				type : "msg"
		};
		
		$.ajax({
			url : "<%= request.getContextPath() %>/chat/groupchat",
			data : {
				msg : JSON.stringify(o),
			},
			success(data){
				console.log(data);
			},
			error : console.log
		});
		
		// 메세지 전송
		ws.send(JSON.stringify(o));
		
		// #msg 초기화
		$(msg).val('').focus();
		
		
	
		
		
	});
	
	$(msg).keyup((e) => {
		if(e.key == 'Enter')
			$(send).trigger("click");
	});
});

</script>


<link rel="stylesheet" href="<%= request.getContextPath() %>/css/chat.css" />
	<section id="chat-container">	
		<h2>스터디 채팅</h2>
		<div id="msg-container">
			<ul></ul>
		</div>
		
		<div id="msg-editor" class="editor">
			<textarea name="" id="msg" cols="30" rows="2"></textarea>
			<button type="button" class="btn btn-warning" id = "send">전송</button>
		</div>
		
		<hr style="margin:20px 0" />


	</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>  
