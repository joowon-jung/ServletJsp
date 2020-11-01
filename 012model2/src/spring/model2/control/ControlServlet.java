package spring.model2.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spring.model2.service.user.vo.UserVO;
import spring.model2.service.user.dao.UserDao;

/*
 *  단일인입점 (Single Point of Entry)
 *  Client 요구사항 판단 => .do 파싱
 *  선처리 / 공통처리
 *  	- Work Flow Control :: 권한, 인증 등..
 *  	- Client Form Data 한글 처리
 *  Business logic 수행 (Bean Method Call)
 *  Model 과 View 연결
 *  	- Business Logic 처리 결과 JSP 전달 (Object Scope / VO 사용)
 *  처리된 결과에 따라, JSP 로 forward / sendRedirect : Navigation
 */

public class ControlServlet extends HttpServlet {

	/// init() Method
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		// ==> web.xml 설정 :: <load-on-startup>1</load-on-startup> 확인
		System.out.println("\n\n=====================");
		System.out.println("ControlServlet의 init() Method");
		System.out.println("========================\n");
	}

	/// service() Method
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("\n [ ControlServlet.service() start.....]");

		// ==> Controller :: Client 요구사항 판단 :: URL/URI 사용
		String actionPage = this.getURI(req.getRequestURI()); // 현재 내 URI 가져와서 getURI로 파싱 
		System.out.println(":: URI ? => : " + req.getRequestURI()); // 현재 내 URI 가져옴
		System.out.println(":: Client의 요구사항은 ? => " + actionPage);

		// ==> Controller :: 선처리 / 공통처리 사항이 있다면..
		// ==> 본 예제 : 한글처리 / session 관리, 처리 / 선/공통처리
		req.setCharacterEncoding("euc-kr");
		HttpSession session = req.getSession(true);

		// ==> Controller : Navigation (forward / sendRedirect view page 결정)
		// Navigation 디폴트 페이지 지정
		String requestPage = "/user/logon.jsp";

		// ==> Controller : 권한 / 인증처리
		// ==> session ObjectScope 저장된 UserVO 객체 이용 인증
		// ==> 경우 1: session ObjectScope 에 userVO 인스턴스 생성 및 저장

		if (session.isNew() || session.getAttribute("userVO") == null) {
			session.setAttribute("userVO", new UserVO()); // session 이 새거면 새로 만듦
		}

		// ==> 경우 2 : session ObjectScope userVO 추출
		UserVO userVO = (UserVO) session.getAttribute("userVO");

		// ==> UserVO.active 이용 로그인 유무 판단
		if (userVO != null && userVO.isActive()) { // userVO가 null값이 아니고 isActive가 true이면
			requestPage = "/user/home.jsp";
		}

		// ==> Controller : client 요구사항 처리 Business layer 접근

		// 1. logon.do 경우 :: Business Logic 처리 할 것 없음 : default page 로 forward
		else if (actionPage.equals("logon")) {

		}

		// 2. logonAction.do 경우
		// ==> Controller : client 요구사항 처리 Business logic 처리
		// ==> client form data 처리
		// ==> client form data 를 Business layer 로 전송하기 위한 VO Binding
		// ==> Business Layer Method 호출 및 결과 값 받아 View(JSP) 에서 사용 할 수
		// ==> 있도록 ObjectScope 에 저장 :: Model / View 연결
		else if (actionPage.equals("logonAction")) {

			// Client Form Data 처리
			String userId = req.getParameter("userId");
			String userPasswd = req.getParameter("userPasswd");

			// Controller : Model과 View 의 연결 : Binding
			userVO.setUserId(userId);
			userVO.setUserPasswd(userPasswd);

			// Controller : Business logic 처리
			UserDao userDAO = new UserDao();
			userDAO.getUser(userVO);

			// Controller : Navigation (forward/sendRedirect view page 결정)
			if (userVO.isActive()) {
				requestPage = "/user/home.jsp";
			}
		}

		// 3. home.do 의 경우
		// ==> 비 로그인 회원이 home.do Request : default request page로 forward
		else if (actionPage.equals("home")) {

		}

		System.out.println("최종 결정된 View page 는 : [[ " + requestPage + ".jsp ]]");

		// ==> Controller : Navigation (최종 결정된 page forward)
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(requestPage);
		rd.forward(req, res);

		System.out.println("[ ControlServlet.service() end.... ] ");
	}

	// Client 의 요구사항 판단 ==> requestURI = "/~~.do" 형식이므로...
	private String getURI(String requestURI) { // ex) logonAction.do 면 logonAction만 나오게 파싱!!

		int start = requestURI.lastIndexOf('/') + 1;
		int end = requestURI.lastIndexOf(".do");
		String actionPage = requestURI.substring(start, end);
		return actionPage;
	}

}
