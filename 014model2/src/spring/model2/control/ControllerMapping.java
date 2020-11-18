package spring.model2.control;

import spring.model2.service.user.view.HomeController;
import spring.model2.service.user.view.LogonActionController;
import spring.model2.service.user.view.LogonController;

/*
 * 	- Client Request(�䱸����, command)�� ó��, Controller �����Ͽ� ����
 */
public class ControllerMapping {

	private static ControllerMapping controllerMapping;

	// ������ private - �ν��Ͻ� ���� �Ұ�!
	private ControllerMapping() {
	}

	// static �޼ҵ带 ���� �ν��Ͻ� �����ϰ� �� => �ѹ� �ν��Ͻ� ���� �� ��� ����Ѵ� �� 
	public static ControllerMapping getInstance() {
		if (controllerMapping == null) {
			controllerMapping = new ControllerMapping();
		}
		return controllerMapping;
	}

	// Client Request Page(Action Page)�� �޾Ƽ� Request ó�� �� Controller ���� & ���� 
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

		// ==> �߰������� �߻��Ѵٸ� �Ʒ��� ���� �߰�����!
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
