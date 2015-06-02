package FormModel;

import org.hibernate.validator.constraints.NotEmpty;

public class UserModel {

	public String usearch;
	public String loginuserid;

	@NotEmpty
	public String loginusername;
	public String loginuser;
	public String loginuserpass1;
	public String loginuserpass2;

	public String getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}
	public String getLoginusername() {
		return loginusername;
	}
	public void setLoginusername(String loginusername) {
		this.loginusername = loginusername;
	}
	public String getLoginuser() {
		return loginuser;
	}
	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}
	public String getLoginuserpass1() {
		return loginuserpass1;
	}
	public void setLoginuserpass1(String loginuserpass1) {
		this.loginuserpass1 = loginuserpass1;
	}
	public String getLoginuserpass2() {
		return loginuserpass2;
	}
	public void setLoginuserpass2(String loginuserpass2) {
		this.loginuserpass2 = loginuserpass2;
	}
	public String getUsearch() {
		return usearch;
	}
	public void setUsearch(String usearch) {
		this.usearch = usearch;
	}

}
