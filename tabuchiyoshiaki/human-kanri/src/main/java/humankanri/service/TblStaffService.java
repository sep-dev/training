package humankanri.service;

import humankanri.domain.TblStaff;
import humankanri.repository.TblStaffRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblStaffService {
	@Autowired
	TblStaffRepository tblStaffRepository;
	
	
	public List<TblStaff> findAll() {
		return tblStaffRepository.findAll();
	}
	
	 
	
	
}