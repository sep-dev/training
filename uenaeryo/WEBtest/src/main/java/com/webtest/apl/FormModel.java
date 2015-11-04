package com.webtest.apl;


public class FormModel {


//	@Size(max = 6)
	String user;
	String pass;

	/*------------------登録画面txtboxに入っている値------------------*/
	//ユーザーID
	public String getUser(){

		return user;
	}
	//パスワード
	public String getPass(){
		return pass;
	}

	//なんかセットしてるね
	public void setuser(String user){
		this.user = user;
	}

	public void setpass(String pass){
		this.pass = pass;
	}

	byte[] read;
	public byte[] getRead(){
		return read;
	}

	public void setread(byte[] read){
		this.read = read;
	}


}


