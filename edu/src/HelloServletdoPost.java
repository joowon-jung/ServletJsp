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
	
	// 이 java 파일을 실행했을 때 doPost 메소드만 있으면 에러나는 이유 :
	// <form method>에서 get, post 방식을 기술해줬을 때는 상관이 없는데,
	// 아무것도 하지 않았을 때는 기본적으로 doGet 메소드가 실행된다.
	// 그러므로 html 문서를 만들지 않고 실행했을 때도 doGet 메소드가 실행된 것!
	//  ==> 하위(이 클래스)에 없으니까 상위로 올라갔는데 상위의 doGet 메소드안에서 에러를 발생시켜주고 있어서.
	
	// doPost 메소드를 실행시키려면 html 문서 만든 후 <form> 태그의 method를 post로 해줘야 한다 
	// 아니면 위의 방식대로 doGet 메소드 안에서 doPost 메소드 호출하거나, 아예 service 메소드 사용하면 된다 
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