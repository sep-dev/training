package jp.co.hakenSystem;

public class UpdateFormModel {

	private String kensakuword;

	private String tablename;

	private String primary;

	private String deleteId;

	private String Itiran;

	//派遣先一覧
	private String syainname;

	private String hakensakiId;

	private String tankin;

	private String jouken;

	private String koujo;

	private String tyouka;

	//派遣社員情報

	private String staffname;

	private String staffemail;

	private String stafftel;

	private String staffmobiletel;

	private String staffpostcode;

	private String staffadd;

	private String staffneareststation;

	private String staffremarks;

	private String affiliationId;

	private String updatestaffId;

	private String deletestaffId;

	//ログインユーザー情報
	private String username;

	private String user;

	private String userid;

	private String updateloginUserId;

	private String deleteloginUserId;

	private String password;

	private String passwordcheck;

	//出向先情報
	private String affiliationid;

	private String affiliationname;

	private String affiliationtel;

	private String affiliationpostcode;

	private String affiliationadd;

	private String affiliationneareststation;

	private String affiliationremarks;

	private String updateaffiliationId;

	private String deleteaffiliationId;

	//所属元情報

	private String clientid;

	private String clientname;

	private String clienttel;

	private String clientpostcode;

	private String clientadd;

	private String clientneareststation;

	private String clientremarks;

	private String updateclientId;

	private String deleteclientId;




	public String getKensakuword(){
		return kensakuword;
	}
	public void setKensakuword(String kensakuword){
		this.kensakuword = kensakuword;
	}
	public String getTablename(){
		return tablename;
	}
	public void setTablename(String tablename){
		this.tablename = tablename;
	}
	public String getPrimay(){
		return primary;
	}
	public void setPrimary(String primary){
		this.primary = primary;
	}
	public String getDeleteId(){
		return deleteId;
	}
	public void setDeleteId(String deleteId){
		this.deleteId = deleteId;
	}
	public String getItiran(){
		return Itiran;
	}
	public void setItiran(String Itiran){
		this.Itiran = Itiran;
	}

	//派遣先情報
	public String getSyainname(){
		return syainname;
	}
	public void setSyainname(String syainname){
		this.syainname = syainname;
	}
	public String getHakensakiId(){
		return hakensakiId;
	}
	public void setHakensakiId(String hakensakiId){
		this.hakensakiId = hakensakiId;
	}
	public String getTankin(){
		return tankin;
	}
	public void setTankin(String tankin){
		this.tankin = tankin;
	}
	public String getJouken(){
		return jouken;
	}
	public void setJouken(String jouken){
		this.jouken = jouken;
	}
	public String getKoujo(){
		return koujo;
	}
	public void setKoujo(String koujo){
		this.koujo = koujo;
	}
	public String getTyouka(){
		return tyouka;
	}
	public void setTyouka(String tyouka){
		this.tyouka = tyouka;
	}
	//派遣社員情報
	public void setStaffname(String staffname){
		this.staffname = staffname;
	}
	public String getStaffname(){
		return staffname;
	}
	public void setStaffemail(String staffemail){
		this.staffemail = staffemail;
	}
	public String getStaffemail(){
		return staffemail;
	}
	public void setStafftel(String stafftel){
		this.stafftel = stafftel;
	}
	public String getStafftel(){
		return stafftel;
	}
	public void setStaffmobiletel(String staffmobiletel){
		this.staffmobiletel = staffmobiletel;
	}
	public String getStaffmobiletel(){
		return staffmobiletel;
	}
	public void setStaffpostcode(String staffpostcode){
		this.staffpostcode = staffpostcode;
	}
	public String getStaffpostcode(){
		return staffpostcode;
	}
	public void setStaffadd(String staffadd){
		this.staffadd = staffadd;
	}
	public String getStaffadd(){
		return staffadd;
	}
	public void setstaffneareststation(String staffneareststation){
		this.staffneareststation = staffneareststation;
	}
	public String getStaffneareststation(){
		return staffneareststation;
	}
	public void setStaffremarks(String staffremarks){
		this.staffremarks = staffremarks;
	}
	public String getStaffremarks(){
		return staffremarks;
	}
	public void setAffiliationId(String affiliationId){
		this.affiliationId = affiliationId;
	}
	public String getAffiliationId(){
		return affiliationId;
	}
	public void setUpdatestaffId(String updatestaffId){
		this.updatestaffId = updatestaffId;
	}
	public String getUpdatestaffId(){
		return updatestaffId;
	}
	public void setDeletestaffId(String deletestaffId){
		this.deletestaffId = deletestaffId;
	}
	public String getDeletestaffId(){
		return deletestaffId;
	}



