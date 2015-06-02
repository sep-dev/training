package humankanri.repository;



import humankanri.domain.TblStaff;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TblStaffRepository extends
		JpaRepository<TblStaff, Integer> {

	public TblStaff findByStaffId(Integer staffId);

	public List<TblStaff> findByStaffName(String staffName);

	public List<TblStaff> findByStaffEMail(String StaffEMail);

	public List<TblStaff> findByStaffPostCode(String StaffPostCode);

	public List<TblStaff> findByStaffAdd(String StaffAdd);

	public List<TblStaff> findByStaffTel(String StaffTel);

	public List<TblStaff> findByStaffMobileTel(String StaffMobileTel);

	public List<TblStaff> findByStaffNearestStation(String StaffNearestStation);

	public List<TblStaff> findByaffiliationId(Integer affiliationId);

	public List<TblStaff> findByStaffRemarks(String StaffRemarks);


}
