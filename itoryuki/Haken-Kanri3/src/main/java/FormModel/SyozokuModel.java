package FormModel;

import org.hibernate.validator.constraints.NotEmpty;

public class SyozokuModel {
	private String affiliationid;
	private String affiliationremarks;
	private String asearch;
	@NotEmpty
	private String affiliationname;
	@NotEmpty
	private String affiliationtel;
	@NotEmpty
	private String affiliationpostcode;
	@NotEmpty
	private String affiliationadd;
	@NotEmpty
	private String affiliationneareststation;

	public String getAffiliationid() {
		return affiliationid;
	}
	public void setAffiliationid(String affiliationid) {
		this.affiliationid = affiliationid;
	}
	public String getAffiliationname() {
		return affiliationname;
	}
	public void setAffiliationname(String affiliationname) {
		this.affiliationname = affiliationname;
	}
	public String getAffiliationtel() {
		return affiliationtel;
	}
	public void setAffiliationtel(String affiliationtel) {
		this.affiliationtel = affiliationtel;
	}
	public String getAffiliationpostcode() {
		return affiliationpostcode;
	}
	public void setAffiliationpostcode(String affiliationpostcode) {
		this.affiliationpostcode = affiliationpostcode;
	}
	public String getAffiliationadd() {
		return affiliationadd;
	}
	public void setAffiliationadd(String affiliationadd) {
		this.affiliationadd = affiliationadd;
	}
	public String getAffiliationneareststation() {
		return affiliationneareststation;
	}
	public void setAffiliationneareststation(String affiliationneareststation) {
		this.affiliationneareststation = affiliationneareststation;
	}
	public String getAffiliationremarks() {
		return affiliationremarks;
	}
	public void setAffiliationremarks(String affiliationremarks) {
		this.affiliationremarks = affiliationremarks;
	}
	public String getAsearch() {
		return asearch;
	}
	public void setAsearch(String asearch) {
		this.asearch = asearch;
	}
}
