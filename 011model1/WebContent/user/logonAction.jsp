<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ page import = "spring.model1.service.user.vo.UserVO" %>
<%@ page import = "spring.model1.service.user.dao.UserDao" %>

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
	if (userVO == null) {
		userVO = new UserVO();
	}
%>

<!--  #. 로그인 한 회원 -->
<%	if (userVO.isActive()) { %>
	<jsp:forward page = "home.jsp"/>
<% } %>

<!--  #. 미 로그인 한 회원 -->
<%
	// 한글 처리
	request.setCharacterEncoding("EUC_KR");

	//Client Form Data 처리
	String userId = request.getParameter("userId");
	String userPasswd = request.getParameter("userPasswd");	
	
	// Navigation 디폴트 페이지 지정
	String targetAction = "/user/logon.jsp";
	
	//Client Form Data Value Object Binding
	userVO.setUserId(userId);
	userVO.setUserPasswd(userPasswd);
	
	// DAO 이용 DB 확인
	UserDao userDAO = new UserDao();
	userDAO.getUser(userVO);
	
	// 로그인 유무 확인
	if ( userVO.isActive()) {
		session.setAttribute("userVO", userVO);
		targetAction = "/user/home.jsp";
	}
%>

<!-- Navigation -->
<jsp:forward page = '<%= targetAction %>'/>