import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 FileName  : ServletTest.java
	ㅇ Servlet Life Cycle  이해 및 확인
	
	1. Client가 request 하면, Servlet Container 은 "스레드 생성" 후 start() 메소드 호출해서 run() 메소드 호출
	   그 후 init(), service(), 메소드 실행하고 스레드 죽음. Servlet 인스턴스는 아직 살아있음 
	2. 또 Client가 request 하면, Servlet 인스턴스는 아직 살아있으니까, 스레드 생성 run() 메소드 호출 후 service()만 실행
	3. 뭔가 수정되면 destroy() 해서 Servlet 인스턴스 소멸시키고, WAS 다시 시작됨 (reloadable이 true 일 때. 현업 - false)
*/
public class ServletTest extends HttpServlet{

	///Field
	// 각각의 method의 호출을 count 위한 변수 선언
	int initCount,serviceCount,destroyCount;

	///Method
	// Client 최초 request 시 1번 호출되는 init()  method
	public void init(){
		System.out.println("init() : "+ ++initCount);
	}
	
	//Client request 시 마다 호출되는 service() method
	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		System.out.println("service() : "+ ++serviceCount);
	}
	
	// Web Application 이 shutdown 시 호출
	public void destroy(){
		System.out.println("destroy() : "+ ++destroyCount);
	}

}//end of class