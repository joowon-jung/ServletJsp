package jw04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

							// 서블릿은 반드시 서블릿 인터페이스를 확장해야 한다 
public class LoginBean extends HttpServlet {

	// id, pwd를 보내는 거니까 post 방식 사용!
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("EUC_KR");
		res.setContentType("text/html;charset=EUC_KR");
		PrintWriter out = res.getWriter();
		
		//html 파일에서 클라이언트가 입력한 데이터 받아옴
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		// DB 접근. data 검색은 DbBean 이용하여 회원 유무 확인
		DbBean dbBean = new DbBean();
		// 상태값 세팅
		dbBean.setId(id);
		dbBean.setPwd(pwd);
		
		// DbBean.getUser() method : DB 접근. data 검색 비교 후 true/false return
		boolean isLogin = dbBean.getUser();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		
		out.println("<h2>Login 화면</h2>");
		
		// DBMS Data 와 Client Form Data 비교 회원 유무 판단
		if (isLogin) {
			out.println(id + "님 환영합니다.");
		} else {
			out.println("Login 실패! id, pwd를 확인하세요.");
		}
		
		out.println("<p><p><a href='/edu/jw04/loginBean.html'>뒤로</a>");
		out.println("<body>");
		out.println("</html>");
	
	}

}