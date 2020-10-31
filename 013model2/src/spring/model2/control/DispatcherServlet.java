package spring.model2.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("\n [ DispatcherServlet.service() start...]");
		
		String actionPage = this.getURI(req.getRequestURI()); // URL 파싱한 값이 들어감
		System.out.println(":: URI ? => : " + req.getRequestURI()); 
		System.out.println(":: Client의 요구사항은 ? => " + actionPage);
		
		req.setCharacterEncoding("euc-kr");
		
		Controller controller = null;
		
		ControllerMapping cf = ControllerMapping.getInstance(); // 인스턴스 받아옴 
		controller = cf.getController(actionPage); // 알맞는 컨트롤러 받아옴 
		
		controller.execute(req, res);
		
		System.out.println("\n [ DispatcherServlet.service() end...]");
		
	}

	// Client 의 요구사항 판단 ==> requestURI = "/~~.do" 형식이므로...
	private String getURI(String requestURI) { // ex) logonAction.do 면 logonAction만 나오게 파싱!!

		int start = requestURI.lastIndexOf('/') + 1;
		int end = requestURI.lastIndexOf(".do");
		String actionPage = requestURI.substring(start, end);
		return actionPage;
	}

}
