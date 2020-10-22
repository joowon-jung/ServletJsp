import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	ㅇ Servlet Life Cycle  이해 및 적용 구현
	ㅇ Servlet Hierarchy / Servlet API 이해
*/
public class HelloServletdoPost extends HttpServlet{

	/// init() ==> 이 예제에서는 상태값 세팅할 것이 필요하지 않아서 생략
	
	/// service() ==> service() 메소드 오버라이딩 하지 않고, doGet(), doPost() 오버라이딩 할 거니까 생략
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		this.doPost(req,res);
	}

	// doPost() method Overriding
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		System.out.println("servlet doPost() 시작");
		System.out.println("요청하는 클라이언트 IP 주소 : "+ req.getRemoteAddr());

		// 한글 처리
		res.setContentType("text/html;charset=EUC_KR"); // 한글 안 깨짐!

		// PrintWriter 인스턴스 생성을 Encapsulation 한 API 사용하여 Stream 생성
		PrintWriter out = res.getWriter();

		out.println("<html>");	
		out.println("<head><title>hello Servlet</title></head>");
		out.println("<body>");
		out.println("English : HelloServlet");
		out.println("<p>");
		out.println("Korea : 헬로 서블릿");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();

		System.out.println("Servlet doPost() 종료!");
	}
	
	// Web Application 이 shutdown 시 호출
	/// destroy() ==> 필요하지 않아 생략

}//end of class