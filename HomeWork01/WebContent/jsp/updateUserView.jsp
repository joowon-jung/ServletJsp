<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jw.services.user.vo.UserVO" %>
    
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>SignUp</title>
</head>
<% session = request.getSession(true); %>
<body>
	<br>
	<form method="post" action="updateUser.jsp">
		<table border="2" height="400">
		
			<tr height="10">
				<td>* 아이디 - 수정 불가</td>
				<td><input type='text' name='id' value = "<%= ((UserVO) session.getAttribute("userVO")).getId() %>" readonly></td>
			</tr>

			<tr height="10">
				<td>* 이름(한글실명)</td>
				<td><input type='text' name='name' value = "<%=((UserVO) session.getAttribute("userVO")).getName() %>"></td>
			</tr>

			<tr>
				<td>* 성별</td>
				<td><input type="radio" name='sex' value='남' checked />남 
				<input type="radio" name='sex' value='여' />여</td>
			</tr>

			<tr>
				<td>* 생년월일</td>
				<td><input type="text" name='birth' size="5" value = "<%=((UserVO) session.getAttribute("userVO")).getBirth() %>"/>년</td>
			</tr>

			<tr>
				<td>* 최종학력</td>
				<td><select name="edu" size="1">
						<option value="">선택하세요.</option>
						<option value="고졸">고졸</option>
						<option value="대학 중퇴자">대학 중퇴</option>
						<option value="초대졸 ">초대졸</option>
						<option value="대졸">대졸</option>
				</select></td>
			</tr>

			<tr>
				<td>* 직업</td>
				<td><select name="job" size="1">
						<option value="">선택하세요.</option>
						<option value="개발자">개발자</option>
						<option value="디자이너">디자이너</option>
						<option value="마케터">마케터</option>
						<option value="기획자">기획자</option>
				</select></td>
			</tr>

			<tr>
				<td>* 연락처</td>
				<td>&nbsp; 휴대폰 <input type="text" name='pnum1' size="5" /> - <input
					type="text" name='pnum2' size="5" /> - <input type="text"
					name='pnum3' size="5" /> <br>
				<br>
				</td>
			</tr>

			<tr>
				<td>* 거주지 주소</td>
				<td><input type="text" name='address' size="50" value = "<%=((UserVO) session.getAttribute("userVO")).getAddress() %>"/></td>
			</tr>

		</table>
		<input type='submit' value='회원가입' />
	</form>
</body>
</html>