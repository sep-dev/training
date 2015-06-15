package FormModel;

import org.hibernate.validator.constraints.NotEmpty;

public class SyainModel {
	private String staffid;
	private String staffremarks;
	private String ssearch;
	@NotEmpty
	private String staffname;
	@NotEmpty
	private String staffemail;
	@NotEmpty
	private String stafftel;
	@NotEmpty
	private String staffmobiletel;
	@NotEmpty
	private String staffpostcode;
	@NotEmpty
	private String staffadd;
	@NotEmpty
	private String staffneareststation;
	@NotEmpty
	private String affiliationid;

	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public String getStaffemail() {
		return staffemail;
	}
	public void setStaffemail(String staffemail) {
		this.staffemail = staffemail;
	}
	public String getStafftel() {
		return stafftel;
	}
	public void setStafftel(String stafftel) {
		this.stafftel = stafftel;
	}
	public String getStaffmobiletel() {
		return staffmobiletel;
	}
	public void setStaffmobiletel(String staffmobiletel) {
		this.staffmobiletel = staffmobiletel;
	}
	public String getStaffpostcode() {
		return staffpostcode;
	}
	public void setStaffpostcode(String staffpostcode) {
		this.staffpostcode = staffpostcode;
	}
	public String getStaffadd() {
		return staffadd;
	}
	public void setStaffadd(String staffadd) {
		this.staffadd = staffadd;
	}
	public String getStaffneareststation() {
		return staffneareststation;
	}
	public void setStaffneareststation(String staffneareststation) {
		this.staffneareststation = staffneareststation;
	}
	public String getAffiliationid() {
		return affiliationid;
	}
	public void setAffiliationid(String affiliationid) {
		this.affiliationid = affiliationid;
	}
	public String getStaffremarks() {
		return staffremarks;
	}
	public void setStaffremarks(String staffremarks) {
		this.staffremarks = staffremarks;
	}
	public String getSsearch() {
		return ssearch;
	}
	public void setSsearch(String ssearch) {
		this.ssearch = ssearch;
	}

}