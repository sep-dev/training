package kanri.repository;

import kanri.domain.TblClient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TblClientRepository extends JpaRepository<TblClient, Integer> {

	public TblClient findByClientId(Integer clientId);

	public List<TblClient> findByClientName(String clientName);

	public List<TblClient> findByClientPostCode(String clientPostCode);

	public List<TblClient> findByClientAdd(String clientAdd);

	public List<TblClient> findByClientTel(String clientTel);

	public List<TblClient> findByClientNearestStation(
			String clientNearestStation);

	public List<TblClient> findByClientRemarks(String clientRemarks);

}
