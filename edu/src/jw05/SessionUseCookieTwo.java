package jw05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 	������ �ʿ��� ���� HttpSession �̿� ���� 
 */
//@WebServlet("/SessionUseCookieTwo")
public class SessionUseCookieTwo extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("EUC_KR");
		res.setContentType("text/html;charset=EUC_KR");
		PrintWriter out = res.getWriter();

		// ==> �Ʒ��� Cookie ���� Ȯ�� �ǹ�
		// ==> HttpSession �� Cookie�� ������� Ȯ��
		// ==> Cookie�� HttpSession �� ����, GET �� �� �ִ�
		// ==> unique �� SESSION ID ���� ���� Ȯ��
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				System.out.print("\n Cookie�� ����� ���� : ");
				System.out.print(cookies[i].getName() + " : " + cookies[i].getValue());
				System.out.print("\n");
			}
		}

		// �� ���� ��� ����
		// 1. HttpSession ��ü�� �������
		// 2. HttpSession ��ü�� �������
		HttpSession session = req.getSession(false);
		// ==> 1�� ��� ���ο� HttpSession ��ü ���� ���� (API Ȯ��)
		// ==> 2�� ��� ���� HttpSession ��ü GET (API Ȯ��)

		out.println("<html><head></head>");
		out.println("<body>");
		out.println("<center><h2>SessionUseCookieTwo</h2></center>");

		// session�� null �ǹ� : ������ ����, ������� HttpSession ��ü�� ����
		// session�� null �� �ƴ϶�� �ǹ� : ������ ������ HttpSession ��ü�� �ִ�
		if (session != null) {
			out.println("<hr> SESSIONID = " + session.getId() + "<hr>");
			String name = (String) session.getAttribute("name");
			out.println("�̸� : " + name);
		} else {
			out.println("ó���̽ʴϴ�.");
		}
		out.println("</body></html>");
	}
}
