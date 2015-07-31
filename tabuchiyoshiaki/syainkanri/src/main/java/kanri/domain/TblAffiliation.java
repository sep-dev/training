package kanri.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "tblaffiliation")
public class TblAffiliation {
	@Id
	@GeneratedValue
	private Integer affiliationId;
	@NotNull
	private String affiliationName;
	private String affiliationPostCode;
	private String affiliationAdd;
	private String affiliationTel;
	private String affiliationNearestStation;
	private String affiliationRemarks;

}