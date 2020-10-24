import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	�� Servlet Life Cycle  ���� �� ���� ����
	�� Servlet Hierarchy / Servlet API ����
*/
public class HelloServletService extends HttpServlet{

	/// init() ==> �� ���������� ���°� ������ ���� �ʿ����� �ʾƼ� ����
	
	// Client request �� ���� ȣ��Ǵ� service() method �������̵�
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
				// req : �䱸���� get ( Ŭ���̾�Ʈ�� �䱸����, ������� ex.ip �ּ� ��... )
				// res : html�� set ( Ŭ���̾�Ʈ�� ���� ���� )
		
		System.out.println("Servlet service() ����");
		System.out.println("��û�ϴ� Ŭ���̾�Ʈ IP �ּ� : "+ req.getRemoteAddr());

		// �ѱ� ó��
		//res.setContentType("text/html"); // Html ���� ����. �ѱ� ����!
		res.setContentType("text/html;charset=EUC_KR"); // �ѱ� �� ����!

		// 1. client���� Html Text ������ ���� Stream ����
		// OutputStream outputStream = res.getOutputStream(); // �� �غ�, ���� ����
		// Writer writer = new OutputStreamWriter(outputStream); // ���� �� ������ Writer�� �ٲ��� => read()�� �� ���ھ� �� ��� �ϴ� ����
		// PrintWriter out = new PrintWriter(writer); // readLine() �ؼ� �� �ٷ� �о�ֱ� ����

		// 2. PrintWriter �ν��Ͻ� ������ Encapsulation �� API ����Ͽ� Stream ����
		PrintWriter out = res.getWriter(); // getWriter() : ���� ������ �� ����� ������ 

		// �������� html ��� 
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

		System.out.println("Servlet service() ����!");
	}
	
	// Web Application �� shutdown �� ȣ��
	/// destroy() ==> �ʿ����� �ʾ� ����

}//end of class