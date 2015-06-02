package humankanri.web;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TblClientForm {
	@NotNull
	@Id
	private Integer clientId;
	@NotNull
	@Size(min = 1, max = 255)
	private String clientName;
	@Size(min = 1, max = 8)
	private String clientPostCode;
	@Size(min = 1, max = 255)
	private String clientAdd;
	@Size(min = 1, max = 20)
	private String clientTel;
	@Size(min = 1, max = 255)
	private String clientNearestStation;
	@Size(min = 1, max = 1000)
	private String clientRemarks;
	
	
}