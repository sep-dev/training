package kanri.repository;

import java.util.List;

import kanri.domain.TblLoginUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TblLoginUserRepository extends
		JpaRepository<TblLoginUser, Integer> {
	public TblLoginUser findByLoginUserId(Integer loginUserId);

	public List<TblLoginUser> findByLoginUserName(String loginUserName);

	public List<TblLoginUser> findByLoginUser(String loginUser);

	public List<TblLoginUser> findByLoginUserPass(String loginUserPass);

}
