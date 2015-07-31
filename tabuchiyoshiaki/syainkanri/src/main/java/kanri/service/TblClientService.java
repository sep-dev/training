package kanri.service;

import kanri.domain.TblClient;
import kanri.repository.TblClientRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblClientService {
	@Autowired
	TblClientRepository tblClientRepository;

	public void delete(Integer clientid) {
		tblClientRepository.delete(clientid);
	}

	public List<TblClient> findAll() {
		return tblClientRepository.findAll();
	}

	public TblClient findOne(Integer clientid) {
		return tblClientRepository.findOne(clientid);
	}

	public TblClient create(TblClient tblclient) {

		return tblClientRepository.save(tblclient);
	}

	public TblClient update(TblClient tblClient) {
		return tblClientRepository.save(tblClient);
	}

}