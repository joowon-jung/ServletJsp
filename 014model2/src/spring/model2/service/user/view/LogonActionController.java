package spring.model2.service.user.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spring.model2.control.Controller;
import spring.model2.control.ModelAndView;
import spring.model2.service.user.dao.UserDao;
import spring.model2.service.user.vo.UserVO;

/*
 * 		Client�� Request (logonAction.do) ó�� Controller 
 */
public class LogonActionController implements Controller {

	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("[ LogonActionController.execute() start...]");

		HttpSession session = req.getSession(true);

		// session ObjectScope�� ����� UserVO ��ü �̿� 
		// ��� 1 : session ObjectScope �� userVO �ν��Ͻ� ���� �� ���� 
		if (session.isNew() || session.getAttribute("userVO") == null) {
			session.setAttribute("userVO", new UserVO());
		}

		// ��� 2 : session ObjectScope userVO ���� 
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		System.out.println(userVO); // ����� ���� :: �α��� �� �Ǿ������� NULL �� �� 

		// Navigation ����Ʈ ������ ���� 
		String requestPage = "/user/logon.jsp";

		// UserVO.active �̿��Ͽ� �α��� ���� �Ǵ�. �α����̸� true 
		if (userVO.isActive()) {
			requestPage = "/user/home.jsp";
		}

		// �α��� �� ȸ���� �ƴ϶��?
		else {
			// Client Form Data ó��
			String userId = req.getParameter("userId");
			String userPasswd = req.getParameter("userPasswd");

			// Controller : Model�� View �� ���� : Binding
			userVO.setUserId(userId);
			userVO.setUserPasswd(userPasswd);

			// Controller : Business logic ó��
			UserDao userDAO = new UserDao();
			userDAO.getUser(userVO); // ������ ���� ������ true�� �ٲ� ��

			// Controller : Navigation (forward/sendRedirect view page ����)
			if (userVO.isActive()) {
				requestPage = "/user/home.jsp";
			}
		}

		//============ ����� �κ� =================
		// Controller : Navigation (���� ������ page forward)
		//RequestDispatcher rd = req.getRequestDispatcher(requestPage);
		//rd.forward(req, res);

		System.out.println("[ LogonActionController.execute() end...]");
		
		// �̵��� View (jsp page) ������ ���� ModelAndView ��ü return 
		return new ModelAndView(requestPage, "info", "[LogonActionController Message] :: Welcome");

	}

}
