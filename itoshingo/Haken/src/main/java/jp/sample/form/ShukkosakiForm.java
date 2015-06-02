package jp.sample.form;

import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


public class ShukkosakiForm {

	@Id
	private int id;

	@NotEmpty
	private String name;


	@Length(min=0,max=7)
	@Pattern(regexp="|[0-9]+")
	private String postcode;

	private String add;

	@Length(min=0,max=11)
	@Pattern(regexp="|[0-9]+")
	private String tel;

	private String neareststation;

	private String remarks;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPostcode(){
		return postcode;
	}

	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getAdd(){
		return add;
	}

	public void setAdd(String add){
		this.add = add;
	}

	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}

	public String getNeareststation(){
		return neareststation;
	}

	public void setNeareststation(String neareststation){
		this.neareststation = neareststation;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
}
