package jw04;

/*
 * 	1. Client Data �� Object Modeling
 *  2. Value Object (Value Object Pattern) : Data�� ���� ��ü
 */
public class UserVO {
	
	///Field
	private String id;
	private String pwd;
	private boolean active; // �ʱⰪ : false. ȸ�������� ������ true�� �ٲ� 
	
	///Constructor
	public UserVO() {
	}

	///Method
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pwd=" + pwd + ", active=" + active + "]";
	}

}
