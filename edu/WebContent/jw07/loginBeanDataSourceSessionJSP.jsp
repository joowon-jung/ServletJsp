<%@ page language="java" 
	contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!-- <%@ page import = "jw04.*"  %> jw04.이렇게 안 하고 아예 패키지 import 해버려도 됨! --> 
<%
request.setCharacterEncoding("EUC_KR");
//response.setContentType("text/html;charset=EUC_KR"); // <%@ page 에서 해주고 있으니까 주석처리 
//PrintWriter out = res.getWriter();

// Client Form Data 처리
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");

//session 유무 확인 :: 새로운 HttpSession 생성 OR 기존 HttpSession GET
//HttpSession session = req.getSession(true);

// login 한 회원 :: session 에 저장된 UserVO Get
// login 안 한 회원 :: session 에 저장된 UserVO 없으므로 null Gest
jw04.UserVO userVO = (jw04.UserVO) session.getAttribute("userVO"); // 로그인 되어있나 확인하기 위함 
// session 은 값을 가져올때 Object 타입이므로 자기 자신으로 명시적 형변환
// 세션이 만들어지지 않았을 때 이 코드를 실행하게 되면 null이 찍힘! 
System.out.println("session 에 저장된 UserVO 유무 확인 : " + userVO);

//아래의 두 경우를 고려
// ==> 고려할 사항 1
// loginBeanPoolSession.html 을 거쳐서 오는 경우
// 1. id를 입력하지 않은 경우 : req.getParameter("id") 의 값 ==> ""(NullString)

// ==> 고려할 사항 2
// Browser 주소창에 직접 URL
// http://127.0.0.1:8080/edu/LoginBeanDataSourceSession 입력한 경우
// 2. req.getParameter("id") 의 값 ==> null

// ==> 1, 2 인 경우 DB 접근 불필요 :: 입력 값의 유효성을 check 하는 if문
if (!(id == null || id.equals(""))) { // validation check 해서 데이터 입력할때만 DB접근
	//UserVO instance 생성 및 Client Form Data 전달 (Binding)
	userVO = new jw04.UserVO();
	userVO.setId(id);
	userVO.setPwd(pwd);

	// DB 접근 Data 검색 비교 UserVO.active true / false 변경
	jw04.UserDataSourceDao userDataSourceDao = new jw04.UserDataSourceDao();
	userDataSourceDao.getUser(userVO); // 여기서 유저 정보 확인하고 맞으면 isActive를 true로 바꿔줌 
}
%>

<html>
<head></head>
<body>
<h2>Login 화면</h2>
<%  if (userVO != null && userVO.isActive()) {%>
<%= userVO.getId() %> 님 환영합니다.
<%
//Login 이 정상적으로 이루어진 경우
// session 에 로그인 정보로 userVO instance 저장
session.setAttribute("userVO", userVO); // 세션에 유저 정보 저장 
%>
<% } else { %>
Login 실패! id, pwd를 확인하세요.
<% } %>
<p><p><a href='loginBeanDataSourceSessionJSP.html'>뒤로</a>
</body>
</html>