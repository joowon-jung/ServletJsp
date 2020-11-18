package spring.model2.service.user.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spring.model2.control.Controller;
import spring.model2.control.ModelAndView;
import spring.model2.service.user.vo.UserVO;

/*
 * 		Client�� Request (logon.do) ó�� Controller 
 */
public class LogonController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("[ LogonController.execute() start...]");
		
		HttpSession session = req.getSession(true); // ������ ������ �ִ°� ��������, ������ ���ο� ���� ���� 
		
		// session ObjectScope�� ����� UserVO ��ü �̿� 
		// ��� 1 : session ObjectScope �� userVO �ν��Ͻ� ���� �� ���� 
		if (session.isNew() || session.getAttribute("userVO") == null) { // ������ ���� ���鶧
			session.setAttribute("userVO", new UserVO()); // session�� userVO ��ü ���� 
		}
		
		// ��� 2 : session ObjectScope userVO ���� 
		UserVO userVO = (UserVO)session.getAttribute("userVO"); // ���ǿ� ����� userVO ��ü ������
		System.out.println(userVO); // ����� ���� :: �α��� �� �Ǿ������� NULL �� �� 
		
		// Controller :: Navigation (forward/sendRedirect view page ����)
		// Navigation ����Ʈ ������ ���� 
		String requestPage = "/user/logon.jsp"; 
		
		// UserVO.active �̿��Ͽ� �α��� ���� �Ǵ�. �α����̸� true 
		if (userVO.isActive()) {
			requestPage = "/user/home.jsp";
		}
		
		//============ ����� �κ� =================
		// Controller :: Navigation (���� ������ page forward)
		//RequestDispatcher rd = req.getRequestDispatcher(requestPage);
		//rd.forward(req, res); // Servlet������ forward 
		
		System.out.println("[ LogonController.execute() end...]");
		
		// �̵��� View (jsp page) ������ ���� ModelAndView ��ü return 
		return new ModelAndView(requestPage, "info", "[LogonController Message] :: Welcome");
		
	}
}
