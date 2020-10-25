import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	
	//doGet() Overriding
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
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String birth = request.getParameter("birth");
		String edu = request.getParameter("edu");
		String job = request.getParameter("job");
		String pnum1 = request.getParameter("pnum1");
		String pnum2 = request.getParameter("pnum2");
		String pnum3 = request.getParameter("pnum3");
		String address = request.getParameter("address");
		
		//Client에 받은 data로 UserVO instance 생성 및 값 세팅 
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO.setName(name);
		userVO.setSex(sex);
		userVO.setBirth(birth);
		userVO.setEdu(edu);
		userVO.setJob(job);
		userVO.setPhone_num(pnum1+pnum2+pnum3);
		userVO.setAddress(address);
		
		//DB에 접근하는 UserDAO를 이용하여 회원정보 Insert
		UserDAO bean = new UserDAO();
		bean.addUser(userVO);
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		
		out.println("<h2>회원가입 화면</h2>");
		
		// insert 유무에 따라 
		if (userVO.isActive()) {
			out.println(userVO.getId()+"님 회원가입을 축하합니다.");
			out.println("<br><a href='/HomeWork01/findUser.html'>내 정보 보기 1 (html 거쳐서) </a>");
			out.println("<br><a href='FindUserDataSourceSession?id="+userVO.getId()+"&name="+userVO.getName()+""
					+ "&sex="+userVO.getSex()+"&birth="+userVO.getBirth()+"&edu="+userVO.getEdu()+""
							+ "&job="+userVO.getJob()+"&phone_num="+userVO.getPhone_num()+""
									+ "&address="+userVO.getAddress()+"'>내 정보 보기 2 (세션 사용) </a>");
		}else {
			out.println("회원정보를 다시 확인해주세요.");
		}
		out.println("<p><p><a href='/HomeWork01/addUser.html'>뒤로</a>");
		
		out.println("</body>");
		out.println("</html>");
		
	}

}
