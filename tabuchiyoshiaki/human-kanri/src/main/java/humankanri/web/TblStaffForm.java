package humankanri.web;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TblStaffForm {
	@NotNull
	@Id
	private Integer staffId;
	@NotNull
	@Size(min = 1, max = 255)
	private String staffName;
	@Size(min = 1, max = 255)
	private String staffEMail;
	@Size(min = 1, max = 8)
	private String staffPostCode;
	@Size(min = 1, max = 255)
	private String staffAdd;
	@Size(min = 1, max = 20)
	private String staffTel;
	@Size(min = 1, max = 20)
	private String staffMobileTel;
	@Size(min = 1, max = 255)
	private String staffNearestStation;
	private Integer affiliationId;
	@Size(min = 1, max = 1000)
	private String staffRemarks;
	
	
}