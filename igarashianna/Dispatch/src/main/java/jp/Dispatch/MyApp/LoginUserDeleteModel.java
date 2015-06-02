package jp.Dispatch.MyApp;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginUserDeleteModel {
	private String loginUserID;
	private String loginUserPassCheck;
	public String getLoginUserID() {
		return loginUserID;
	}
	public void setLoginUserID(String loginUserID) {
		this.loginUserID = loginUserID;
	}
	public String getLoginUserPassCheck() {
		return loginUserPassCheck;
	}
	public void setLoginUserPassCheck(String loginUserPassCheck) {
		this.loginUserPassCheck = loginUserPassCheck;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public String getLoginUserPass() {
		return loginUserPass;
	}
	public void setLoginUserPass(String loginUserPass) {
		this.loginUserPass = loginUserPass;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	private String loginUserName;
	@NotEmpty
	private String loginUserPass;
	private String loginUser;
}
