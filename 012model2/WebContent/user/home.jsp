<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "spring.model2.service.user.vo.UserVO" %>

<!--  ///////////////// �� �� �� �� �� /////////////// 
	Model2 Web Arch. ���� �� :: JSP�� View ����
	Work Flow Control�� Controller ���
	�Ʒ��� �ּ� : Controller ����ϴ� ControlServlet ���� ó��
	///////////////////////////////////////////// -->
 
 <% UserVO userVO = (UserVO)session.getAttribute("userVO"); %>

<!--  #. �� �α����� ȸ��  -->
<% // if (userVO == null || ! userVO.isActive())  { %>
		<%--  <jsp:forward page = "logon.jsp"/> --%>
<% //} %>

<!--  #. �α����� ȸ�� -->
<html>
<head></head>
<body>
	<p> Simple Model2 Examples </p>
	<p> ȯ���մϴ�. : <%= userVO.getUserId() %> �� </p>
</body>
</html>