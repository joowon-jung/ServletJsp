<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<body>
	::  08JSPActionForward.jsp 시작 <br/>
	
	<% request.setAttribute("aaa", new String("연결됨")); %>
	
	<jsp:forward page = "09JSPActionForward.jsp" />
	
	::  08JSPActionForward.jsp 끝 <br/>
</body>
</html>