package FormModel;

import org.hibernate.validator.constraints.NotEmpty;

public class SyainModel {
	public String ssearch;
	@NotEmpty
	public String staffid;
	public String staffname;
	public String staffemail;
	public String stafftel;
	public String staffmobiletel;
	public String staffpostcode;
	public String staffadd;
	public String staffneareststation;
	public String affiliationid;
	public String staffremarks;

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