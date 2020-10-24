package jw02;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	�� Post ������� ���۵� Client Form Data ó��
*/
public class PostDataATagServletMapping extends HttpServlet{

	// 1. Client Post ������� Request : doPost() method �������̵�
	// 2. service() method �������̵�
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		// 16������ ���ڵ��� client form data�� �ѱ۷� ���ڵ�
		req.setCharacterEncoding("EUC_KR"); // Post�� �����! why? body �� ����(key&value)�� ���ϱ� !

		// �Ʒ��� �� ������ servlet���� client�� Html ���� �� �ʼ��� ����� �ϴ� �ڵ�
		res.setContentType("text/html;charset=EUC_KR"); // �ѱ� �� ����!
		PrintWriter out = res.getWriter();

		// client from data(QueryString :: name=value)ó�� (APIȮ��)
		String clientName = req.getParameter("name");
		String clientAddr = req.getParameter("addr");

		// client form data ���
		System.out.println(clientName + ":" + clientAddr);

		out.println("<html>");	
		out.println("<head><title>PostDataATagServletMapping.java</title></head>");
		out.println("<body>");

		out.println("<h2>Get Test </h2>");
		out.println("<li> �̸� : " + clientName);
		out.println("<li> �ּ� : " + clientAddr);

		// ==> <a> Tag �̿� QueryString ����
		out.println("<p><a href='/edu/PostDataATagServletMapping?name=ȫ�浿&addr=����'>"+"�ڱ��ڽ�</a>");
							// get ������� �����ϰ� �����ϱ� �ڱ��ڽ��� ������ ������ ���� �� 

		out.println("</body>");
		out.println("</html>");	
	}

}//end of class