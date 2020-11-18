package spring.model2.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	
	public ViewResolver() {
		// TODO Auto-generated constructor stub
	}
	
	public void forward (HttpServletRequest request,
							HttpServletResponse response,
							ModelAndView modelAndView)
										throws ServletException, IOException {
		
		System.out.println("[ ViewResolver.forward() start...... ]");
		
		if (modelAndView.getModelName() != null) { // �ƹ� ���� �ѱ��� �ʰ� forward �� ���� ������ üũ���ִ� �� 
			request.setAttribute( modelAndView.getModelName(), modelAndView.getModelObject() ); // request ���߿� ���� 
		}
		
		request.getRequestDispatcher(modelAndView.getViewName()).forward(request, response); // ��� �Է��� �� ���Ϸ� forward ���� 
		
		System.out.println("[ ViewResolver.forward() end...... ]");
	}

}
