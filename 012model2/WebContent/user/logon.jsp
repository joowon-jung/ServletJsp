<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<!--  ///////////////// �� �� �� �� �� /////////////// 
	Model2 Web Arch. ���� �� :: JSP�� View ����
	Work Flow Control�� Controller ���
	�Ʒ��� �ּ� : Controller ����ϴ� ControlServlet ���� ó��
	///////////////////////////////////////////// -->
<%
	//UserVO userVO = (UserVO)session.getAttribute("userVO");
%>
    
<html>
<head></head>
<body>
	<% //if (userVO == null || userVO.isActive() != true ) { %>
		
		<!--  /////////////////////////// ����� �κ� ///////////////////////// -->
		<!--  <form id = "login" method = "post" action = "/011model1/user/logonAction.jsp"> -->
		<!--  ////////////////////////////////////////////////////////////// -->
		<form id = "login" method = "post" action = "/012model2/user/logonAction.do">
		
			���̵� :	 <input id = "userId" type = "text" name = "userId" value = "" ><br/><br/>
			�н����� :  <input id = "userId" type = "text" name = "userPasswd" value = "" ><br/><br/>
			<input id = "submit" type = "submit" name = "submit" value = "Enter" />
		
		</form>
	
	<% //} else { %>
		
		<%-- <%= userVO.getUserId()%> ���� �̹� �α��� �ϼ̽��ϴ�. --%>
		
	<% //} %>

</body>
</html>