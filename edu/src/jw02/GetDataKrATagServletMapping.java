package jw02;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class GetDataKrATagServletMapping extends HttpServlet{

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
		out.println("<head><title>GetDataKrATag.java</title></head>");
		out.println("<body>");

		out.println("<h2>Get Test </h2>");
		out.println("<li> 이름 : " + clientNameKo);
		out.println("<li> 주소 : " + clientAddrKo);

		out.println("<p><p><a href='/edu/jw02/getDataKrATagServletMapping.html'>뒤로</a>");

		// ==> <a> Tag 이용 QueryString 전달
		out.println("<p><a href='/edu/GetDataKrATagServletMapping?name=홍길동&addr=서울'>"+"자기자신</a>");

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