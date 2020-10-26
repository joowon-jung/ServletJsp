

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/findUser")
public class FindUser extends HttpServlet {
	
	// doGet() Overriding ==> 이렇게 하면 get 방식으로 호출되더라도 post메소드 사용 가능!
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	//doPost() Overriding
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		// client 에서 넘어온 값을 받자 
		String id = request.getParameter("id");
		
		if (id == null) { // AddUser에서 내정보보기2(세션 사용)를 눌렀을 경우에는 아무런 값도 넘어오지 않는다.
		// html을 거치지 않았으니까 get 방식으로 넘어옴 => doPost메소드만 있으면 오류나므로 doGet 만듦 => doGet 메소드를 거쳐 doPost메소드로 왔음.
		// 결론 : 아무런 값도 가지고 오지 않았으니까 id 값이 null일경우 => 세션 이용한 내정보보기!
			//==>  Session에 저장된 userVO instance 의 id 사용
			HttpSession session = request.getSession(true);
//			UserVO userVO = (UserVO) session.getAttribute("userVO");
//			id = userVO.getId();
			id = ((UserVO) session.getAttribute("userVO")).getId();
		}
		
		// DB에 접근하는 UserDAO를 이용하여 회원정보 Select
		UserDAO bean = new UserDAO(); // 인스턴스 생성 

		// UserDAO 안의 findUser 메소드 호출하여 UserVO instance 받기
		UserVO userVO = bean.findUser(id); // findUser에서 찾는 회원정보가 없으면 null 리턴되어 userVO에 null이 담김
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h2>회원정보 화면</h2>");
		
		if (userVO != null) { // findUser 에서 찾는 회원정보가 없으면 null이 리턴되니까
							  // null이 아니라는 뜻은 userVO 안에 찾는 값이 잘 들어갔다는 것
			out.println(userVO.getId() + "님 의 회원정보 <br>");
			out.println("이름 : " + userVO.getName() + "<br>");
			out.println("성별 : " + userVO.getSex() + "<br>");
			out.println("생년 : " + userVO.getBirth() + "<br>");
			out.println("학력 : " + userVO.getEdu() + "<br>");
			out.println("직업 : " + userVO.getJob() + "<br>");
			out.println("연락처 : " + userVO.getPhone_num() + "<br>");
			out.println("주소 : " + userVO.getAddress() + "<br>");
		} else {
			out.println(id+" 에 해당하는 회원정보 없음! id를 확인하세요.");
		}
		
		out.println("<p><p><a href='findUser.html'>뒤로</a>");
		out.println("<body>");
		out.println("</html>");
	}

}
