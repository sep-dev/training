package jp.co.hakenSystem;

public class DataSet {

	private String year;

	private String month;

	private String kensakuword;

	private String userId;

	private String pass;

	public String getYear(){
		return year;
	}
	public void setYear(String year){
		this.year = year;
	}
	public String getMonth(){
		return month;
	}
	public void setMonth(String month){
		this.month = month;
	}
	public String getKensakuword(){
		return kensakuword;
	}
	public void setKensakuword(String kensakuword){
		this.kensakuword = kensakuword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPass(){
		return pass;
	}
	public void setPass(String pass){
		this.pass = pass;
	}

}
