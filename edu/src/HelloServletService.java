import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	ㅇ Servlet Life Cycle  이해 및 적용 구현
	ㅇ Servlet Hierarchy / Servlet API 이해
*/
public class HelloServletService extends HttpServlet{

	/// init() ==> 이 예제에서는 상태값 세팅할 것이 필요하지 않아서 생략
	
	// Client request 시 마다 호출되는 service() method 오버라이딩
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
				// req : 요구사항 get ( 클라이언트의 요구사항, 모든정보 ex.ip 주소 등... )
				// res : html에 set ( 클라이언트에 보낼 정보 )
		
		System.out.println("Servlet service() 시작");
		System.out.println("요청하는 클라이언트 IP 주소 : "+ req.getRemoteAddr());

		// 한글 처리
		//res.setContentType("text/html"); // Html 보낼 것임. 한글 깨짐!
		res.setContentType("text/html;charset=EUC_KR"); // 한글 안 깨짐!

		// 1. client에게 Html Text 전송을 위한 Stream 생성
		// OutputStream outputStream = res.getOutputStream(); // 쏠 준비, 문자 깨짐
		// Writer writer = new OutputStreamWriter(outputStream); // 문자 안 깨지게 Writer로 바꿔줌 => read()로 한 글자씩 해 줘야 하니 귀찮
		// PrintWriter out = new PrintWriter(writer); // readLine() 해서 한 줄로 읽어내주기 위함

		// 2. PrintWriter 인스턴스 생성을 Encapsulation 한 API 사용하여 Stream 생성
		PrintWriter out = res.getWriter(); // getWriter() : 위의 과정을 다 만들어 놓았음 

		// 브라우저에 html 쏘기 
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

		System.out.println("Servlet service() 종료!");
	}
	
	// Web Application 이 shutdown 시 호출
	/// destroy() ==> 필요하지 않아 생략

}//end of class