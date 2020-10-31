package spring.model2.control;

import spring.model2.service.user.view.HomeController;
import spring.model2.service.user.view.LogonActionController;
import spring.model2.service.user.view.LogonController;

/*
 * 	- Client Request(요구사항, command)를 처리, Controller 생성하여 리턴
 */
public class ControllerMapping {

	private static ControllerMapping controllerMapping;

	// 생성자 private - 인스턴스 생성 불가!
	private ControllerMapping() {
	}

	// static 메소드를 통해 인스턴스 생성하게 함 => 한번 인스턴스 생성 후 계속 사용한단 뜻 
	public static ControllerMapping getInstance() {
		if (controllerMapping == null) {
			controllerMapping = new ControllerMapping();
		}
		return controllerMapping;
	}

	// Client Request Page(Action Page)를 받아서 Request 처리 후 Controller 생성 & 리턴 
	public Controller getController(String actionPage) {

		System.out.println("[ ControllerMapping.getController() start...]");

		Controller controller = null;

		if (actionPage.equals("logon")) {
			controller = new LogonController();
		} else if (actionPage.equals("logonAction")) {
			controller = new LogonActionController();
		} else if (actionPage.equals("home")) {
			controller = new HomeController();
		}

		// ==> 추가사항이 발생한다면 아래와 같이 추가하자!
		/*
		 else if (actionPage.equals("member")) {
			controller = new MemberController(); / new MemberAction();
		}
		 * 
		 */
		System.out.println("[ ControllerMapping.getController() end...]");

		return controller;
	}

}
