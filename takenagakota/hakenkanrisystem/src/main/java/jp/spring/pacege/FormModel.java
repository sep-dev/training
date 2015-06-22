package jp.spring.pacege;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class FormModel {
	private int id;
	@NotNull
	private int tankin;
	@NotEmpty
	private String zyouken;
	@NotNull
	private int kouzyo;
	@NotEmpty
	private String saito;
	@NotNull
	private int tyouka;
	@NotEmpty
	private String nen;
	@NotEmpty
	private String gatu;
	@NotEmpty
	private String hi;
	@NotEmpty
	private String onen;
	@NotEmpty
	private String ogatu;
	@NotEmpty
	private String ohi;
	@NotEmpty
	private String bikou;

	private String name;
	private int syainID;
	private int kaisya;




	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public int getSyainID(){
		return syainID;
	}
	public void setSyainID(int syamei){
		this.syainID=syamei;
	}
	public int getKaisya(){
		return kaisya;
	}
	public void setKaisya(int kaisya){
		this.kaisya=kaisya;
	}
	public int getTankin(){
		return tankin;
	}
	public void setTankin(int tankin){
		this.tankin=tankin;
	}
	public String getZyouken(){
		return zyouken;
	}
	public void setZyouken(String zyouken){
		this.zyouken=zyouken;
	}
	public int getKouzyo(){
		return kouzyo;
	}
	public void setKouzyo(int kouzyo){
		this.kouzyo=kouzyo;
	}
	public int getTyouka(){
		return tyouka;
	}
	public void setTyouka(int tyouka){
		this.tyouka=tyouka;
	}
	public String getNen(){
		return nen;
	}
	public void setNen(String nen){
		this.nen=nen;
	}
	public String getGatu(){
		return gatu;
	}
	public void setGatu(String gatu){
		this.gatu=gatu;
	}
	public String getHi(){
		return hi;
	}
	public void setHi(String hi){
		this.hi=hi;
	}
	public String getOnen(){
		return onen;
	}
	public void setOnen(String onen){
		this.onen=onen;
	}
	public String getOgatu(){
		return ogatu;
	}
	public void setOgatu(String ogatu){
		this.ogatu=ogatu;
	}
	public String getOhi(){
		return ohi;
	}
	public void setOhi(String ohi){
		this.ohi=ohi;
	}
	public String getBikou(){
		return bikou;
	}
	public void setBikou(String bikou){
		this.bikou=bikou;
	}
	public String getSaito(){
		return saito;
	}
	public void setSaito(String saito){
		this.saito=saito;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
}
