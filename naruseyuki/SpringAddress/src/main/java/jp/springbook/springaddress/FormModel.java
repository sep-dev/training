package jp.springbook.springaddress;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;


public class FormModel {

	@Length(min=1,max=10,message="名前は必須項目です！")
	public String name;
	@Length(min=1,max=20,message="住所は必須項目です！")
	public String address;
	@Length(min=1,max=11,message="電話番号は必須項目です！")
	@Pattern(regexp="^[0-9]*$",message="電話番号は半角数字で入力してください！")
	public String tel;

	public String getName() {
		return name;
	}public void setName(String name) {
		this.name = name;
	}public String getAddress() {
		return address;
	}public void setAddress(String address) {
		this.address = address;
	}public String getTel() {
		return tel;
	}public void setTel(String tel) {
		this.tel = tel;
	}

}
