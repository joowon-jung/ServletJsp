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
		
		if (modelAndView.getModelName() != null) { // 아무 값도 넘기지 않고 forward 할 수도 있으니 체크해주는 것 
			request.setAttribute( modelAndView.getModelName(), modelAndView.getModelObject() ); // request 스콥에 담음 
		}
		
		request.getRequestDispatcher(modelAndView.getViewName()).forward(request, response); // 경로 입력해 준 파일로 forward 해줌 
		
		System.out.println("[ ViewResolver.forward() end...... ]");
	}

}
