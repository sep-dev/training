package jp.baba.spring;

import org.hibernate.validator.constraints.NotEmpty;

public class ManagementSql {

	private int up;
	private int sub;

	private int id;
	private String remarks;
	private int kigyou;

	//派遣先専用
	@NotEmpty
	private String amo;
	@NotEmpty
	private String con;
	@NotEmpty
	private String ded;
	@NotEmpty
	private String over;
	@NotEmpty
	private String site;
	@NotEmpty
	private String yearS;
	@NotEmpty
	private String monthS;
	@NotEmpty
	private String dayS;
	@NotEmpty
	private String yearE;
	@NotEmpty
	private String monthE;
	@NotEmpty
	private String dayE;

	@NotEmpty
	private String name;

	private String year;
	private String month;
	private String input;

	//アクションキー
	public int getUp(){
		return up;
	}

	public void setUp(int up){
		this.up  = up;
	}

	//予備ID
	public int getSub(){
		return sub;
	}

	public void setSub(int sub){
		this.sub = sub;
	}



	//ID
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id  = id;
	}

	//備考
	public String getRemarks(){
		return remarks;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	//企業名
	public int getKigyou(){
		return kigyou;
	}

	public void setKigyou(int kigyou){
		this.kigyou = kigyou;
	}

//派遣先専用
	//単金
	public String getAmo(){
		return amo;
	}

	public void setAmo(String amo){
		this.amo= amo;
	}

	//条件
	public String getCon(){
		return con;
	}

	public void setCon(String con){
		this.con= con;
	}

	//控除単価
	public String getDed(){
		return ded;
	}

	public void setDed(String ded){
		this.ded = ded;
	}

	//超過単価
	public String getOver(){
		return over;
	}

	public void setOver(String over){
		this.over= over;
	}

	//サイト
	public String getSite(){
		return site;
	}

	public void setSite(String site){
		this.site = site;
	}

	//開始日
	public String getYearS(){
		return yearS;
	}

	public void setYearS(String yearS){
		this.yearS = yearS;
	}

	public String getMonthS(){
		return monthS;
	}

	public void setMonthS(String monthS){
		this.monthS = monthS;
	}

	public String getDayS(){
		return dayS;
	}

	public void setDayS(String dayS){
		this.dayS = dayS;
	}

	//終了日
	public String getYearE(){
		return yearE;
	}

	public void setYearE(String yearE){
		this.yearE = yearE;
	}

	public String getMonthE(){
		return monthE;
	}

	public void setMonthE(String monthE){
		this.monthE = monthE;
	}

	public String getDayE(){
		return dayE;
	}

	public void setDayE(String dayE){
		this.dayE = dayE;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	//検索用
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

	public String getInput(){
		return input;
	}

	public void setInput(String input){
		this.input = input;
	}

}
