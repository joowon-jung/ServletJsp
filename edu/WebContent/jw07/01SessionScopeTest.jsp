<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// 내장객체 : request, session, out, application 등등
	// Scriplet, Expression Tag 에서 사용 가능
	
	// 1. sessoin Object Scope "count" 추출
	Integer count = (Integer)session.getAttribute("count");

	// 2. 없다면
	// ==> name : "count" value : new Integer(1)
	if (count == null) {
		out.println(" :: Browser 켜고 1번째 방문 ::");
		session.setAttribute("count", new Integer(1));
	}
	
	// 3. 있다면
	// ==> session ObjectScope 추출한 출력
	// ==> count 값을 증가 (+1) 다시 session ObjectScope 저장
	else {
		int changeCount = count.intValue() + 1;
		out.println(":: Browser 켜고 " + changeCount + " 번째 방문 ::");
		
		session.setAttribute("count", new Integer(changeCount));
	}

%>