package kanri.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "tblloginuser")
public class TblLoginUser {
	@Id
	@GeneratedValue
	private Integer loginUserId;
	@NotNull
	private String loginUserName;
	@NotNull
	private String loginUser;
	@NotNull
	private String loginUserPass;

}