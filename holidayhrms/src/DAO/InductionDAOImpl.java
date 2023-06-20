package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import models.Induction;

@Repository
public class InductionDAOImpl implements InductionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<Integer> getAllInductions() {
		String query = "SELECT DISTINCT i.indcId FROM Induction i ORDER BY i.indcId DESC";
		return entityManager.createQuery(query, Integer.class).getResultList();
	}

	@Override
	@Transactional
	public List<Induction> getInductionById(int id) {
		Query query = entityManager.createQuery("SELECT i FROM Induction i WHERE i.id = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void insertEmployee(Induction induction) {
		entityManager.persist(induction);

	}

	@Override
	@Transactional
	public List<Integer> getAllEmploymentOffers() {
		String query = "SELECT o.candidateId FROM HrmsEmploymentOffer o WHERE o.status='INPR'";
		return entityManager.createQuery(query, Integer.class).getResultList();
	}

	@Override
	@Transactional
	public void updateEmploymentOfferStatus(int offerId, String status) {
		String query = "UPDATE HrmsEmploymentOffer SET eofr_status = :status WHERE candidateId = :offerId";
		entityManager.createQuery(query).setParameter("status", status).setParameter("offerId", offerId)
				.executeUpdate();
	}

}
