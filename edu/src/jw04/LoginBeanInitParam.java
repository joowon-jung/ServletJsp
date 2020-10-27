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
 * 	UserDao.java : Ư�� DB �� ��������
 *  DB ���� ������ web.xml(Meta-Data)�� ���
 *  => Ư�� DB�� ���������� �ʴ� DAO ���� ���� !
 */
//@WebServlet("/LoginBeanInitParam")
public class LoginBeanInitParam extends HttpServlet {
	
	///Field
	private String jdbcDriver;
	private String jdbcURL;
	private String jdbcUser;
	private String jdbcPasswd;

	// init() method �������̵�
	// ==> web.xml(Meta-data)�� ��ϵ� ���� ����
	// ==> init() method�� Client�� ���� request �� "1��"�� ȣ���! (Servlet Life Cycle)
	public void init(ServletConfig sc) throws ServletException {
					// ServletConfig : meta-data ������ �޴� ��ü 
		super.init(sc);
		jdbcDriver = sc.getInitParameter("jdbcDriver");
		jdbcURL = sc.getInitParameter("jdbcURL");
		jdbcUser = sc.getInitParameter("jdbcUser");
		jdbcPasswd = sc.getInitParameter("jdbcPasswd");
		
		//==> ������ : Console�� ��µ�
		System.out.println("web.xml�� ��ϵ� InitParam���� �������� Ȯ��");
		System.out.println("jdbcDriver : " + jdbcDriver);
		System.out.println("jdbcURL : " + jdbcURL);
		System.out.println("jdbcUser : " + jdbcUser);
		System.out.println("jdbcPasswd : " + jdbcPasswd);
	}

	//doPost() method �������̵�
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("EUC_KR");
		res.setContentType("text/html;charset=EUC_KR");
		PrintWriter out = res.getWriter();
		
		//html ���Ͽ��� Ŭ���̾�Ʈ�� �Է��� ������ �޾ƿ�
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		// UserVO instance ���� �� Ŭ���̾�Ʈ�� �Է��� ������ ���� (binding)
		UserVO userVO = new UserVO();
		// ���°� ����
		userVO.setId(id);
		userVO.setPwd(pwd);
		
		// DB ����. ������ �˻��ؼ� ���� �� UserVO.active true/false ����
		UserInitParamDao dao = new UserInitParamDao();
		// DB �������� driver, url, user, password ����
		dao.setJdbcDriver(jdbcDriver);
		dao.setJdbcURL(jdbcURL);
		dao.setJdbcUser(jdbcUser);
		dao.setJdbcPasswd(jdbcPasswd);
		
		dao.getUser(userVO); // userVO�� ���뼺! ��� ��� ����!
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		
		out.println("<h2>Login ȭ��</h2>");
		
		// DBMS Data �� Client Form Data �� ȸ�� ���� �Ǵ�
		if (userVO.isActive()) {
			out.println(id + "�� ȯ���մϴ�.");
		} else {
			out.println("Login ����! id, pwd�� Ȯ���ϼ���.");
		}
		
		out.println("<p><p><a href='/edu/jw04/loginBeanInitParam.html'>�ڷ�</a>");
		out.println("<body>");
		out.println("</html>");
	}
	


}
