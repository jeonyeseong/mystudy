<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id=enroll-container>
	<h2>스터드 그룹 정보 입력</h2>
	<form name="groupCreateFrm" action="" method="POST">
		<table>
			<tr>
				<th>그룹이름<sup>*</sup></th>
				<td>
					<input type="text" placeholder="" name="groupName" id="groupName" value="" required>
				</td>
			</tr>
			<tr>
				<th>최대인원<sup>*</sup></th>
				<td>
					<input type="text" name="max_member" id="max_member" value="" placeholder="" required><br>
				</td>
			</tr> 
			
			<tr>
				<th>모집상태</th>
				<td>
					<input type="radio" name="status" id="status0" value="O" checked>
					<label for="gender0">모집</label>
					<input type="radio" name="status" id="status1" value="X">
					<label for="gender1">모집X</label>
				</td>
			</tr>
			<tr>
				<th>온/오프라인</th>
				<td>
					<input type="radio" name="on/off" id="offline" value="offline" checked>
					<label for="on/off0">오프라인 스터디</label>
					<input type="radio" name="on/off" id="online" value="online">
					<label for="on/off1">온라인 스터디</label>
				</td>
			</tr>
			<tr>
				<th>지역선택</th>
				<td>
			<div id="local-select">
				<select name="area" id="area">
					<option value="gyeonggido">경기도</option>
					<option value="daejeon">대전</option>
					<option value="busan">부산</option>
					<option value="seoul">서울</option>
				</select>
			</div>
				</td>
			</tr>
			<tr>
				<th>스터디 언어</th>
				<td>
					<input type="radio" name="language" id="c" value="c"><label for="c">c</label>
					<input type="radio" name="language" id="c++" value="c++"><label for="c++">c++</label>
					<input type="radio" name="language" id="java" value="java"><label for="java">java</label><br />
					<input type="radio" name="language" id="javaScript" value="javaScript"><label for="javaScript">javaScript</label>
					<input type="radio" name="language" id="Python" value="Python"><label for="Python">Python</label>
					<input type="radio" name="language" id="Spring" value="Spring"><label for="Spring">Spring</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="생성" >
		<input type="reset" value="취소">
		<input type="hidden" name = "memberId" value = "<%= loginMember.getMember_id()%>"/>
		<input type="hidden" name = "memberName" value = "<%= loginMember.getMember_name()%>"/>
	</form>

</section>
<script>
const val = $("[name=area]").val();
$("#online").click(function(){
	$("#local-select").hide();
	$("[name=area]").val("");
});
$("#offline").click(function(){
	$("#area").val(val);
	$("#local-select").show();
});


 
 
</script>

    
    
<%@ include file="/WEB-INF/views/common/footer.jsp" %>