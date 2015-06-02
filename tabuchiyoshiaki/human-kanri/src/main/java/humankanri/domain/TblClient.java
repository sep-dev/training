package humankanri.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "tblclient")
public class TblClient {
	@Id
	private Integer clientId;
	@NotNull
	private String clientName;
	private String clientPostCode;
	private String clientAdd;
	private String clientTel;
	private String clientNearestStation;
	private String clientRemarks;
	

}