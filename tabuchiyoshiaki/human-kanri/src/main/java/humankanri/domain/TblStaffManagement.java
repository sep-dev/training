package humankanri.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data


@Entity
@Table(name = "tblstaffmanagement")
public class TblStaffManagement {
	 @Id
	    private Integer staffManId;
	 @NotNull
	 	private Integer staffId;
	 @NotNull
	 	private Integer clientId;
	 	private Date startDate;
	 	private Date endDate;
	 	private Integer amountMonth;
	 	private String conditions;
	 	private Integer deductionUnitPrice;
	 	private Integer overtimeRate;
	 	private String site;
	 	private String staffManRemarks;
	 	
}