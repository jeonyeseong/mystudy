<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/stopwatch.css" />
<br />
<h2 id = "stopWatchTitle">오늘의 공부 시간</h2>
<form 
	name = "stopWatchFrm"
	method = "POST"
	action="<%= request.getContextPath() %>/studygroup/stopwatch">

	<div id='box' class="box">
		<div id='timerBox' class="timerBox">
			<div id="time" class="time">00:00:00</div>
		</div>
		<br />
		<div class="btnBox">
			<button type="button" id="startbtn" class="btn btn-secondary"><svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-play" viewBox="0 0 16 16" id = "play">
  <path d="M10.804 8 5 4.633v6.734L10.804 8zm.792-.696a.802.802 0 0 1 0 1.392l-6.363 3.692C4.713 12.69 4 12.345 4 11.692V4.308c0-.653.713-.998 1.233-.696l6.363 3.692z"/></svg></button>
			
			<button type="button" id="pausebtn" class="btn btn-secondary"><svg xmlns="http://www.w3.org/2000/svg"  fill="currentColor" class="bi bi-pause" viewBox="0 0 16 16" id = "pause">
  	<path d="M6 3.5a.5.5 0 0 1 .5.5v8a.5.5 0 0 1-1 0V4a.5.5 0 0 1 .5-.5zm4 0a.5.5 0 0 1 .5.5v8a.5.5 0 0 1-1 0V4a.5.5 0 0 1 .5-.5z"/></svg></button>
			
			
			<button type="button" id="stopbtn" class="btn btn-secondary"><svg xmlns="http://www.w3.org/2000/svg"  fill="currentColor" class="bi bi-stop" viewBox="0 0 16 16" id = "stop">
  <path d="M3.5 5A1.5 1.5 0 0 1 5 3.5h6A1.5 1.5 0 0 1 12.5 5v6a1.5 1.5 0 0 1-1.5 1.5H5A1.5 1.5 0 0 1 3.5 11V5zM5 4.5a.5.5 0 0 0-.5.5v6a.5.5 0 0 0 .5.5h6a.5.5 0 0 0 .5-.5V5a.5.5 0 0 0-.5-.5H5z"/></svg></button>


		</div>
	</div>

</form>

	
<script>
var time = 0;
var starFlag = true;
$(document).ready(function(){
  buttonEvt();
});

function init(){
  document.getElementById("time").innerHTML = "00:00:00";
}

function buttonEvt(){
  var hour = 0;
  var min = 0;
  var sec = 0;
  var timer;
  var stringTime;
  // start btn
  $("#startbtn").click(function(){

    if(starFlag){
      $(".fa").css("color","#FAED7D")
      this.style.color = "#4C4C4C";
      starFlag = false;

      if(time == 0){
        init();
      }
      timer = setInterval(function(){
        time++;

        min = Math.floor(time/60);
        hour = Math.floor(min/60);
        sec = time%60;
        min = min%60;

        var th = hour;
        var tm = min;
        var ts = sec;
        if(th<10){
        th = "0" + hour;
        }
        if(tm < 10){
        tm = "0" + min;
        }
        if(ts < 10){
        ts = "0" + sec;
        
        }

        stringTime = th + ":" + tm + ":" + ts;
        document.getElementById("time").innerHTML = stringTime;
      }, 1000);
    }
  });

  // pause btn
  $("#pausebtn").click(function(){
    if(time != 0){
      $(".fa").css("color","#FAED7D")
      this.style.color = "#4C4C4C";
      clearInterval(timer);	// 반복 중단
      starFlag = true;
      
    }
  });

  // stop btn
  $("#stopbtn").click(function(){
    if(time != 0){
      $(".fa").css("color","#FAED7D")
      this.style.color = "#4C4C4C";
      clearInterval(timer);	// 반복 중단
      starFlag = true;
      time = 0;				// 시간 초기화
      init();				// time 클래스에 초기화된 텍스트 삽입 함수
      const inputTime = `<input type="hidden" name="stringTime" value="\${stringTime}" />`; 
      const inputId = `<input type="hidden" name="memberId" value="<%= loginMember.getMember_id() %>" />`; 
      $(document.stopWatchFrm)
      		.append(inputTime)
      		.append(inputId)
      		.submit();
      
    }
  });
}
</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>