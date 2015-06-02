package humankanri.service;

import humankanri.domain.TblStaffManagement;
import humankanri.repository.TblStaffManagementRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblStaffManagementService {
	@Autowired
	TblStaffManagementRepository tblStaffManagementRepository;
	public void delete(Integer staffmanid) {
		tblStaffManagementRepository.delete(staffmanid);
	}
	
	public List<TblStaffManagement> findAll() {
		return tblStaffManagementRepository.findAll();
	}
	
	  public TblStaffManagement findOne(Integer staffmanid) {
	        return tblStaffManagementRepository.findOne(staffmanid);
	    }

	    public TblStaffManagement create(TblStaffManagement tblstaffmanagement) {
	        
	        return tblStaffManagementRepository.save(tblstaffmanagement);
	    }
	
	public TblStaffManagement update(Integer staffmanid) {
        return tblStaffManagementRepository.save(tblstaffmanagement);
    }

	
}