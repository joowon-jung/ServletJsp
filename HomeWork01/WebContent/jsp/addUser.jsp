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
		
		//DB에 접근하는 UserDAO를 이용하여 회원정보 Insert
		UserDAO bean = new UserDAO();
		bean.addUser(userVO);
%>
<html>
<head></head>
<body>
<h2>회원가입 화면</h2>
	<%
		if (userVO.isActive()) {
	%>
	<%=userVO.getId()%>
	님 회원가입을 축하합니다.
	<br>
	<a href='findUser.html'>내 정보 보기 1 (html 거쳐서 id 입력) </a>
	<%
		request.getSession(true).setAttribute("userVO", userVO);
	%>
	<br>
	<a href='findUser.jsp'>내 정보 보기 2 (세션 사용) </a>
	<%
		} else {
	%>
	회원정보를 다시 확인해주세요.
	<% } %>
	<p><p><a href='addUser.html'>뒤로</a>
</body>
</html>