package kanri.web;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TblAffiliationForm {
	@NotNull
	@Id
	private Integer affiliationId;
	@NotNull
	@Size(min = 1, max = 255)
	private String affiliationName;
	@Size(min = 1, max = 8)
	private String affiliationPostCode;
	@Size(min = 1, max = 255)
	private String affiliationAdd;
	@Size(min = 1, max = 20)
	private String affiliationTel;
	@Size(min = 1, max = 255)
	private String affiliationNearestStation;
	@Size(min = 1, max = 1000)
	private String affiliationRemarks;

}