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
 * 		Client Request (home.do) ó�� Controller
 */
public class HomeController implements Controller {

	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("[ HomeController.execute() start...]");

		HttpSession session = req.getSession(true);

		// session ObjectScope�� ����� UserVO ��ü �̿� 
		// ��� 1 : session ObjectScope �� userVO �ν��Ͻ� ���� �� ���� 
		if (session.isNew() || session.getAttribute("userVO") == null) {
			session.setAttribute("userVO", new UserVO()); // session �� ���Ÿ� ���� ����
		}

		// ��� 2 : session ObjectScope userVO ���� 
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		System.out.println(userVO); // �α��� �� �Ǿ������� NULL �� �� 

		// Controller :: Navigation (forward/sendRedirect view page ����)
		// Navigation ����Ʈ ������ ����
		String requestPage = "/user/logon.jsp";

		// UserVO.active �̿��Ͽ� �α��� ���� �Ǵ�. �α����̸� true 
		if (userVO.isActive()) { // �α��� �Ǿ������� isActive�� true�� �ٲ� �� 
			requestPage = "/user/home.jsp";
		}

		// Controller : Navigation (���� ������ page forward)
		RequestDispatcher rd = req.getRequestDispatcher(requestPage);
		rd.forward(req, res);

		System.out.println("[ HomeController.execute() end...]");

	}

}
