package DAO;

import java.util.List;

import models.Induction;

public interface InductionDAO {
	List<Integer> getAllInductions();

	public List<Induction> getInductionById(int id);

	public void insertEmployee(Induction induction);

	public List<Integer> getAllEmploymentOffers();

	void updateEmploymentOfferStatus(int offerId, String status);

	int getIndex();

}
