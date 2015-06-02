package jp.Dispatch.MyApp;

import org.hibernate.validator.constraints.NotEmpty;

public class StaffManUpdateModel {
	private String staffId;
	@NotEmpty
	private String staffName;
	private String staffMail;
	private String staffPostCode;
	private String staffAdd;
	private String staffTel;
	private String staffMobileTel;
	private String staffNearestStation;
	private String affliationId;
	private String staffRemarks;
	private String affliationName;
	public String getStaffRemarks() {
		return staffRemarks;
	}
	public void setStaffRemarks(String staffRemarks) {
		this.staffRemarks = staffRemarks;
	}
	public String getAffliationName() {
		return affliationName;
	}
	public void setAffliationName(String affliationName) {
		this.affliationName = affliationName;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffMail() {
		return staffMail;
	}
	public void setStaffMail(String staffMail) {
		this.staffMail = staffMail;
	}
	public String getStaffPostCode() {
		return staffPostCode;
	}
	public void setStaffPostCode(String staffPostCode) {
		this.staffPostCode = staffPostCode;
	}
	public String getStaffAdd() {
		return staffAdd;
	}
	public void setStaffAdd(String staffAdd) {
		this.staffAdd = staffAdd;
	}
	public String getStaffTel() {
		return staffTel;
	}
	public void setStaffTel(String staffTel) {
		this.staffTel = staffTel;
	}
	public String getStaffMobileTel() {
		return staffMobileTel;
	}
	public void setStaffMobileTel(String staffMobileTel) {
		this.staffMobileTel = staffMobileTel;
	}
	public String getStaffNearestStation() {
		return staffNearestStation;
	}
	public void setStaffNearestStation(String staffNearestStation) {
		this.staffNearestStation = staffNearestStation;
	}
	public String getAffliationId() {
		return affliationId;
	}
	public void setAffliationId(String affliationId) {
		this.affliationId = affliationId;
	}
}
