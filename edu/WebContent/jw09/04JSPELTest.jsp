<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 아래 내용은 Servlet Controller 가 화면 구성 위해 ObjectScope 에 저장 한 것으로 생각
--%>
<%
	// session ObjectScope 저장
	session.setAttribute("client", new jw09.Client());
%>

<!-- EL 이용 화면 구성 -->
<hr/>
<h3>EL 이용 session 저장 Bean(Data) 화면 구성</h3>

name : ${ sessionScope.client.name }<br/>
addr : ${ client.addr }<br/>
age : ${ client.age }<br/>

info 배열은 empty : ${ empty client.info }<br/>

info 배열의 index 0 value : ${ sessionScope.client.info[0] }<br/>
info 배열의 index 1 value : ${ client.info[1] }<br/>

<!-- EL 의 특징 : ObjectScope, Bean, Collection 접근 용이 -->