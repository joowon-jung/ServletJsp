import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	ㅇ Post 방식으로 전송된 Client Form Data 처리
*/
public class PostData extends HttpServlet{

	// 1. Client Post 방식으로 Request : doPost() method 오버라이딩
	// 2. service() method 오버라이딩
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		// res.setCharacterEncoding("~~") ==> API 확인

		// 16진수로 인코딩된 client form data를 한글로 디코딩
		req.setCharacterEncoding("EUC_KR");
		// POST 방식만 이렇게 가능! 인코딩 해주는 영역은 body 영역인데,
		// post 방식은 body 안에 name=value 값들이 담겨져서 가져와지기 때문에!
		// (get 방식은 url에 담겨져서 보내져서 불가능..) 

		// 아래의 두 라인은 servlet에서 client로 Html 전송 시 필수로 해줘야 하는 코딩
		res.setContentType("text/html;charset=EUC_KR"); // 한글 안 깨짐!
		PrintWriter out = res.getWriter();

		// client from data(QueryString :: name=value)처리 (API확인)
		String clientName = req.getParameter("name");
		String clientAddr = req.getParameter("addr");

		// client form data 출력
		System.out.println(clientName + ":" + clientAddr);

		out.println("<html>");	
		out.println("<head><title>PostData.java</title></head>");
		out.println("<body>");

		out.println("<h2>Post Test </h2>");
		out.println("<li> 이름 : " + clientName);
		out.println("<li> 주소 : " + clientAddr);

		out.println("<p><p><a href='/edu/postData.html'>뒤로</a>");

		out.println("</body>");
		out.println("</html>");	
	}

}//end of class