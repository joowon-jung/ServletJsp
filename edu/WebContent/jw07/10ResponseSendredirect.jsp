<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<html>
<body>
	::  10ResponseSendredirect.jsp ���� <br/>
	
	<% request.setAttribute("aaa", new String("�����")); %>
	
	<!--  response.sendRedirect() �� URL, URI, Relative URI ��� ���� -->
	<% response.sendRedirect("09JSPActionForward.jsp"); %>
	<% // response.sendRedirect("http://127.0.0.1:8080/edu/jw07/09JSPActionForward.jsp"); %>
	<% // response.sendRedirect("http://www.daum.net"); %>
	
	::  10ResponseSendredirect.jsp �� <br/>
</body>
</html>