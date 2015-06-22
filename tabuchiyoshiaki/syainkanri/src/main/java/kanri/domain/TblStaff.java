package kanri.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "tblstaff")
public class TblStaff {
	@Id
	@GeneratedValue
	private Integer staffId;
	@NotNull
	private String staffName;
	private String staffEMail;
	private String staffPostCode;
	private String staffAdd;
	private String staffTel;
	private String staffMobileTel;
	private String staffNearestStation;
	private Integer affiliationId;
	private String staffRemarks;

}