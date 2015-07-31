package kanri.repository;

import kanri.domain.TblStaff;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TblStaffRepository extends JpaRepository<TblStaff, Integer> {

	public TblStaff findByStaffId(Integer staffId);

	public List<TblStaff> findByStaffName(String staffName);

	public List<TblStaff> findByStaffEMail(String staffEMail);

	public List<TblStaff> findByStaffPostCode(String staffPostCode);

	public List<TblStaff> findByStaffAdd(String staffAdd);

	public List<TblStaff> findByStaffTel(String staffTel);

	public List<TblStaff> findByStaffMobileTel(String staffMobileTel);

	public List<TblStaff> findByStaffNearestStation(String staffNearestStation);

	public List<TblStaff> findByaffiliationId(Integer affiliationId);

	public List<TblStaff> findByStaffRemarks(String staffRemarks);

}
