package kanri.service;

import java.util.List;

import kanri.domain.TblLoginUser;
import kanri.repository.TblLoginUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblLoginUserService {
	@Autowired
	TblLoginUserRepository tblLoginUserRepository;

	public void delete(Integer loginUserId) {
		tblLoginUserRepository.delete(loginUserId);
	}

	public List<TblLoginUser> findAll() {
		return tblLoginUserRepository.findAll();
	}

	public TblLoginUser findOne(Integer loginUserId) {
		return tblLoginUserRepository.findOne(loginUserId);
	}

	public TblLoginUser create(TblLoginUser tblLoginUser) {

		return tblLoginUserRepository.save(tblLoginUser);
	}

	public TblLoginUser update(TblLoginUser tblLoginUser) {
		return tblLoginUserRepository.save(tblLoginUser);
	}

}