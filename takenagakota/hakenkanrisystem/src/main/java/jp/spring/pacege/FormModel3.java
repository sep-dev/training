package jp.spring.pacege;

import org.hibernate.validator.constraints.NotEmpty;

public class FormModel3 {
	@NotEmpty
	private String name;
	private String dennwa;
	private String yuubinn;
	private String zyusyo;
	private String eki;
	private String bikou;
	private int id;





	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}

	public String getDennwa() {
		return dennwa;
	}
	public void setDennwa(String dennwa) {
		this.dennwa = dennwa;
	}

	public String getYuubinn(){
		return yuubinn;
	}
	public void setYuubinn(String yuubinn){
		this.yuubinn=yuubinn;
	}
	public String getZyusyo(){
		return zyusyo;
	}
	public void setZyusyo(String zyusyo){
		this.zyusyo=zyusyo;
	}
	public String getEki(){
		return eki;
	}
	public void setEki(String eki) {
		this.eki = eki;
	}
	public String getBikou(){
		return bikou;
	}
	public void setBikou(String bikou){
		this.bikou=bikou;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	}


