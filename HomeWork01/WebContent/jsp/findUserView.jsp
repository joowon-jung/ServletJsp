<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

 회원정보 검색입력화면 (검색할 id을 입력하세요.)<br/>

	<form method="post" action="findUser.jsp">
		
		<table border="1" width="80%">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td colspan="2"><center>
				<input type="submit" value=" 입력완료"/></center></td>
			</tr>
		</table>
		
	</form>	
	
</body>
</html>