package jw02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * FileName : MultiCheck01.java
 */
//@WebServlet("/MultiCheck03")
public class MultiCheck03 extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		req.setCharacterEncoding("EUC_KR");		
		res.setContentType("text/html;charset=KSC5601");
		PrintWriter out = res.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<boby><center><h2>Client에서 전송된 내용</h2></center><p>");

		// 1. QueryString(name=value) 의 name 을 return 하는 getParameterNames() 
		// 2. getParameter("name") 의 name  하드코딩 않음
		Enumeration<String> en = req.getParameterNames();
		//java.util 패키지 참고 
		
		while(en.hasMoreElements()){ // hasMoreElements() : 데이터 꺼냄 
			
			String name = en.nextElement(); // nextElement() : 데이터 반환 
			String value = req.getParameter(name);
			
			if (name.equals("sw")) {
				
				String[] sw = req.getParameterValues("sw"); // 여러개 꺼내옴 
				
				for (int i = 0; i < sw.length; i++) {
					out.println("<li>" + name + " : " + sw[i]);
				}
				
			} else {
				out.println("<li> "+name+" : "+value);
			}
		}
        
//		String[] sw = req.getParameterValues("sw");
//
//        for(int i=0;i<sw.length;i++){
//            out.println("<li>"+sw[i]);
//        }
        
		out.println("</body>");
		out.println("</html>");
	}

}//end of class