package humankanri.service;

import humankanri.domain.TblClient;
import humankanri.repository.TblClientRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblClientService {
	@Autowired
	TblClientRepository tblClientRepository;
	
	
	public List<TblClient> findAll() {
		return tblClientRepository.findAll();
	}
	
	 
	
	
}