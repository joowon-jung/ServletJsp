package spring.model2.service.user.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spring.model2.control.Controller;
import spring.model2.service.user.vo.UserVO;

/*
 * 		Client의 Request (logon.do) 처리 Controller 
 */
public class LogonController implements Controller {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("[ LogonController.execute() start...]");
		
		HttpSession session = req.getSession(true); // 세션이 있으면 있는걸 가져오고, 없으면 새로운 세션 만듦 
		
		// session ObjectScope에 저장된 UserVO 객체 이용 
		// 경우 1 : session ObjectScope 에 userVO 인스턴스 생성 및 저장 
		if (session.isNew() || session.getAttribute("userVO") == null) { // 새션을 새로 만들때
			session.setAttribute("userVO", new UserVO()); // session에 userVO 객체 저장 
		}
		
		// 경우 2 : session ObjectScope userVO 추출 
		UserVO userVO = (UserVO)session.getAttribute("userVO"); // 세션에 저장된 userVO 객체 가져옴
		System.out.println(userVO); // 디버깅 위함 :: 로그인 안 되어있으면 NULL 값 들어감 
		
		// Controller :: Navigation (forward/sendRedirect view page 결정)
		// Navigation 디폴트 페이지 지정 
		String requestPage = "/user/logon.jsp"; 
		
		// UserVO.active 이용하여 로그인 유무 판단. 로그인이면 true 
		if (userVO.isActive()) {
			requestPage = "/user/home.jsp";
		}
		
		// Controller :: Navigation (최종 결정된 page forward)
		RequestDispatcher rd = req.getRequestDispatcher(requestPage);
		rd.forward(req, res); // Servlet에서의 forward 
		
		System.out.println("[ LogonController.execute() end...]");
		
	}
}
