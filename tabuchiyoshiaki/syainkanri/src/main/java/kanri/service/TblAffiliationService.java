package kanri.service;

import kanri.domain.TblAffiliation;
import kanri.repository.TblAffiliationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TblAffiliationService {
	@Autowired
	TblAffiliationRepository tblAffiliationRepository;

	public void delete(Integer affiliationid) {
		tblAffiliationRepository.delete(affiliationid);
	}

	public List<TblAffiliation> findAll() {
		return tblAffiliationRepository.findAll();
	}

	public TblAffiliation findOne(Integer affiliationid) {
		return tblAffiliationRepository.findOne(affiliationid);
	}

	public TblAffiliation create(TblAffiliation tblaffiliation) {

		return tblAffiliationRepository.save(tblaffiliation);
	}

	public TblAffiliation update(TblAffiliation tblAffiliation) {
		return tblAffiliationRepository.save(tblAffiliation);
	}

}