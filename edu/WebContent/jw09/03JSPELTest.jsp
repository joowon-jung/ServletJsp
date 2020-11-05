<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3> EL 내장객체를 사용 </h3>

1. pageContext EL 내장객체를 이용한 requestURI : ${ pageContext.request.requestURI } <br/>

2. pageContext EL 내장객체를 이용한 session 의 id : ${ pageContext.session.id } <br/>

3. Expression tag 를 사용 : <%= pageContext.getSession().getId() %><br/>

4. 이름 : <%= request.getParameter("name") %> <br/>

5. 주소 : ${ param.addr } <br/>

<% String[] sw = request.getParameterValues("sw"); %>

6. 선택한 소프트웨어 : <%= sw[0] %><br/>
6. 선택한 소프트웨어 : ${ paramValues.sw[1] }<br/>
6. 선택한 소프트웨어 : ${ paramValues.sw[2] }<br/><hr/>
<%--
	<%= sw[2] %> 인 경우
	Client가 선택한 것이 없다면 원래는 Exception 이 발생 해야 정상임! 
	하지만 화면땅 기술에서는 Null과 Exception 에 관대하므로 그냥 빈 값으로 나옴. 오류 발생 X
--%>

<h5> EL Cookie 내장객체 : Map 형식 </h5>
7. 쿠키에 저장된 JSESSIONID name : ${ cookie.JSESSIONID.name}<br/>
7. 쿠키에 저장된 JSESSIONID value : ${ cookie.JSESSIONID.value}