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
 *  ���������� (Single Point of Entry)
 *  Client �䱸���� �Ǵ� => .do �Ľ�
 *  ��ó�� / ����ó��
 *  	- Work Flow Control :: ����, ���� ��..
 *  	- Client Form Data �ѱ� ó��
 *  Business logic ���� (Bean Method Call)
 *  Model �� View ����
 *  	- Business Logic ó�� ��� JSP ���� (Object Scope / VO ���)
 *  ó���� ����� ����, JSP �� forward / sendRedirect : Navigation
 */

public class ControlServlet extends HttpServlet {

	/// init() Method
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		// ==> web.xml ���� :: <load-on-startup>1</load-on-startup> Ȯ��
		System.out.println("\n\n=====================");
		System.out.println("ControlServlet�� init() Method");
		System.out.println("========================\n");
	}

	/// service() Method
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		System.out.println("\n [ ControlServlet.service() start.....]");

		// ==> Controller :: Client �䱸���� �Ǵ� :: URL/URI ���
		String actionPage = this.getURI(req.getRequestURI()); // ���� �� URI �����ͼ� getURI�� �Ľ� 
		System.out.println(":: URI ? => : " + req.getRequestURI()); // ���� �� URI ������
		System.out.println(":: Client�� �䱸������ ? => " + actionPage);

		// ==> Controller :: ��ó�� / ����ó�� ������ �ִٸ�..
		// ==> �� ���� : �ѱ�ó�� / session ����, ó�� / ��/����ó��
		req.setCharacterEncoding("euc-kr");
		HttpSession session = req.getSession(true);

		// ==> Controller : Navigation (forward / sendRedirect view page ����)
		// Navigation ����Ʈ ������ ����
		String requestPage = "/user/logon.jsp";

		// ==> Controller : ���� / ����ó��
		// ==> session ObjectScope ����� UserVO ��ü �̿� ����
		// ==> ��� 1: session ObjectScope �� userVO �ν��Ͻ� ���� �� ����

		if (session.isNew() || session.getAttribute("userVO") == null) {
			session.setAttribute("userVO", new UserVO()); // session �� ���Ÿ� ���� ����
		}

		// ==> ��� 2 : session ObjectScope userVO ����
		UserVO userVO = (UserVO) session.getAttribute("userVO");

		// ==> UserVO.active �̿� �α��� ���� �Ǵ�
		if (userVO != null && userVO.isActive()) { // userVO�� null���� �ƴϰ� isActive�� true�̸�
			requestPage = "/user/home.jsp";
		}

		// ==> Controller : client �䱸���� ó�� Business layer ����

		// 1. logon.do ��� :: Business Logic ó�� �� �� ���� : default page �� forward
		else if (actionPage.equals("logon")) {

		}

		// 2. logonAction.do ���
		// ==> Controller : client �䱸���� ó�� Business logic ó��
		// ==> client form data ó��
		// ==> client form data �� Business layer �� �����ϱ� ���� VO Binding
		// ==> Business Layer Method ȣ�� �� ��� �� �޾� View(JSP) ���� ��� �� ��
		// ==> �ֵ��� ObjectScope �� ���� :: Model / View ����
		else if (actionPage.equals("logonAction")) {

			// Client Form Data ó��
			String userId = req.getParameter("userId");
			String userPasswd = req.getParameter("userPasswd");

			// Controller : Model�� View �� ���� : Binding
			userVO.setUserId(userId);
			userVO.setUserPasswd(userPasswd);

			// Controller : Business logic ó��
			UserDao userDAO = new UserDao();
			userDAO.getUser(userVO);

			// Controller : Navigation (forward/sendRedirect view page ����)
			if (userVO.isActive()) {
				requestPage = "/user/home.jsp";
			}
		}

		// 3. home.do �� ���
		// ==> �� �α��� ȸ���� home.do Request : default request page�� forward
		else if (actionPage.equals("home")) {

		}

		System.out.println("���� ������ View page �� : [[ " + requestPage + ".jsp ]]");

		// ==> Controller : Navigation (���� ������ page forward)
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(requestPage);
		rd.forward(req, res);

		System.out.println("[ ControlServlet.service() end.... ] ");
	}

	// Client �� �䱸���� �Ǵ� ==> requestURI = "/~~.do" �����̹Ƿ�...
	private String getURI(String requestURI) { // ex) logonAction.do �� logonAction�� ������ �Ľ�!!

		int start = requestURI.lastIndexOf('/') + 1;
		int end = requestURI.lastIndexOf(".do");
		String actionPage = requestURI.substring(start, end);
		return actionPage;
	}

}
