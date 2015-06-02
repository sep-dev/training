package jp.Dispatch.MyApp;

import org.hibernate.validator.constraints.NotEmpty;

public class StaffUpdateModel {
	//tbStaffManagementテーブルのデータ
		private String staffManId;
		private String staffId;
		private String clientId;
		@NotEmpty
		private String startDate;
		private String endDate;
		private String amountMonth;
		private String conditions;
		private String deductionUnitPrice;
		private String overtimeRate;
		private String site;
		private String staffManRemarks;

	//外部結合から取得した別テーブルのデータ
		private String clientName;
		private String staffName;
		public String getStaffManId() {
			return staffManId;
		}
		public void setStaffManId(String staffManId) {
			this.staffManId = staffManId;
		}
		public String getStaffId() {
			return staffId;
		}
		public void setStaffId(String staffId) {
			this.staffId = staffId;
		}
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public String getAmountMonth() {
			return amountMonth;
		}
		public void setAmountMonth(String amountMonth) {
			this.amountMonth = amountMonth;
		}
		public String getConditions() {
			return conditions;
		}
		public void setConditions(String conditions) {
			this.conditions = conditions;
		}
		public String getDeductionUnitPrice() {
			return deductionUnitPrice;
		}
		public void setDeductionUnitPrice(String deductionUnitPrice) {
			this.deductionUnitPrice = deductionUnitPrice;
		}
		public String getOvertimeRate() {
			return overtimeRate;
		}
		public void setOvertimeRate(String overtimeRate) {
			this.overtimeRate = overtimeRate;
		}
		public String getSite() {
			return site;
		}
		public void setSite(String site) {
			this.site = site;
		}
		public String getStaffManRemarks() {
			return staffManRemarks;
		}
		public void setStaffManRemarks(String staffManRemarks) {
			this.staffManRemarks = staffManRemarks;
		}
		public String getClientName() {
			return clientName;
		}
		public void setClientName(String clientName) {
			this.clientName = clientName;
		}
		public String getStaffName() {
			return staffName;
		}
		public void setStaffName(String staffName) {
			this.staffName = staffName;
		}
	}
