package spring.model2.control;

/*
 * 		Controller �� ���� ��
 * 		- Model �� View ���� : ObjectScope ���
 * 		- Navigation : forward / sendRedirect �̿�
 * 		�� ������ ������ �κи� Modeling �� Bean 
 */
public class ModelAndView {

	private String viewName; // navigation �� jsp ���� �̸�
	private String modelName; // key (������ �Ѱܾ� �ϴϱ� key & value ��������!)
	private Object modelObject; // value (�ѱ� ������)

	public ModelAndView() {
		// TODO Auto-generated constructor stub
	}

	public ModelAndView(String viewName) {
		this.viewName = viewName;
	}

	public ModelAndView(String viewName, String modelName, Object modelObject) {
		this.viewName = viewName;
		this.modelName = modelName;
		this.modelObject = modelObject;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Object getModelObject() {
		return modelObject;
	}

	public void setModelObject(Object modelObject) {
		this.modelObject = modelObject;
	}

}
