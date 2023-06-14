package DAO;

import java.util.List;

import models.Candidate;

public interface CandidateDAO {

	List<Object[]> getAllCandidates();

	void saveCandidate(Candidate candidate);

	Candidate getCandidateById(int candidateId);
}
