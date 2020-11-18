package spring.model2.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  DispatcherServlet 은 오로지 단일인입점 역할만 하고,
 *  그 이외의 모든 역할을 다른 Bean 으로 위임하고 싶음 ! 
 */
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
		
		//============ 변경된 부분 =================
		//controller.execute(req, res);
		ModelAndView modelAndView = controller.execute(req, res); // 알맞는 컨트롤러의 execute 메소드를 실행하면 modelAndView 를 반환하고 있음 
		new ViewResolver().forward(req, res, modelAndView); // ViewResolver 하는 일 : 데이터 심고 걔를 가지고 navigation 하는 것 
		
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
