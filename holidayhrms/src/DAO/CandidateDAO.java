package DAO;

import java.util.List;

import models.Candidate;

public interface CandidateDAO {
	List<Candidate> findAllIssuedCandidates();

	Candidate findCandidateById(int id);

}