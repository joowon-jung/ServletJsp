<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "jw.service.user.vo.UserVO" %>


<!-- 
	WorkFlow Control : ����� �ڵ� : �α��� ���� Ȯ��
	�α��� ȸ���� session ObjectScope�� userVO �� �����ϸ�
	Field�� active �� true
 -->
 
 <%
 	UserVO userVO = (UserVO)session.getAttribute("userVO");
 	if (userVO == null ) {
 		userVO = new UserVO();
 	}
 %>
<!-- 
	�α��� �� �� ȸ�� : UserVO.active false
		1. �α��� page include
		2. ȸ������ page�� �̵� �� �� �ִ� link display
	�α��� �� ȸ�� : UserVO.active true ���
		1. ȸ�� id display
		2. �α׾ƿ� ��ư display
 -->
 
 <%  if (!userVO.isActive()) { %>
 			<!--  1. �α��� page include... -->
 			<jsp:include page = "../jw07/login.html"/>
 			<br/><br/>
 			<!-- 2. ȸ������ page�� �̵� �� �� �ִ� link -->
 			<a href = "/edu/jw07/addUser.html"> ȸ������... </a>
 <% } else { %>
 			<!--  1. ȸ�� id display -->
 			<%= userVO.getId() %> �� �α��� �ϼ̽��ϴ�.
 			<br/><br/>
 			<!-- 2. �α׾ƿ� ��ư display -->
 			<form method = "post" action = "<%= request.getRequestURL() %>" >
 				URL : <%= request.getRequestURL() %>
 				URI : <%= request.getRequestURI() %>
 				<input type = "submit" value = "Logout"/>
 			</form>
 			<br/><br/>
 <% } %>
 
<!-- Get / Post ��� �̿� : 1���� page�� 2���� ���� display -->
<%
	if (request.getMethod().equals("POST")) {
		
		// LOGOUT�� 3���� ���
		
		// 1. SESISON �� ����
		//session.invalidate();
		
		// 2. LOGIN ������ ���� userVO session ObjectScope remove
		//session.removeAttribute("userVO");
		
		// 3. LOGIN ������ ���� userVO Field acive false
		userVO.setActive(false);
		
		// LOGOUT �� �α��� page�� sendRedirect
		response.sendRedirect("/edu/jw08/logout.jsp");
	}

%>