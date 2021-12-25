<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/memberEnroll.css" />
<script>

$(() => {
	<% if(msg != null){ %>
	alert("<%= msg %>");
	
	<%} %>
});

</script>
<section id=enroll-container>
	<h2>회원 가입</h2>
	<form name="memberEnrollFrm" action="" method="POST">
		<table id="tbl-enroll-form">
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" placeholder="" name="memberId" id="_memberId" value="" required>
					<input type="button" value="아이디중복검사" onclick="checkIdDuplicate();"/>
					<input type="hidden" id="idValid" value="0" />
					<%-- #idValid의 값이 0이면 중복검사하지 않음. 1이면 중복검사 통과! --%>
				</td>
			</tr>
			<tr>
				<th>비밀번호<sup>*</sup></th>
				<td>
					<input type="password" name="password" id="_password" value="" placeholder="" required><br>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인<sup>*</sup></th>
				<td>	
					<input type="password" id="passwordCheck" value="" required><br>
				</td>
			</tr>  
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value="" required><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>
				<input type="text" name="email01" id="email01" style="width:100px"> @
				<select style="width:100px;margin-right:10px" name="selectEmail" id="selectEmail"> <option value="@gmail.com">gmail.com</option> <option value="@naver.com" selected>naver.com</option> <option value="@hanmail.net">hanmail.net</option> <option value="@hotmail.com">hotmail.com</option> <option value="@nate.com">nate.com</option> <option value="@yahoo.co.kr">yahoo.co.kr</option> <option value="@empas.com">empas.com</option> <option value="@dreamwiz.com">dreamwiz.com</option> <option value="@freechal.com">freechal.com</option> <option value="@lycos.co.kr">lycos.co.kr</option> <option value="@korea.com">korea.com</option> <option value="@hanmir.com">hanmir.com</option> <option value="@paran.com">paran.com</option> </select>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>	
					<input type="text" placeholder="" name="address" id="address"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M" checked>
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F">
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>관심 언어</th>
				<td>
					<input type="radio" name="language" id="c" value="c" checked><label for="c">c</label>
					<input type="radio" name="language" id="c++" value="c++"><label for="c++">c++</label>
					<input type="radio" name="language" id="java" value="java"><label for="java">java</label><br />
					<input type="radio" name="language" id="javaScript" value="javaScript"><label for="javaScript">javaScript</label>
					<input type="radio" name="language" id="Python" value="Python"><label for="Python">Python</label>
					<input type="radio" name="language" id="Spring" value="Spring"><label for="Spring">Spring</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" id="enroll-submit-button" value="가입" >
		<input type="reset" id="enroll-reset-button" value="취소">
	</form>
</section>
<form 
	name="checkIdDuplicateFrm" 
	action="<%= request.getContextPath() %>/member/checkIdDuplicate"
	method="GET">
	<input type="hidden" name="memberId" />
</form>
<script>
const checkIdDuplicate = () => {
	const name = "checkIdDuplicatePopup"; // 팝업페이지 window객체의 name속성
	const spec = "left=500px, top=500px, width=300px, height=250px";
	const popup = open("", name, spec); // url속성은 작성하지 않는다.
	
	const $memberId = $(_memberId);
	const $frm = $(document.checkIdDuplicateFrm);
	$frm.find("[name=memberId]").val($memberId.val());
	$frm.attr("target", name) // popup에 폼을 제출
		.submit();
};

/**
 * 중복검사이후 아이디를 변경한 경우, 다시 중복검사해야 한다.
 */
$(_memberId).change(() => {
	$(idValid).val(0);
});


/**
 * name=memberEnrollFrm 유효성검사
 * - id 영문자/숫자 5글자이상
 * - 비번 영문자/숫자 4~12글자
 * - 이름 한글 2글자 이상
 * - 전화번호 숫자확인
 */
 
$(document.memberEnrollFrm).submit((e) => {
	
	//memberId
	const $memberId = $(_memberId);
	//아이디는 영문자/숫자  4글자이상만 허용 
	if(!/^\w{4,}$/.test($memberId.val())){
		alert("아이디는 최소 4자리이상 입력해주세요.");
		return false;
	}
	
	//memberId 중복검사
	const $idValid = $(idValid);
	if($idValid.val() == 0){
		alert("아이디 중복 검사해주세요.");
		return false;
	}
	
	//password
	const $password = $(_password);
	const $passwordCheck = $(passwordCheck);
	
	if(!/^(?=.*?[a-z])(?=.*?[0-9]).{6,}$/.test($password.val())){
		alert("비밀번호는 6자 이상이어야 하며, 숫자/소문자를 모두 포함해야 합니다.");
		return false;
	}
	if($password.val() != $passwordCheck.val()){
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	}
	 
	
	
	//memberName
	const $memberName = $(memberName);
	if(!/^[가-힣]{2,}$/.test($memberName.val())){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		return false;
	}
	
	//phone
	const $phone = $(phone);
	if(!/^010[0-9]{8}$/.test($phone.val())){
		alert("유효한 전화번호가 아닙니다.");
		return false;
	}
	return true;
});
</script>
