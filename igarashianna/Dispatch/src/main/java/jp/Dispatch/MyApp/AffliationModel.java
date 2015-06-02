package jp.Dispatch.MyApp;

import org.hibernate.validator.constraints.NotEmpty;

public class AffliationModel {

private String affliationId;
@NotEmpty
private String affliationName;
private String affliationPostCode;
private String affliationAdd;
private String affliationTel;
public String getAffliationAdd() {
	return affliationAdd;
}
public void setAffliationAdd(String affliationAdd) {
	this.affliationAdd = affliationAdd;
}
public String getAffliationTel() {
	return affliationTel;
}
public void setAffliationTel(String affliationTel) {
	this.affliationTel = affliationTel;
}
private String affliationNearestStation;
private String affliationRemarks;

public String getAffliationId() {
	return affliationId;
}
public void setAffliationId(String affliationId) {
	this.affliationId = affliationId;
}
public String getAffliationName() {
	return affliationName;
}
public void setAffliationName(String affliationName) {
	this.affliationName = affliationName;
}
public String getAffliationPostCode() {
	return affliationPostCode;
}
public void setAffliationPostCode(String affliationPostCode) {
	this.affliationPostCode = affliationPostCode;
}

public String getAffliationNearestStation() {
	return affliationNearestStation;
}
public void setAffliationNearestStation(String affliationNearestStation) {
	this.affliationNearestStation = affliationNearestStation;
}
public String getAffliationRemarks() {
	return affliationRemarks;
}
public void setAffliationRemarks(String affliationRemarks) {
	this.affliationRemarks = affliationRemarks;
}

}
