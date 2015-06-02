package jp.sample.form;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ShainForm {

	@Id
	private int id;

	@NotEmpty
	private String name;
	private String email;

	@Length(min=0,max=7)
	@Pattern(regexp="|[0-9]+")
	private String postcode;
	private String add;
	@Length(min=0,max=11)
	@Pattern(regexp="|[0-9]+")
	private String tel;
	@Length(min=0,max=11)
	@Pattern(regexp="|[0-9]+")
	private String mobiletel;
	private String neareststation;

	@NotNull
	@Range(min=1)
	private int affiliationid;
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

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
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

	public String getMobiletel(){
		return mobiletel;
	}

	public void setMobiletel(String mobiletel){
		this.mobiletel = mobiletel;
	}

	public String getNeareststation(){
		return neareststation;
	}

	public void setNeareststation(String neareststation){
		this.neareststation = neareststation;
	}

	public int getAffiliationid(){
		return affiliationid;
	}

	public void setAffiliationid(int affiliationid){
		this.affiliationid = affiliationid;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

}