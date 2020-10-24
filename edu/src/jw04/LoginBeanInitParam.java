package jw04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 	UserDao.java : 특정 DB 에 종속적임
 *  DB 접속 정보를 web.xml(Meta-Data)에 등록
 *  => 특정 DB에 종속적이지 않는 DAO 구성 가능 !
 */
//@WebServlet("/LoginBeanInitParam")
public class LoginBeanInitParam extends HttpServlet {
	
	///Field
	private String jdbcDriver;
	private String jdbcURL;
	private String jdbcUser;
	private String jdbcPasswd;

	// init() method 오버라이딩
	// ==> web.xml(Meta-data)에 등록된 정보 추출
	// ==> init() method는 Client의 최초 request 시 "1번"만 호출됨! (Servlet Life Cycle)
	public void init(ServletConfig sc) throws ServletException {
					// ServletConfig : meta-data 정보를 받는 객체 
		super.init(sc);
		jdbcDriver = sc.getInitParameter("jdbcDriver");
		jdbcURL = sc.getInitParameter("jdbcURL");
		jdbcUser = sc.getInitParameter("jdbcUser");
		jdbcPasswd = sc.getInitParameter("jdbcPasswd");
		
		//==> 디버깅용 : Console에 출력됨
		System.out.println("web.xml에 등록된 InitParam값을 추출유무 확인");
		System.out.println("jdbcDriver : " + jdbcDriver);
		System.out.println("jdbcURL : " + jdbcURL);
		System.out.println("jdbcUser : " + jdbcUser);
		System.out.println("jdbcPasswd : " + jdbcPasswd);
	}

	//doPost() method 오버라이딩
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("EUC_KR");
		res.setContentType("text/html;charset=EUC_KR");
		PrintWriter out = res.getWriter();
		
		//html 파일에서 클라이언트가 입력한 데이터 받아옴
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		// UserVO instance 생성 및 클라이언트가 입력한 데이터 전달 (binding)
		UserVO userVO = new UserVO();
		// 상태값 세팅
		userVO.setId(id);
		userVO.setPwd(pwd);
		
		// DB 접근. 데이터 검색해서 비교한 후 UserVO.active true/false 변경
		UserInitParamDao dao = new UserInitParamDao();
		// DB 접속정보 driver, url, user, password 설정
		dao.setJdbcDriver(jdbcDriver);
		dao.setJdbcURL(jdbcURL);
		dao.setJdbcUser(jdbcUser);
		dao.setJdbcPasswd(jdbcPasswd);
		
		dao.getUser(userVO); // userVO의 재사용성! 계속 사용 가능!
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		
		out.println("<h2>Login 화면</h2>");
		
		// DBMS Data 와 Client Form Data 비교 회원 유무 판단
		if (userVO.isActive()) {
			out.println(id + "님 환영합니다.");
		} else {
			out.println("Login 실패! id, pwd를 확인하세요.");
		}
		
		out.println("<p><p><a href='/edu/jw04/loginBeanInitParam.html'>뒤로</a>");
		out.println("<body>");
		out.println("</html>");
	}
	


}
