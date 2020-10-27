package jw04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginBeanPool extends HttpServlet {

	// id, pwd�� ������ �Ŵϱ� post ��� ���!
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
		UserPoolDao userPooldao = new UserPoolDao();
		userPooldao.getUser(userVO);
		
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
		
		out.println("<p><p><a href='/edu/jw04/loginBeanPool.html'>�ڷ�</a>");
		out.println("<body>");
		out.println("</html>");
	
	}

}