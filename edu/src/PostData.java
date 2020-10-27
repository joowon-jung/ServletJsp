import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	�� Post ������� ���۵� Client Form Data ó��
*/
public class PostData extends HttpServlet{

	// 1. Client Post ������� Request : doPost() method �������̵�
	// 2. service() method �������̵�
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		// res.setCharacterEncoding("~~") ==> API Ȯ��

		// 16������ ���ڵ��� client form data�� �ѱ۷� ���ڵ�
		req.setCharacterEncoding("EUC_KR");
		// POST ��ĸ� �̷��� ����! ���ڵ� ���ִ� ������ body �����ε�,
		// post ����� body �ȿ� name=value ������ ������� ���������� ������!
		// (get ����� url�� ������� �������� �Ұ���..) 

		// �Ʒ��� �� ������ servlet���� client�� Html ���� �� �ʼ��� ����� �ϴ� �ڵ�
		res.setContentType("text/html;charset=EUC_KR"); // �ѱ� �� ����!
		PrintWriter out = res.getWriter();

		// client from data(QueryString :: name=value)ó�� (APIȮ��)
		String clientName = req.getParameter("name");
		String clientAddr = req.getParameter("addr");

		// client form data ���
		System.out.println(clientName + ":" + clientAddr);

		out.println("<html>");	
		out.println("<head><title>PostData.java</title></head>");
		out.println("<body>");

		out.println("<h2>Post Test </h2>");
		out.println("<li> �̸� : " + clientName);
		out.println("<li> �ּ� : " + clientAddr);

		out.println("<p><p><a href='/edu/postData.html'>�ڷ�</a>");

		out.println("</body>");
		out.println("</html>");	
	}

}//end of class