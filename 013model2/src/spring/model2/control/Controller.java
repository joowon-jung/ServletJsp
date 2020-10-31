package spring.model2.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 	- Controller ��ü�� �Ϲ���, ������ ����(Method)�� ����, �����ϴ� interface
 *  - Controller ��ü�� Control ���� �����ϴ� ������, �ٽ��� ������ ����
 *  - Servlet API �ٽ�, �߿��� ��ü HttpServletRequest / HttpServletResponse ���ڷ� ���޹���
 */
public interface Controller  {
	
	public void execute (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;

}
