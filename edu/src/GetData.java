import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	�� Get ������� ���۵� Client Form Data ó��
*/
public class GetData extends HttpServlet{

	// 1. Client Get ������� Request : doGet() method �������̵�
	// 2. service() method �������̵� ���� 
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		// �Ʒ��� �� ������ servlet���� client�� Html ���� �� �ʼ��� ����� �ϴ� �ڵ�
		res.setContentType("text/html;charset=EUC_KR"); // �ѱ� �� ����!
		PrintWriter out = res.getWriter();

		// client from data(QueryString :: name=value)ó�� (APIȮ��)
		String clientName = req.getParameter("name");
		String clientAddr = req.getParameter("addr");

		// client form data ���
		System.out.println(clientName + ":" + clientAddr);

		out.println("<html>");	
		out.println("<head><title>GetData.java</title></head>");
		out.println("<body>");

		out.println("<h2>Get Test </h2>");
		out.println("<li> �̸� : " + clientName);
		out.println("<li> �ּ� : " + clientAddr);

		out.println("<p><p><a href='/edu/getData.html'>�ڷ�</a>");

		out.println("</body>");
		out.println("</html>");	
	}

}//end of class