	//ログインユーザー情報
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUser(){
		return user;
	}
	public void setUser(String user){
		this.user = user;
	}
	public String getUserid(){
		return userid;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	public String getUpdateloginUserId(){
		return updateloginUserId;
	}
	public void setUpdateloginUserId(String deleteloginUserId){
		this.updateloginUserId = deleteloginUserId;
	}
	public String getDeleteloginUserId(){
		return deleteloginUserId;
	}
	public void setDeleteloginUserId(String deleteloginUserId){
		this.deleteloginUserId = deleteloginUserId;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getPasswordcheck(){
		return passwordcheck;
	}
	public void setPasswordcheck(String passwordcheck){
		this.passwordcheck = passwordcheck;
	}
	//出向先情報
	public void setClientid(String clientid){
		this.clientid = clientid;
	}
	public String getClientid(){
		return clientid;
	}
	public void setClientname(String clientname){
		this.clientname = clientname;
	}
	public String getClientname(){
		return clientname;
	}
	public void setClienttel(String clienttel){
		this.clienttel = clienttel;
	}
	public String getClienttel(){
		return clienttel;
	}
	public void setClientpostcode(String clientpostcode){
		this.clientpostcode = clientpostcode;
	}
	public String getClientpostcode(){
		return clientpostcode;
	}
	public void setClientadd(String clientadd){
		this.clientadd = clientadd;
	}
	public String getClientadd(){
		return clientadd;
	}
	public void setClientneareststation(String clientneareststation){
		this.clientneareststation = clientneareststation;
	}
	public String getClientneareststation(){
		return clientneareststation;
	}
	public void setClientremarks(String clientremarks){
		this.clientremarks = clientremarks;
	}
	public String getClientremarks(){
		return clientremarks;
	}
	public void setUpdateclientId(String updateclientId){
		this.updateclientId = updateclientId;
	}
	public String getUpdateclientId(){
		return updateclientId;
	}
	public void setDeletecliemtId(String deleteclientId){
		this.deleteclientId = deleteclientId;
	}
	public String getDeleteclientId(){
		return deleteclientId;
	}
	//所属元情報
	public void setAffiliationid(String affiliationid){
		this.affiliationid = affiliationid;
	}
	public String getAffiliationid(){
		return affiliationid;
	}
	public void setAffiliationname(String affiliationname){
		this.affiliationname = affiliationname;
	}
	public String getAffiliationname(){
		return affiliationname;
	}
	public void setAffiliationtel(String affiliationtel){
		this.affiliationtel = affiliationtel;
	}
	public String getAffiliationtel(){
		return affiliationtel;
	}
	public void setAffiliationpostcode(String affiliationpostcode){
		this.affiliationpostcode = affiliationpostcode;
	}
	public String getAffiliationpostcode(){
		return affiliationpostcode;
	}
	public void setAffiliationadd(String affiliationadd){
		this.affiliationadd = affiliationadd;
	}
	public String getAffiliationadd(){
		return affiliationadd;
	}
	public void setAffiliationneareststation(String affiliationneareststation){
		this.affiliationneareststation = affiliationneareststation;
	}
	public String getAffiliationneareststation(){
		return affiliationneareststation;
	}
	public void setAffiliationremarks(String affiliationremarks){
		this.affiliationremarks = affiliationremarks;
	}
	public String getAffiliationremarks(){
		return affiliationremarks;
	}
	public void setUpdateaffiliationId(String updateaffiliationId){
		this.updateaffiliationId = updateaffiliationId;
	}
	public String getUpdateaffiliationId(){
		return updateaffiliationId;
	}
	public void setDeleteaffiliationId(String deleteaffiliationId){
		this.deleteaffiliationId = deleteaffiliationId;
	}
	public String getDeleteaffiliationId(){
		return deleteaffiliationId;
	}
}


