
/*
 *  1. Client의 Data를 Object Modeling한 Normal class
 *  2. Data만 갖는 객체로 ==> Value Object라 한다.  (Value Object Pattern)
 */
public class UserVO {

	private String name;
	private String sex;
	private String birth;
	private String edu;
	private String job;
	private String phone_num;
	private String address;
	private boolean active;
	
	public UserVO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "UserVO [name=" + name + ", sex=" + sex + ", birth=" + birth + ", edu=" + edu + ", job=" + job
				+ ", phone_num=" + phone_num + ", address=" + address + ", active=" + active + "]";
	}

	
}
