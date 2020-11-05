<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page isELIgnored = "false" %> <!-- true로 하면 EL이 무시됨! 디폴트 : false -->

문자 : <%= "홍길동" %><br/>
숫자 : <%= 1234 %><br/><hr/><br/>

<%-- EL Notaion : $ { 표현식 } --%> 
문자 : ${"홍길동"}<br/>
숫자 : ${ 1234 }<br/><hr/><br/>

<h3>1. 기본적 산술, 논리, 관계연산자 사용</h3>
숫자 + 숫자 : ${ 1 + 2 } <br/>
문자 + 숫자 : ${ "1" + 2 } <br/>

10 == 10 : ${10 == 10} <br/>
10 >= 1 : ${ 10 >= 1 } <br/>
!true : ${ !true } <br/><hr/><br/>

<h3>2. 조건연산자 사용</h3>
조건 ? true : false = ${ 10 > 100 ? "10이 100보다 크다 true" : "10이 100보다 작다 false" }
<br/><hr/><br/>

<h3>3. empty 연산자 사용</h3> <!-- empty : 이게 null이냐? -->
empty null : ${ empty null } <br/> <!-- null은 empty 임 -->
empty " " : ${ empty " " } <br/> <!-- 공백도 하나의 문자로 보기 때문에 false -->
empty "" : ${ empty "" } <!-- Null String -->