package DAO;

import java.util.List;

import models.Candidate;

public interface CandidateDAO {

	List<Candidate> getAllCandidates();

	void saveCandidate(Candidate candidate);

	Candidate getCandidateById(int candidateId);
}
