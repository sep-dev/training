package jp.springbook.springaddress;

import javax.validation.constraints.Pattern;

public class FormModelUpdate {

	private String name;
	private String address;
	@Pattern(regexp="[0-9]+")
	private String tel;
	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
