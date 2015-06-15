package kanri.web;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TblLoginUserForm {
	@NotNull
	@Id
	private Integer loginUserId;
	@NotNull
	@Size(min = 1, max = 255)
	private String loginUserName;
	@NotNull
	@Size(min = 1, max = 32)
	private String loginUser;
	@NotNull
	@Size(min = 1, max = 32)
	private String loginUserPass;
	@NotNull
	@Size(min = 1, max = 32)
	private String loginUserPass1;

}