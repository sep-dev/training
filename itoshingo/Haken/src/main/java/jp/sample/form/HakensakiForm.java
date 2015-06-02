package jp.sample.form;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class HakensakiForm {

	@Id
	private int id;

	@NotNull
	@Min(1)
	private int staffid;

	@NotNull
	@Min(1)
	private int clientid;

	private String startyear;
	private String startmonth;
	private String startday;

	private String endyear;
	private String endmonth;
	private String endday;

	@Min(0)
	private int amountmonth;

	private String conditions;

	@Min(0)
	private int deductionunitprice;

	@Min(0)
	private int overtimerate;

	private String site;

	private String remarks;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getStaffid(){
		return staffid;
	}

	public void setStaffid(int staffid){
		this.staffid = staffid;
	}

	public int getClientid(){
		return clientid;
	}

	public void setClientid(int clientid){
		this.clientid = clientid;
	}

	public String getStartyear(){
		return startyear;
	}

	public void setStartyear(String startyear){
		this.startyear = startyear;
	}

	public String getStartmonth(){
		return startmonth;
	}

	public void setStartmonth(String startmonth){
		this.startmonth = startmonth;
	}

	public String getStartday(){
		return startday;
	}

	public void setStartday(String startday){
		this.startday = startday;
	}

	public String getEndyear(){
		return endyear;
	}

	public void setEndyear(String endyear){
		this.endyear = endyear;
	}

	public String getEndmonth(){
		return endmonth;
	}

	public void setEndmonth(String endmonth){
		this.endmonth = endmonth;
	}

	public String getEndday(){
		return endday;
	}

	public void setEndday(String endday){
		this.endday = endday;
	}

	public int getAmountmonth(){
		return amountmonth;
	}

	public void setAmountmonth(int amountmonth){
		this.amountmonth = amountmonth;
	}

	public String getConditions(){
		return conditions;
	}

	public void setConditions(String conditions){
		this.conditions = conditions;
	}

	public int getDeductionunitprice(){
		return deductionunitprice;
	}

	public void setDeductionunitprice(int deductionunitprice){
		this.deductionunitprice = deductionunitprice;
	}

	public int getOvertimerate(){
		return overtimerate;
	}

	public void setOvertimerate(int overtimerate){
		this.overtimerate = overtimerate;
	}

	public String getSite(){
		return site;
	}

	public void setSite(String site){
		this.site = site;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

}
