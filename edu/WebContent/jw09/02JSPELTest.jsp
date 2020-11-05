<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>

<h3>4. 색인 (. , [] ) 연산자 사용</h3>

<%-- 아래 내용은 Servlet Controller가 화면 구성을 위해 ObjectScope에 저장 한 것으로 생각 --%>

<%
	// 1. page ObjectScope 저장
	pageContext.setAttribute("abc", new String("page ObjectScope 저장된 문자열"));
	
	// 2. request ObjectScope 저장
	request.setAttribute("abc", new String("request ObjectScope 저장된 문자열"));
	
	// 3.1 session ObjectScope (size() == 0 인) ArrayList 저장
	session.setAttribute("abc", new ArrayList());
	
	// 3.2 session ObjectScope 값을 갖는 ArrayList 저장
	ArrayList arrayList = new ArrayList();
	arrayList.add(new String("ArrayList 저장된 문자열"));
	session.setAttribute("def", arrayList);
	
	// 4. Application ObjectScope 값을 갖는 HashMap 저장
	HashMap hashMap = new HashMap();
	hashMap.put("zzz", new String("HashMap 저장된 문자열"));
	application.setAttribute("abc", hashMap);
%>

<!-- 
	EL : 자체 연산자 . 와 [] 연산자를 제공
	ObjectScope 을 사용 할 수 있게 자체적으로 implicit Object 제공
	page ==> pageScope
	request ==> requestScope
	session ==> sessionScope
	application ==> applicationScope
 -->
 
<h5>1. page ObjectScope 접근</h5>
${ pageScope.abc } = ${ pageScope["abc"] }

<h5>2. request ObjectScope 접근</h5>
${ requestScope.abc } = ${ requestScope["abc"] }

<h5>3. session ObjectScope 접근 : 저장된 ArrayList empty 유무</h5>
${ empty sessionScope.abc } = ${ empty sessionScope["abc"] }

<h5>4.1 session ObjectScope 접근 : 저장된 ArrayList empty 유무</h5>
${ empty sessionScope.def } = ${ empty sessionScope["def"] }

<h5>4.2 session ObjectScope 접근</h5>
${ sessionScope.def[0] } = ${ sessionScope["def"][0] } 

<h5>5.1 application ObjectScope 접근 : 저장된 HashMap empty 유무</h5>
${ empty applicationScope.abc } = ${ empty applicationScope["abc"] } 

<h5>5.2 application ObjectScope 접근</h5>
${ applicationScope.abc.zzz } = ${ applicationScope["abc"].zzz }  <br/><hr/><br/>

<h3>Object Scope 의 영역을 지정하지 않고 EL 을 사용하여 abc 접근 시 </h3>
어떤 ObjectScope 값이 추출되는지 확인 <br/><br/>
어떤 ObjectScope 의 내용인가 : ${abc} </h3>