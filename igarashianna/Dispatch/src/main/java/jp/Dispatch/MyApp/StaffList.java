package jp.Dispatch.MyApp;

public class StaffList {
	private String clientName;
	private String staffName;
	private String clientId;
	private String staffId;

	private String affliationId;


	public String getAffliationId() {
		return affliationId;
	}
	public void setAffliationId(String affliationId) {
		this.affliationId = affliationId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	private String affliationName;

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAffliationName() {
		return affliationName;
	}
	public void setAffliationName(String affliationName) {
		this.affliationName = affliationName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
}
