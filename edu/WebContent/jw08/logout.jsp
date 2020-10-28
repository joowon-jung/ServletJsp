<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "jw.service.user.vo.UserVO" %>


<!-- 
	WorkFlow Control : 방어적 코딩 : 로그인 유무 확인
	로그인 회원은 session ObjectScope에 userVO 가 존재하며
	Field의 active 는 true
 -->
 
 <%
 	UserVO userVO = (UserVO)session.getAttribute("userVO");
 	if (userVO == null ) {
 		userVO = new UserVO();
 	}
 %>
<!-- 
	로그인 안 한 회원 : UserVO.active false
		1. 로그인 page include
		2. 회원가입 page로 이동 할 수 있는 link display
	로그인 한 회원 : UserVO.active true 라면
		1. 회원 id display
		2. 로그아웃 버튼 display
 -->
 
 <%  if (!userVO.isActive()) { %>
 			<!--  1. 로그인 page include... -->
 			<jsp:include page = "../jw07/login.html"/>
 			<br/><br/>
 			<!-- 2. 회원가입 page로 이동 할 수 있는 link -->
 			<a href = "/edu/jw07/addUser.html"> 회원가입... </a>
 <% } else { %>
 			<!--  1. 회원 id display -->
 			<%= userVO.getId() %> 님 로그인 하셨습니다.
 			<br/><br/>
 			<!-- 2. 로그아웃 버튼 display -->
 			<form method = "post" action = "<%= request.getRequestURL() %>" >
 				URL : <%= request.getRequestURL() %>
 				URI : <%= request.getRequestURI() %>
 				<input type = "submit" value = "Logout"/>
 			</form>
 			<br/><br/>
 <% } %>
 
<!-- Get / Post 방식 이용 : 1개의 page로 2개의 내용 display -->
<%
	if (request.getMethod().equals("POST")) {
		
		// LOGOUT의 3가지 방법
		
		// 1. SESISON 을 종료
		//session.invalidate();
		
		// 2. LOGIN 정보를 갖는 userVO session ObjectScope remove
		//session.removeAttribute("userVO");
		
		// 3. LOGIN 정보를 갖는 userVO Field acive false
		userVO.setActive(false);
		
		// LOGOUT 후 로그인 page로 sendRedirect
		response.sendRedirect("/edu/jw08/logout.jsp");
	}

%>