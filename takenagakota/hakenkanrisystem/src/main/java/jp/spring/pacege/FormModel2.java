package jp.spring.pacege;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class FormModel2 {

	@NotEmpty
		private String name;

	@NotEmpty
		private String meru;

	@NotEmpty
		private String dennwa;

	@NotEmpty
		private String keitai;

	@NotEmpty
		private String yuubinn;

	@NotEmpty
		private String zyusyo;

	@NotEmpty
		private String eki;

	@NotNull
		private int kaisyaID;

	@NotEmpty
		private String bikou;

		private int id;

		private int hensyuu;

		public int getHensyuu() {
			return hensyuu;
		}
		public void setHensyuu(int hensyuu) {
			this.hensyuu = hensyuu;
		}

		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}

		public String getMeru(){
			return meru;
		}
		public void setMeru(String meru){
			this.meru=meru;
		}

		public String getDennwa(){
			return dennwa;
		}
		public void setDennwa(String dennwa){
			this.dennwa=dennwa;
		}

		public String getKeitai(){
			return keitai;
		}
		public void setKeitai(String keitai){
			this.keitai=keitai;
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
		public void setEki(String eki){
			this.eki=eki;
		}

		public String getBikou(){
			return bikou;
		}
		public void setBikou(String bikou){
			this.bikou=bikou;
		}

		public int getKaisyaID(){
			return kaisyaID;
		}
		public void setkaisyaID(int kaisyaID){
			this.kaisyaID=kaisyaID;
		}

		public int getId(){
			return id;
		}
		public void setId(int id){
			this.id=id;
		}

}
