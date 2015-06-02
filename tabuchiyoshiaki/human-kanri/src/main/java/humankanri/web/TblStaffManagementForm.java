package humankanri.web;

import java.util.Date;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TblStaffManagementForm {
	@NotNull
	@Id
	private Integer staffManId;
	@NotNull
	private Integer staffId;
	@NotNull
	private Integer clientId;
	private Date startDate;
	private Date endDate;
	private  Integer amountMonth;
	@Size(min = 1, max = 255)
	private String conditions;
	private Integer deductionUnitPrice;
	private Integer overtimeRate;
	@Size(min = 1, max = 255)
	private String site;
	@Size(min = 1, max = 1000)
	private String staffManRemarks;
}