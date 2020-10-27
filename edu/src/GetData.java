import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	ㅇ Get 방식으로 전송된 Client Form Data 처리
*/
public class GetData extends HttpServlet{

	// 1. Client Get 방식으로 Request : doGet() method 오버라이딩
	// 2. service() method 오버라이딩 가능 
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		// 아래의 두 라인은 servlet에서 client로 Html 전송 시 필수로 해줘야 하는 코딩
		res.setContentType("text/html;charset=EUC_KR"); // 한글 안 깨짐!
		PrintWriter out = res.getWriter();

		// client from data(QueryString :: name=value)처리 (API확인)
		String clientName = req.getParameter("name");
		String clientAddr = req.getParameter("addr");

		// client form data 출력
		System.out.println(clientName + ":" + clientAddr);

		out.println("<html>");	
		out.println("<head><title>GetData.java</title></head>");
		out.println("<body>");

		out.println("<h2>Get Test </h2>");
		out.println("<li> 이름 : " + clientName);
		out.println("<li> 주소 : " + clientAddr);

		out.println("<p><p><a href='/edu/getData.html'>뒤로</a>");

		out.println("</body>");
		out.println("</html>");	
	}

}//end of class