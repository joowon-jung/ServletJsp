

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/FindUserDataSourceSession")
public class FindUserDataSourceSession extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// AddUser.java 에서 url 로 보낸 데이터 처리 - UserVO 사용
		UserVO userVO = new UserVO();
	
		// 주소창에 직접 URL 입력한 경우와, id값을 입력하지 않은 경우 배제 (validation check)
		if (!(request.getParameter("id") == null || request.getParameter("id").equals(""))) { // 브라우저 주소창에 직접 URL 하면 null값이 들어가는 것 배제
			userVO.setActive(true);
			userVO.setId(request.getParameter("id"));
			userVO.setName(request.getParameter("name"));
			userVO.setSex(request.getParameter("sex"));
			userVO.setBirth(request.getParameter("birth"));
			userVO.setEdu(request.getParameter("edu"));
			userVO.setJob(request.getParameter("job"));
			userVO.setPhone_num(request.getParameter("phone_num"));
			userVO.setAddress(request.getParameter("address"));
		}
		
		// session 유무 확인 :: 새로운 HttpSession 생성 OR 기존 HttpSession GET
		HttpSession session = request.getSession(true);
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h2>Select 화면</h2>");
		
		if (userVO != null && userVO.isActive()) {
			session.setAttribute("userVO", userVO); // 세션에 유저 정보 저장
			System.out.println("UNIQUE한 SESSION ID는 : " + session.getId()); // id값 확인하기 위함 
			out.println(session.getAttribute("userVO"));
											// .toString() 이 생략된 구조!
		} else {
			out.println("Login 실패! id, pwd를 확인하세요.");
		}
		
	}

}
