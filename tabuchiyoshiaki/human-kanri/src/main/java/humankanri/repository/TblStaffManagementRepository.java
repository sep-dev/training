package humankanri.repository;

import humankanri.domain.TblStaffManagement;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TblStaffManagementRepository extends
		JpaRepository<TblStaffManagement, Integer> {

	public TblStaffManagement findByStaffManId(Integer staffManId);

	public List<TblStaffManagement> findByStaffId(Integer staffId);

	public List<TblStaffManagement> findByClientId(Integer clientId);

	public List<TblStaffManagement> findByStartDate(Date startDate);

	public List<TblStaffManagement> findByEndDate(Date endDate);

	public List<TblStaffManagement> findByAmountMonth(Integer amountMonth);

	public List<TblStaffManagement> findByConditions(String conditions);

	public List<TblStaffManagement> findByDeductionUnitPrice(
			Integer deductionUnitPrice);

	public List<TblStaffManagement> findByOvertimeRate(Integer overtimeRate);

	public List<TblStaffManagement> findBySite(String site);

	public List<TblStaffManagement> findByStaffManRemarks(String staffManRemarks);

}
