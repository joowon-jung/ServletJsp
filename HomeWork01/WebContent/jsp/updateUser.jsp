<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jw.services.user.dao.*" 
		 import="jw.services.user.vo.*" %>

<%
	request.setCharacterEncoding("UTF-8");

//client 에서 넘어온 값을 받자 
String id = request.getParameter("id");
String name = request.getParameter("name");
String sex = request.getParameter("sex");
String birth = request.getParameter("birth");
String edu = request.getParameter("edu");
String job = request.getParameter("job");
String pnum1 = request.getParameter("pnum1");
String pnum2 = request.getParameter("pnum2");
String pnum3 = request.getParameter("pnum3");
String address = request.getParameter("address");

//Client에 받은 data로 UserVO instance 생성 및 값 세팅 
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO.setName(name);
		userVO.setSex(sex);
		userVO.setBirth(birth);
		userVO.setEdu(edu);
		userVO.setJob(job);
		userVO.setPhone_num(pnum1+pnum2+pnum3);
		userVO.setAddress(address);
		
		//DB에 접근하는 UserDAO를 이용하여 회원정보 Update
		UserDAO bean = new UserDAO();
		bean.updateUser(userVO);
%>
<html>
<head></head>
<body>
<h2>회원수정완료 화면</h2>
	<%
		if (userVO.isActive()) {
	%>
	회원수정이 완료되었습니다.
	<br>
	<br>
	<%= userVO.getId() %> 님 의 수정된 회원정보 <br><br>
	이름 : <%= userVO.getName() %> <br>
	성별 : <%=userVO.getSex() %> <br>
	생년 : <%=userVO.getBirth() %> <br>
	학력 :  <%=userVO.getEdu() %> <br>
	직업 : <%=userVO.getJob() %> <br>
	연락처 :  <%=userVO.getPhone_num() %> <br>
	주소 : <%=userVO.getAddress() %> <br>

	<%
		} else {
	%>
	회원수정에 문제가 생겼습니다.
	<% } %>
	<p><p><a href='updateUserView.jsp'>뒤로</a>
</body>
</html>