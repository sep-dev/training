package jp.baba.spring;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class SqlModel2 {
	//その他

	private int up;

	@NotNull
	private int sub;

	@NotNull
	private int id;

	private String name;

	private String tel;

	private String post;

	private String add;

	private String station;

	private String remarks;

	private String kigyou;

	//派遣社員専用
	private String mobtel;

	private String email;

	//ユーザー専用

	private String loginUser;

	private String loginUserPass;

	private String loginUserPass_second;



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

	//名前
	@NotEmpty
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	//電話番号
	@NotNull
	@NotEmpty
	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}

	//郵便番号
	@NotNull
	@NotEmpty
	public String getPost(){
		return post;
	}

	public void setPost(String post){
		this.post = post;
	}

	//住所
	@NotNull
	@NotEmpty
	public String getAdd(){
		return add;
	}

	public void setAdd(String add){
		this.add = add;
	}

	//最寄駅
	@NotNull
	@NotEmpty
	public String getStation(){
		return station;
	}

	public void setStation(String station){
		this.station = station;
	}

	//備考
	@NotNull
	@NotEmpty
	public String getRemarks(){
		return remarks;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	//ユーザー専用
	//ユーザーID
	@NotEmpty
	public String getLoginUser(){
		return loginUser;
	}

	public void setLoginUser(String loginUser){
		this.loginUser = loginUser;
	}

	//パスワード
	@NotEmpty
	public String getLoginUserPass(){
		return loginUserPass;
	}

	public void setLoginUserPass(String loginUserPass){
		this.loginUserPass = loginUserPass;
	}

	//確認パスワード
	@NotEmpty
	public String getLoginUserPass_second(){
		return loginUserPass_second;
	}

	public void setLoginUserPass_second(String loginUserPass_second){
		this.loginUserPass_second = loginUserPass_second;
	}

	//派遣社員専用
	//携帯電話
	@NotNull
	@NotEmpty
	public String getMobtel(){
		return mobtel;
	}
	public void setMobtel(String mobtel){
		this.mobtel = mobtel;
	}

	//メール
	@NotNull
	@NotEmpty
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	//企業名
	@NotNull
	@NotEmpty
	public String getKigyou(){
		return kigyou;
	}

	public void setKigyou(String kigyou){
		this.kigyou = kigyou;
	}


}
