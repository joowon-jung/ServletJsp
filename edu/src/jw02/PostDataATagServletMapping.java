package jw02;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	ㅇ Post 방식으로 전송된 Client Form Data 처리
*/
public class PostDataATagServletMapping extends HttpServlet{

	// 1. Client Post 방식으로 Request : doPost() method 오버라이딩
	// 2. service() method 오버라이딩
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		// 16진수로 인코딩된 client form data를 한글로 디코딩
		req.setCharacterEncoding("EUC_KR"); // Post만 적용됨! why? body 에 내용(key&value)이 들어가니까 !

		// 아래의 두 라인은 servlet에서 client로 Html 전송 시 필수로 해줘야 하는 코딩
		res.setContentType("text/html;charset=EUC_KR"); // 한글 안 깨짐!
		PrintWriter out = res.getWriter();

		// client from data(QueryString :: name=value)처리 (API확인)
		String clientName = req.getParameter("name");
		String clientAddr = req.getParameter("addr");

		// client form data 출력
		System.out.println(clientName + ":" + clientAddr);

		out.println("<html>");	
		out.println("<head><title>PostDataATagServletMapping.java</title></head>");
		out.println("<body>");

		out.println("<h2>Get Test </h2>");
		out.println("<li> 이름 : " + clientName);
		out.println("<li> 주소 : " + clientAddr);

		// ==> <a> Tag 이용 QueryString 전달
		out.println("<p><a href='/edu/PostDataATagServletMapping?name=홍길동&addr=서울'>"+"자기자신</a>");
							// get 방식으로 전달하고 있으니까 자기자신을 누르면 오류가 나는 것 

		out.println("</body>");
		out.println("</html>");	
	}

}//end of class