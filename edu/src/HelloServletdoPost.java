import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	�� Servlet Life Cycle  ���� �� ���� ����
	�� Servlet Hierarchy / Servlet API ����
*/
public class HelloServletdoPost extends HttpServlet{

	/// init() ==> �� ���������� ���°� ������ ���� �ʿ����� �ʾƼ� ����
	
	/// service() ==> service() �޼ҵ� �������̵� ���� �ʰ�, doGet(), doPost() �������̵� �� �Ŵϱ� ����
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		this.doPost(req,res);
	}

	// doPost() method Overriding
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		System.out.println("servlet doPost() ����");
		System.out.println("��û�ϴ� Ŭ���̾�Ʈ IP �ּ� : "+ req.getRemoteAddr());

		// �ѱ� ó��
		res.setContentType("text/html;charset=EUC_KR"); // �ѱ� �� ����!

		// PrintWriter �ν��Ͻ� ������ Encapsulation �� API ����Ͽ� Stream ����
		PrintWriter out = res.getWriter();

		out.println("<html>");	
		out.println("<head><title>hello Servlet</title></head>");
		out.println("<body>");
		out.println("English : HelloServlet");
		out.println("<p>");
		out.println("Korea : ��� ����");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();

		System.out.println("Servlet doPost() ����!");
	}
	
	// Web Application �� shutdown �� ȣ��
	/// destroy() ==> �ʿ����� �ʾ� ����

}//end of class