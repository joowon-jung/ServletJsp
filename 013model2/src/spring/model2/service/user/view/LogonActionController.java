package spring.model2.service.user.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spring.model2.control.Controller;
import spring.model2.service.user.dao.UserDao;
import spring.model2.service.user.vo.UserVO;

/*
 * 		Client의 Request (logonAction.do) 처리 Controller 
 */
public class LogonActionController implements Controller {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("[ LogonActionController.execute() start...]");

		HttpSession session = req.getSession(true);

		// session ObjectScope에 저장된 UserVO 객체 이용 
		// 경우 1 : session ObjectScope 에 userVO 인스턴스 생성 및 저장 
		if (session.isNew() || session.getAttribute("userVO") == null) {
			session.setAttribute("userVO", new UserVO());
		}

		// 경우 2 : session ObjectScope userVO 추출 
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		System.out.println(userVO); // 디버깅 위함 :: 로그인 안 되어있으면 NULL 값 들어감 

		// Navigation 디폴트 페이지 지정 
		String requestPage = "/user/logon.jsp";

		// UserVO.active 이용하여 로그인 유무 판단. 로그인이면 true 
		if (userVO.isActive()) {
			requestPage = "/user/home.jsp";
		}

		// 로그인 한 회원이 아니라면?
		else {
			// Client Form Data 처리
			String userId = req.getParameter("userId");
			String userPasswd = req.getParameter("userPasswd");

			// Controller : Model과 View 의 연결 : Binding
			userVO.setUserId(userId);
			userVO.setUserPasswd(userPasswd);

			// Controller : Business logic 처리
			UserDao userDAO = new UserDao();
			userDAO.getUser(userVO); // 데이터 값이 있으면 true로 바뀔 것

			// Controller : Navigation (forward/sendRedirect view page 결정)
			if (userVO.isActive()) {
				requestPage = "/user/home.jsp";
			}
		}

		// Controller : Navigation (최종 결정된 page forward)
		RequestDispatcher rd = req.getRequestDispatcher(requestPage);
		rd.forward(req, res);

		System.out.println("[ LogonActionController.execute() end...]");

	}

}
