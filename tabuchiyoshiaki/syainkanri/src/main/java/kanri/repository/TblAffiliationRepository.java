package kanri.repository;

import kanri.domain.TblAffiliation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TblAffiliationRepository extends
		JpaRepository<TblAffiliation, Integer> {
	public TblAffiliation findByAffiliationId(Integer affiliationId);

	public List<TblAffiliation> findByAffiliationName(String affiliationName);

	public List<TblAffiliation> findByAffiliationPostCode(
			String affiliationPostCode);

	public List<TblAffiliation> findByAffiliationAdd(String affiliationAdd);

	public List<TblAffiliation> findByAffiliationTel(String affiliationTel);

	public List<TblAffiliation> findByAffiliationNearestStation(
			String affiliationNearestStation);

	public List<TblAffiliation> findByAffiliationRemarks(
			String affiliationRemarks);

}
