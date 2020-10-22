import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/*
	ㅇ Get 방식으로 전송된 Client Form Data 처리
*/
public class GetDataKr extends HttpServlet{

	// 1. Client Get 방식으로 Request : doGet() method 오버라이딩
	// 2. service() method 오버라이딩
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{

		// 아래의 두 라인은 servlet에서 client로 Html 전송 시 필수로 해줘야 하는 코딩
		res.setContentType("text/html;charset=EUC_KR"); // 한글 안 깨짐!
		PrintWriter out = res.getWriter();

		// client from data(QueryString :: name=value)처리 (API확인)
		String clientName = req.getParameter("name");
		String clientAddr = req.getParameter("addr");

		// 16진수로 인코딩된 client form data를 한글로 디코딩
		String clientNameKo = this.convertKo(clientName);
		String clientAddrKo = convertKo(clientAddr);

		// client form data 출력
		System.out.println(clientNameKo + ":" + clientAddrKo);

		out.println("<html>");	
		out.println("<head><title>GetDataKr.java</title></head>");
		out.println("<body>");

		out.println("<h2>Get Test Kr </h2>");
		out.println("<li> 이름 : " + clientNameKo);
		out.println("<li> 주소 : " + clientAddrKo);

		out.println("<p><p><a href='/edu/getDataKr.html'>뒤로</a>");

		out.println("</body>");
		out.println("</html>");	
	}

	/// Method
	private String convertKo(String paramValue) {
		String convertParamValue = null;

		try{
			// ==> API 확인
			byte[] b = paramValue.getBytes("8859_1");
			convertParamValue = new String(b, "EUC_KR");
		}
		catch (UnsupportedEncodingException uee) {
			System.out.println("한글 변환중 Exception 발생");
			uee.printStackTrace();
		}
		return convertParamValue;
	}
}//end of class