package jp.springbook.springaddress;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


public class FormModel {

	@NotNull
	@Length(min=1,max=10)
	private String name;
	@NotNull
	@Length(min=1,max=20)
	private String address;
	@NotNull
	@Length(min=1,max=11)
	@Pattern(regexp="[0-9]+")
	private String tel;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
