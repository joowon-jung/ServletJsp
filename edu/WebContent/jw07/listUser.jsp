<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "java.util.ArrayList" %> 
<%@ page import = "java.util.List" %>    
<%@ page import = "jw.service.user.vo.UserVO" %>
<%@ page import = "jw.service.user.dao.UserDao" %>

<!-- 
	WorkFlow Control : ����� �ڵ� : �α��� ���� Ȯ��
	�α��� ȸ���� session ObjectScope�� userVO �� �����ϸ�
	Field�� active �� true
 -->
 
 <%
 	UserVO userVO = (UserVO)session.getAttribute("userVO");
 	if (userVO == null ) {
 		userVO = new UserVO();
 	}
 %>
 
 <!-- 
	�α��� �� �� ȸ�� : UserVO.active false
		1. �α��� page include
		2. ȸ������ page�� �̵� �� �� �ִ� link display
	�α��� �� ȸ�� : UserVO.active true ���
		1. DAO ���� ȸ�� ���� GET
		2. ȸ������ display
 -->
 
 <%  if (!userVO.isActive()) { %> <!-- �α����� �� �Ǿ������� -->
 			<!--  1. �α��� page include... -->
 			<jsp:include page = "../jw07/login.html"/>
 			<br/><br/>
 			<!-- 2. ȸ������ page�� �̵� �� �� �ִ� link -->
 			<a href = "/edu/jw07/addUser.html"> ȸ������... </a>
 <% } else { %>
 			<center><h1>ȸ�������� ��ü ���...</h1></center><hr/>
 			<center>
 				<table border = '1'>
 					<tr>
 						<th>��ȣ</th>
 						<th>���̵�</th>
 						<th>��й�ȣ</th>
 					</tr>
 					<% UserDao userDao = new UserDao(); %>
 					<% List<UserVO> arrayList = userDao.getUserList(); %>
 					<% for (int i = 0; i < arrayList.size(); i++) { %>
 							<% UserVO user = arrayList.get(i); %>
 						<tr>
 							<td><%= user.getNo() %></td>
 							<td><%= user.getId() %></td>
 							<td><%= user.getPwd() %></td>
 						</tr>
 					<% } %>
 				</table>
 			</center>
 <% } %>