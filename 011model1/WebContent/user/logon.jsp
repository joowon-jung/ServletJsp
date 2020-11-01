<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "spring.model1.service.user.vo.UserVO" %>

<!-- 
	1. 로그인 유무 확인 :: Work Flow Control (방어적 코딩)
	    - 비 로그인 : 로그인 화면 display
	    - 로그인    : 이미 로그인 한 회원임을 display
	2. 로그인 확인은..
		로그인 한 회원은 session ObjectScope에 UserVO 객체를 갖고, active는 true
		UserVO 객체의 유무 및 UserVO의 active 값 true / false 판단
 -->
<%
	UserVO userVO = (UserVO)session.getAttribute("userVO");
	// 처음 로그인 하는 거면 null값이 들어오게 될 것 
%>
    
<html>
<head></head>
<body>
	<% if (userVO == null || userVO.isActive() != true ) { %>
		
		<form id = "login" method = "post" action = "/011model1/user/logonAction.jsp">
			
			아이디 :	 <input id = "userId" type = "text" name = "userId" value = "" ><br/><br/>
			패스워드 :  <input id = "userId" type = "text" name = "userPasswd" value = "" ><br/><br/>
			<input id = "submit" type = "submit" name = "submit" value = "Enter" />
		
		</form>
	
	<% } else { %>
		
		<%= userVO.getUserId() %> 님은 이미 로그인 하셨습니다.
		
	<% } %>

</body>
</html>