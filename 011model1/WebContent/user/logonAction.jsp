<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ page import = "spring.model1.service.user.vo.UserVO" %>
<%@ page import = "spring.model1.service.user.dao.UserDao" %>

<!-- 
	1. �α��� ���� Ȯ�� :: Work Flow Control (����� �ڵ�)
	    - �� �α��� : �α��� ȭ�� display
	    - �α���    : �̹� �α��� �� ȸ������ display
	2. �α��� Ȯ����..
		�α��� �� ȸ���� session ObjectScope�� UserVO ��ü�� ����, active�� true
		UserVO ��ü�� ���� �� UserVO�� active �� true / false �Ǵ�
 -->
<%
	UserVO userVO = (UserVO)session.getAttribute("userVO");
	if (userVO == null) {
		userVO = new UserVO();
	}
%>

<!--  #. �α��� �� ȸ�� -->
<%	if (userVO.isActive()) { %>
	<jsp:forward page = "home.jsp"/>
<% } %>

<!--  #. �� �α��� �� ȸ�� -->
<%
	// �ѱ� ó��
	request.setCharacterEncoding("EUC_KR");

	//Client Form Data ó��
	String userId = request.getParameter("userId");
	String userPasswd = request.getParameter("userPasswd");	
	
	// Navigation ����Ʈ ������ ����
	String targetAction = "/user/logon.jsp";
	
	//Client Form Data Value Object Binding
	userVO.setUserId(userId);
	userVO.setUserPasswd(userPasswd);
	
	// DAO �̿� DB Ȯ��
	UserDao userDAO = new UserDao();
	userDAO.getUser(userVO);
	
	// �α��� ���� Ȯ��
	if ( userVO.isActive()) {
		session.setAttribute("userVO", userVO);
		targetAction = "/user/home.jsp";
	}
%>

<!-- Navigation -->
<jsp:forward page = '<%= targetAction %>'/>