package DAO_Interfaces;

import java.util.List;

import models.Candidate;
import models.Eofr;
import models.HRDepartment;
import models.OfferModel;

public interface CandidateDAO {

	// To display list of candidates
	List<Candidate> getAllCandidates();

	//
	void saveCandidate(Candidate candidate);

	// To display candidate details by id
	Candidate getCandidateById(int candidateId);

	// To display all candidates whose offer letter has to be issued
	List<Candidate> findAllIssuedCandidates();

	Long getLatestEofrIdFromDatabase();

	void insertEofrInto(Eofr eofr);

	HRDepartment getHrById(int hR_id);

	List<String> getAllDocuments();

	void updateEmploymentOfferDocuments(Eofr eofr, OfferModel offerModel);

	void updateCandidateStatus(String cand_status, String newValue);

	// To display all candidates whose offer letter has been issued
	public List<Candidate> findAllProvidedCandidates();
}
