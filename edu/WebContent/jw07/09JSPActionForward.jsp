<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<br/>
<hr/>
	<h3>
	::  09JSPActionForward.jsp ���� <br/>
	
	<!-- 
		request Object Scope ���� ���� Ȯ�� ( Data ���� ���� )
		request Object Scope Data ���� : response ��
		request Object Scope Data ���� �Ұ� : response ��
	 -->
	 
	 <br/> Request Object Scope ���� ���� : <%= request.getAttribute("aaa") %><br/><br/>
	 
	 ::  09JSPActionForward.jsp �� <br/>
	</h3>
<hr/>