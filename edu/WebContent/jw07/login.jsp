<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "jw.service.user.vo.UserVO" %>
<%@ page import = "jw.service.user.dao.UserDao" %>

<!-- 방어적 코딩 / WorkFlow Control -->
<% if (request.getMethod().equals("GET")) { %> <!-- login.jsp 파일을 바로 실행하면 get 방식임! -->
	<jsp:forward page="login.html"/>
<% } %>

<%
	request.setCharacterEncoding("EUC_KR");

	//Client Form Data 처리
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");	
	
	// UserVO instance 생성 및 Client Form Data 전달 (Binding)
	UserVO userVO = new UserVO();
	userVO.setId(id);
	userVO.setPwd(pwd);
	
	// DB 접근 Data 검색 비교 UserVO.active true / false 변경
	UserDao userDAO = new UserDao();
	userDAO.getUser(userVO);
%>

<html>
<head></head>
<body>
<h2>Login 화면</h2>
<%  if (userVO.isActive()) {%>
<%= id %> 님 환영합니다.
<%
//Login 이 정상적으로 이루어진 경우
// session 에 로그인 정보로 userVO instance 저장
session.setAttribute("userVO", userVO); // 세션에 유저 정보 저장 
%>
<% } else { %>
Login 실패! id, pwd를 확인하세요.
<% } %>
<p><p><a href='login.html'>뒤로</a>
</body>
</html>