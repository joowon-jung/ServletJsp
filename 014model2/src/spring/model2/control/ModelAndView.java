package spring.model2.control;

/*
 * 		Controller 의 역할 중
 * 		- Model 과 View 연결 : ObjectScope 사용
 * 		- Navigation : forward / sendRedirect 이용
 * 		두 역할의 정적인 부분만 Modeling 한 Bean 
 */
public class ModelAndView {

	private String viewName; // navigation 할 jsp 파일 이름
	private String modelName; // key (데이터 넘겨야 하니까 key & value 형식으로!)
	private Object modelObject; // value (넘길 데이터)

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
