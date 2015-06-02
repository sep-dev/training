package jp.sample.form;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {
	@NotEmpty
	private String username;

	@NotEmpty
	private String user;

	@NotEmpty
	private String pass1;

	@NotEmpty
	private String pass2;


	public String getUser(){
		return user;
	}

	public void setUser(String user){
		this.user = user;
	}

	public String getPass1(){
		return pass1;
	}

	public void setPass1(String pass1){
		this.pass1 = pass1;
	}

	public String getPass2(){
		return pass2;
	}

	public void setPass2(String pass2){
		this.pass2 = pass2;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

}
