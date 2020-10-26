<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jw.services.user.dao.*" 
		 import="jw.services.user.vo.*" %>

<%
	request.setCharacterEncoding("UTF-8");

//client 에서 넘어온 값을 받자 
String id = request.getParameter("id");

if (id == null) { // AddUser에서 내정보보기2(세션 사용)를 눌렀을 경우에는 아무런 값도 넘어오지 않는다.
	// html을 거치지 않았으니까 get 방식으로 넘어옴 => doPost메소드만 있으면 오류나므로 doGet 만듦 => doGet 메소드를 거쳐 doPost메소드로 왔음.
	// 결론 : 아무런 값도 가지고 오지 않았으니까 id 값이 null일경우 => 세션 이용한 내정보보기!
	//==>  Session에 저장된 userVO instance 의 id 사용
	session = request.getSession(true);
	//				UserVO userVO = (UserVO) session.getAttribute("userVO");
	//				id = userVO.getId();
	id = ((UserVO) session.getAttribute("userVO")).getId();
}

// DB에 접근하는 UserDAO를 이용하여 회원정보 Select
UserDAO bean = new UserDAO(); // 인스턴스 생성 

// UserDAO 안의 findUser 메소드 호출하여 UserVO instance 받기
UserVO userVO = bean.findUser(id); // findUser에서 찾는 회원정보가 없으면 null 리턴되어 userVO에 null이 담김
%>

<html>
<head></head>
<body>
<h2>회원정보 화면</h2>

<% if (userVO != null) { %>
이름 : <%= userVO.getId() %> 님 의 회원정보 <br>
성별 : <%=userVO.getSex() %> <br>
생년 : <%=userVO.getBirth() %> <br>
학력 :  <%=userVO.getEdu() %> <br>
직업 :<%=userVO.getJob() %> <br>
연락처 :  <%=userVO.getPhone_num() %> <br>
주소 : <%=userVO.getAddress() %> <br>
<% } else { %>
<%= id %> 에 해당하는 회원정보 없음! id를 확인하세요.
<% } %>
<p><p><a href='findUser.html'>뒤로</a>
</body>
</html>