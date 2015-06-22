package jp.spring.pacege;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class FormModel4 {
	@NotEmpty
	private String pass;
	@NotEmpty
	private String kakuninn;
	@NotEmpty
	private String loginID;
	@NotEmpty
	private String name;
	@NotNull
	private int id;



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getKakuninn() {
		return kakuninn;
	}
	public void setKakuninn(String kakuninn) {
		this.kakuninn = kakuninn;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
}