package kanri.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "tblstaffmanagement")
public class TblStaffManagement {
	@Id
	@GeneratedValue
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

	private TblStaff tblStaff;

}