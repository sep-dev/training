package FormModel;

import org.hibernate.validator.constraints.NotEmpty;

public class SyozokuModel {
	public String asearch;
	@NotEmpty
	public String affiliationid;
	public String affiliationname;
	public String affiliationtel;
	public String affiliationpostcode;
	public String affiliationadd;
	public String affiliationneareststation;
	public String affiliationremarks;

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
