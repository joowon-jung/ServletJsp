package jw04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

							// ������ �ݵ�� ���� �������̽��� Ȯ���ؾ� �Ѵ� 
public class LoginBean extends HttpServlet {

	// id, pwd�� ������ �Ŵϱ� post ��� ���!
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("EUC_KR");
		res.setContentType("text/html;charset=EUC_KR");
		PrintWriter out = res.getWriter();
		
		//html ���Ͽ��� Ŭ���̾�Ʈ�� �Է��� ������ �޾ƿ�
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		// DB ����. data �˻��� DbBean �̿��Ͽ� ȸ�� ���� Ȯ��
		DbBean dbBean = new DbBean();
		// ���°� ����
		dbBean.setId(id);
		dbBean.setPwd(pwd);
		
		// DbBean.getUser() method : DB ����. data �˻� �� �� true/false return
		boolean isLogin = dbBean.getUser();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		
		out.println("<h2>Login ȭ��</h2>");
		
		// DBMS Data �� Client Form Data �� ȸ�� ���� �Ǵ�
		if (isLogin) {
			out.println(id + "�� ȯ���մϴ�.");
		} else {
			out.println("Login ����! id, pwd�� Ȯ���ϼ���.");
		}
		
		out.println("<p><p><a href='/edu/jw04/loginBean.html'>�ڷ�</a>");
		out.println("<body>");
		out.println("</html>");
	
	}

}