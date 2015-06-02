package jp.Dispatch.MyApp;

import org.hibernate.validator.constraints.NotEmpty;

public class ClientUpdateModel {
	private String clientId;
	@NotEmpty
	private String clientName;
	private String clientPostCode;
	private String clientAdd;
	private String clientTel;
	private String clientNearestStation;
	private String clientRemarks;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientPostCode() {
		return clientPostCode;
	}
	public void setClientPostCode(String clientPostCode) {
		this.clientPostCode = clientPostCode;
	}
	public String getClientAdd() {
		return clientAdd;
	}
	public void setClientAdd(String clientAdd) {
		this.clientAdd = clientAdd;
	}
	public String getClientTel() {
		return clientTel;
	}
	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}
	public String getClientNearestStation() {
		return clientNearestStation;
	}
	public void setClientNearestStation(String clientNearestStation) {
		this.clientNearestStation = clientNearestStation;
	}
	public String getClientRemarks() {
		return clientRemarks;
	}
	public void setClientRemarks(String clientRemarks) {
		this.clientRemarks = clientRemarks;
	}
}
