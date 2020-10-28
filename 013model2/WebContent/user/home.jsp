<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "spring.model2.service.user.vo.UserVO" %>

<!--  ///////////////// 변 경 된 부 분 /////////////// 
	Model2 Web Arch. 적용 시 :: JSP는 View 역할
	Work Flow Control은 Controller 담당
	아래의 주석 : Controller 담당하는 ControlServlet 에서 처리
	///////////////////////////////////////////// -->
 
 <% UserVO userVO = (UserVO)session.getAttribute("userVO"); %>

<!--  #. 미 로그인한 회원  -->
<% // if (userVO == null || ! userVO.isActive())  { %>
		<%--  <jsp:forward page = "logon.jsp"/> --%>
<% //} %>

<!--  #. 로그인한 회원 -->
<html>
<head></head>
<body>
	<p> Simple Model2 Examples </p>
	<p> 환영합니다. : <%= userVO.getUserId() %> 님 </p>
</body>
</html>