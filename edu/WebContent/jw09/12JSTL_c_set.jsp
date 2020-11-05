<%@ page 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	c:set, c:remove 등.. 태그중심이긴 하나 Controller단의 역할이다.
	화면 (jsp)에서는 get 해서 가져오는 역할만 하기 때문에 이런걸 쓸 일은 없지만 한번 연습해보는 것 !
	자바코드를 "태그 중심"으로 할 수 있다는 것 알아두기 
 -->

<!-- JSTL 사용 : taglib 지시자 설정 -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- JSTL c:set Bean 생성 ObjectScope 저장 -->
<c:set var = "client" value = "<%= new jw09.Client() %>" scope = "session" />

<h3>EL 사용 ObjectScope 에 저장된 Bean 접근 </h3>
${sessionScope.client.name }<br/>
${client.addr }<br/>
${client.age }<br/>
${empty client.info }<br/>
${sessionScope.client.info[0] }<br/>
${client.info[1] }<br/>

<h3># 스크립트렛 이용 ObjectScope 에 저장된 Bean 접근</h3>
<%-- Call by Value / Call by Reference
 		A a1 = new A();
 		A a2 = a;				--%>
<!-- JSTL : session ObjectScope 저장된 client를 test로 다시 저장 -->
<c:set var ="test" value = "${client }" scope="session" />

<h3>EL 이용 ObjectScope 에 저장된 Bean 접근 </h3>
1. session ObjectScope 저장된 client 객체의 name : ${client.name }<br/>
1. session ObjectScope 저장된 test 객체의 name : ${test.name }<br/>

<h3>ObjectScope 저장된 test Bean setter Method 호출 (홍길동 -> 이순신으로 변경)<br/>
	Call by Reference 고려!</h3>
<c:set target = "${test }" property = "name" value = "이순신" />
2. session ObjectScope 저장된 client 객체의 name : ${client.name }<br/>
2. session ObjectScope 저장된 test 객체의 name : ${test.name }<br/>

<h3>JSTL c:remove ObjectScope 저장된 test remove </h3>
remove 전 client 객체는 empty : ${empty sessionScope.client} <br/>
Session 에 저장된 client 객체 remove <br/>
<c:remove var = "client" scope = "session" />
remove 후 client 객체는 empty : ${empty client} <br/>
remove 하지 않은 test 객체는 empty : ${empty test}<br/>