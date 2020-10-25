

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/findUser")
public class FindUser extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// client 에서 넘어온 값을 받자 
		String id = request.getParameter("id");
		
		// DB에 접근하는 UserDAO를 이용하여 회원정보 Select
		UserDAO bean = new UserDAO(); // 인스턴스 생성 

		// UserDAO 안의 findUser 메소드 호출하여 UserVO 리턴 
		UserVO userVO = bean.findUser(id);
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h2>Select 화면</h2>");
		
		if (userVO.isActive()) { // true로 언제 바뀜? UserDAO 에서 setActive(true)하는 부분 있음
			out.println(userVO.getId() + "님 의 회원정보 <br>");
			out.println("이름 : " + userVO.getName() + "<br>");
			out.println("성별 : " + userVO.getSex() + "<br>");
			out.println("생년 : " + userVO.getBirth() + "<br>");
			out.println("학력 : " + userVO.getEdu() + "<br>");
			out.println("직업 : " + userVO.getJob() + "<br>");
			out.println("연락처 : " + userVO.getPhone_num() + "<br>");
			out.println("주소 : " + userVO.getAddress() + "<br>");
		} else {
			out.println("회원정보 없음! id를 확인하세요.");
		}
		
		out.println("<p><p><a href='findUser.html'>뒤로</a>");
		out.println("<body>");
		out.println("</html>");
	}

}
