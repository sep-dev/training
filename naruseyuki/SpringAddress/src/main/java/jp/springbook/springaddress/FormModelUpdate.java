package jp.springbook.springaddress;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class FormModelUpdate {

	@Length(min=1,max=10,message="名前は必須項目です！")
	private String name;
	@Length(min=1,max=20,message="住所は必須項目です！")
	private String address;
	@Length(min=1,max=11,message="電話番号は必須項目です！")
	@Pattern(regexp="^[0-9]*$",message="電話番号は半角数字で入力してください！")
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
