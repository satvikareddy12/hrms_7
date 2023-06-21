package DAO;

import java.util.List;

import models.Candidate;
import models.Eofr;
import models.HRDepartment;
import models.OfferModel;

public interface CandidateDAO {

	List<Candidate> getAllCandidates();

	void saveCandidate(Candidate candidate);

	Candidate getCandidateById(int candidateId);

	List<Candidate> findAllIssuedCandidates();

	Long getLatestEofrIdFromDatabase();

	void insertEofrInto(Eofr eofr);

	HRDepartment getHrById(int hR_id);

	List<String> getAllDocuments();

	void updateEmploymentOfferDocuments(Eofr eofr, OfferModel offerModel);

	void updateCandidateStatus(String cand_status, String newValue);

	public List<Candidate> findAllProvidedCandidates();
}
