package spring.model2.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 	- Controller 객체의 일반적, 공통적 행위(Method)을 정의, 규정하는 interface
 *  - Controller 객체는 Control 에서 수행하는 실질적, 핵심적 역할을 수행
 *  - Servlet API 핵심, 중요한 객체 HttpServletRequest / HttpServletResponse 인자로 전달받음
 */
public interface Controller  {
	
	public void execute (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}
