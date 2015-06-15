package kanri.service;

import kanri.domain.TblStaff;
import kanri.repository.TblStaffRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblStaffService {
	@Autowired
	TblStaffRepository tblStaffRepository;

	public void delete(Integer staffid) {
		tblStaffRepository.delete(staffid);
	}

	public List<TblStaff> findAll() {
		return tblStaffRepository.findAll();
	}

	public TblStaff findOne(Integer staffid) {
		return tblStaffRepository.findOne(staffid);
	}

	public TblStaff create(TblStaff tblstaff) {

		return tblStaffRepository.save(tblstaff);
	}

	public TblStaff update(TblStaff tblStaff) {
		return tblStaffRepository.save(tblStaff);
	}

}