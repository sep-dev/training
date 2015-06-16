package FormModel;

import org.hibernate.validator.constraints.NotEmpty;

public class SyukkoModel {
	private String clientid;
	private String clientremarks;
	private String csearch;
	@NotEmpty
	private String clientname;
	@NotEmpty
	private String clienttel;
	@NotEmpty
	private String clientpostcode;
	@NotEmpty
	private String clientadd;
	@NotEmpty
	private String clientneareststation;

	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getClienttel() {
		return clienttel;
	}
	public void setClienttel(String clienttel) {
		this.clienttel = clienttel;
	}
	public String getClientpostcode() {
		return clientpostcode;
	}
	public void setClientpostcode(String clientpostcode) {
		this.clientpostcode = clientpostcode;
	}
	public String getClientadd() {
		return clientadd;
	}
	public void setClientadd(String clientadd) {
		this.clientadd = clientadd;
	}
	public String getClientneareststation() {
		return clientneareststation;
	}
	public void setClientneareststation(String clientneareststation) {
		this.clientneareststation = clientneareststation;
	}
	public String getClientremarks() {
		return clientremarks;
	}
	public void setClientremarks(String clientremarks) {
		this.clientremarks = clientremarks;
	}
	public String getCsearch() {
		return csearch;
	}
	public void setCsearch(String csearch) {
		this.csearch = csearch;
	}

}
