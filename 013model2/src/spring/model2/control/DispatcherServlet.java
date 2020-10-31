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
		
		String actionPage = this.getURI(req.getRequestURI()); // URL �Ľ��� ���� ��
		System.out.println(":: URI ? => : " + req.getRequestURI()); 
		System.out.println(":: Client�� �䱸������ ? => " + actionPage);
		
		req.setCharacterEncoding("euc-kr");
		
		Controller controller = null;
		
		ControllerMapping cf = ControllerMapping.getInstance(); // �ν��Ͻ� �޾ƿ� 
		controller = cf.getController(actionPage); // �˸´� ��Ʈ�ѷ� �޾ƿ� 
		
		controller.execute(req, res);
		
		System.out.println("\n [ DispatcherServlet.service() end...]");
		
	}

	// Client �� �䱸���� �Ǵ� ==> requestURI = "/~~.do" �����̹Ƿ�...
	private String getURI(String requestURI) { // ex) logonAction.do �� logonAction�� ������ �Ľ�!!

		int start = requestURI.lastIndexOf('/') + 1;
		int end = requestURI.lastIndexOf(".do");
		String actionPage = requestURI.substring(start, end);
		return actionPage;
	}

}
