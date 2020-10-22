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
 * 	서버에 필요한 정보 HttpSession 이용 저장 
 */
//@WebServlet("/SessionUseCookieTwo")
public class SessionUseCookieTwo extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("EUC_KR");
		res.setContentType("text/html;charset=EUC_KR");
		PrintWriter out = res.getWriter();

		// ==> 아래의 Cookie 정보 확인 의미
		// ==> HttpSession 이 Cookie를 사용유무 확인
		// ==> Cookie에 HttpSession 을 구분, GET 할 수 있는
		// ==> unique 한 SESSION ID 존재 유무 확인
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				System.out.print("\n Cookie에 저장된 정보 : ");
				System.out.print(cookies[i].getName() + " : " + cookies[i].getValue());
				System.out.print("\n");
			}
		}

		// 두 가지 경우 이해
		// 1. HttpSession 객체가 없을경우
		// 2. HttpSession 객체가 있을경우
		HttpSession session = req.getSession(false);
		// ==> 1의 경우 새로운 HttpSession 객체 없이 진행 (API 확인)
		// ==> 2의 경우 기존 HttpSession 객체 GET (API 확인)

		out.println("<html><head></head>");
		out.println("<body>");
		out.println("<center><h2>SessionUseCookieTwo</h2></center>");

		// session이 null 의미 : 기존에 생성, 사용중인 HttpSession 객체가 없다
		// session이 null 이 아니라는 의미 : 기존에 생성된 HttpSession 객체가 있다
		if (session != null) {
			out.println("<hr> SESSIONID = " + session.getId() + "<hr>");
			String name = (String) session.getAttribute("name");
			out.println("이름 : " + name);
		} else {
			out.println("처음이십니다.");
		}
		out.println("</body></html>");
	}
}
