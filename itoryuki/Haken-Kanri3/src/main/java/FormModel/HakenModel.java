package FormModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class HakenModel {
	private String staffmanid;
	private String staffid;
	private String clientid;
	private String hsearch;
	private String conditions;
	private String staffmanremarks;
	@NotEmpty
	private String amountmonth;
	@NotEmpty
	private String deductionunitprice;
	@NotEmpty
	private String overtimerate;
	@NotEmpty
	private String site;
	@NotEmpty
	@Size(min = 4 ,max=4)
	private String startdate1;
	@NotEmpty
	@Size(min = 2 ,max=2)
	@Min(01)
	@Max(12)
	private String startdate2;
	@NotEmpty
	@Size(min = 2 ,max=2)
	@Min(01)
	@Max(31)
	private String startdate3;
	@NotEmpty
	@Size(min = 4 ,max=4)
	private String enddate1;
	@NotEmpty
	@Size(min = 2 ,max=2)
	@Min(01)
	@Max(12)
	private String enddate2;
	@NotEmpty
	@Size(min = 2 ,max=2)
	@Min(01)
	@Max(31)
	private String enddate3;


	public String getStaffmanid() {
		return staffmanid;
	}
	public void setStaffmanid(String staffmanid) {
		this.staffmanid = staffmanid;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getAmountmonth() {
		return amountmonth;
	}
	public void setAmountmonth(String amountmonth) {
		this.amountmonth = amountmonth;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getDeductionunitprice() {
		return deductionunitprice;
	}
	public void setDeductionunitprice(String deductionunitprice) {
		this.deductionunitprice = deductionunitprice;
	}
	public String getOvertimerate() {
		return overtimerate;
	}
	public void setOvertimerate(String overtimerate) {
		this.overtimerate = overtimerate;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getStartdate1() {
		return startdate1;
	}
	public void setStartdate1(String startdate1) {
		this.startdate1 = startdate1;
	}
	public String getStartdate2() {
		return startdate2;
	}
	public void setStartdate2(String startdate2) {
		this.startdate2 = startdate2;
	}
	public String getStartdate3() {
		return startdate3;
	}
	public void setStartdate3(String startdate3) {
		this.startdate3 = startdate3;
	}
	public String getEnddate1() {
		return enddate1;
	}
	public void setEnddate1(String enddate1) {
		this.enddate1 = enddate1;
	}
	public String getEnddate2() {
		return enddate2;
	}
	public void setEnddate2(String enddate2) {
		this.enddate2 = enddate2;
	}
	public String getEnddate3() {
		return enddate3;
	}
	public void setEnddate3(String enddate3) {
		this.enddate3 = enddate3;
	}
	public String getStaffmanremarks() {
		return staffmanremarks;
	}
	public void setStaffmanremarks(String staffmanremarks) {
		this.staffmanremarks = staffmanremarks;
	}
	public String getHsearch() {
		return hsearch;
	}
	public void setHsearch(String hsearch) {
		this.hsearch = hsearch;
	}


}