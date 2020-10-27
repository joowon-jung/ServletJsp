<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	// ���尴ü : request, session, out, application ���
	// Scriplet, Expression Tag ���� ��� ����
	
	// 1. request Object Scope "count" ����
	Integer count = (Integer)request.getAttribute("count");

	// 2. ���ٸ�
	// ==> name : "count" value : new Integer(1)
	if (count == null) {
		out.println(" :: �ش� ������ 1��° ��� ::");
		request.setAttribute("count", new Integer(1));
	}
	
	// 3. �ִٸ�
	// ==> request ObjectScope ������ ���
	// ==> count ���� ���� (+1) �ٽ� request ObjectScope ����
	else {
		int changeCount = count.intValue() + 1;
		out.println(":: "+ changeCount + " ��° request ::");
		
		request.setAttribute("count", new Integer(changeCount));
	}

%>