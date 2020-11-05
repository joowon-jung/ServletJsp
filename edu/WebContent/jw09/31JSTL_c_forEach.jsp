<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- JSTL 사용 : taglib 지시자 설정 -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- c:forEach : while / for 비교 -->
<h3>c:forEach 이용 구구단을 출력</h3>

	1. c:forEach 이용 : 5단출력 <br/>
	<c:forEach var = "i" begin = "1" end = "10" step = "1">
		5 * ${ i } = ${5 * i} <br/>
	</c:forEach><br/><hr/>
	
	2. 중첩 c:forEach 이용 : 구구단 짝수 출력 <br/>
	<c:forEach var = "i" begin = "2" end = "9" step = "2">
		[ ${ i } ] 단을 출력합니다. <br/>
		<c:forEach var = "j" begin = "1" end = "10" step = "1">
			${ i } * ${ j } = ${ j * i } <br/>
		</c:forEach>
		<br/>
	</c:forEach><hr/>
	
<h3>3. index 관리 Collection 접근</h3>
	<%
		// Controller 단에 이런 코드가 적혀져 있을 것 
		java.util.Vector vector = new java.util.Vector();
		vector.add("A");
		vector.add("B");
		vector.add("C");
		vector.add("D");
		
		// console 확인 후 아래의 ForEach 와 비교
	%>
	
	<%-- end > size() 경우 : 출력은? --%>
	<c:forEach var = "i" items = "<%=vector %>" begin = "0" step = "1" end = "10">
		Vector 저장 정보 추출 : ${ i }<br/>
	</c:forEach><br/>
	
	<%-- end 생략 / begin 확인 : 출력은 ? --%>
	<c:forEach var = "i" items = "<%= vector %>" begin = "1" step = "1">
		Vector 저장 정보 추출 : ${ i }<br/>
	</c:forEach><br/>
	
	<%-- end 생략 / step 확인 : 출력은 ? --%>
	<c:forEach var = "i" items = "<%= vector %>" begin = "0" step = "2">
		Vector 저장 정보 추출 : ${ i }<br/>
	</c:forEach><br/>
	
	<%-- end < size() 경우 : 출력은? --%>
	<c:forEach var = "i" items = "<%=vector %>" begin = "0" step = "1" end = "1">
		Vector 저장 정보 추출 : ${ i }<br/>
	</c:forEach><br/>
	
<h3>4. key = value 형식의 map 의 value 접근</h3><br/>
	<%
		java.util.HashMap hasMap = new java.util.HashMap();
		hasMap.put("a", "A");
		hasMap.put("b", "B");
		hasMap.put("c", "C");
		hasMap.put("d", "D");
	%>
	<!-- hasMap 참조하는 Map 정보 i 변수에 저장 -->
	<c:forEach var = "i" items = "<%= hasMap %>"> <!-- begin, end 지정 안 해주면 map 에 저장한 값 다 꺼내옴! -->
		HashMap 에 저장된 내용은 : ${ i.key } = ${ i.value } <br/>
	</c:forEach><br/><hr/>
	
<h3>5. EL / c:set, c:if 를 동시 사용 </h3><br/>
	<c:set var = "aaa" value = "<%=hasMap %>" />
	
	<c:forEach var = "i" items = "${ aaa }">
		<c:if test = "${ i.key == 'a' }">
			key a 의 value : ${ i.value }
		</c:if>
	</c:forEach>