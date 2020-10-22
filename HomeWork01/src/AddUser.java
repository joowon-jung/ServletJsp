

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String edu = request.getParameter("edu");
		String job = request.getParameter("job");
		String pnum1 = request.getParameter("pnum1");
		String pnum2 = request.getParameter("pnum2");
		String pnum3 = request.getParameter("pnum3");
		String address = request.getParameter("address");
		
		UserVO userVO = new UserVO();
		userVO.setName(name);
		userVO.setSex(sex);
		userVO.setBirth(birth);
		userVO.setEdu(edu);
		userVO.setJob(job);
		userVO.setPhone_num(pnum1+pnum2+pnum3);
		userVO.setAddress(address);
		
		UserDAO userDataSourceDao = new UserDAO();
		userDataSourceDao.addUser(userVO);
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		
		out.println("<h2>회원가입 화면</h2>");
		
		if (userVO.isActive()) {
			out.println(name+"님 회원가입을 축하합니다.");
		}else {
			out.println("회원정보를 다시 확인해주세요.");
		}
		out.println("<p><p><a href='/HomeWork01/addUser.html'>뒤로</a>");
		out.println("<br><a href='/HomeWork01/addUser.html'>내 정보 보기</a>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
