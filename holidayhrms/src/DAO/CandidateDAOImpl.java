package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import DAO_Interfaces.CandidateDAO;
import models.Candidate;
import models.Eofr;
import models.HRDepartment;
import models.Inductiondocuments;
import models.OfferModel;
import models.empoffdocscomposite;
import models.empoffdocuments;

@Repository
public class CandidateDAOImpl implements CandidateDAO {

	@PersistenceContext
	private EntityManager entityManager;
	int eofrId;
	Candidate cann;

	@Override
	@Transactional
	public void saveCandidate(Candidate candidate) {
		entityManager.persist(candidate);
	}

	@Override
	@Transactional
	public Candidate getCandidateById(int candidateId) {
		return entityManager.find(Candidate.class, candidateId);
	}

	@Override
	@Transactional
	public List<Candidate> getAllCandidates() {
		String query = "SELECT c FROM Candidate c";
		return entityManager.createQuery(query, Candidate.class).getResultList();
	}

	@Override
	public List<Candidate> findAllIssuedCandidates() {
		TypedQuery<Candidate> query = entityManager
				.createQuery("SELECT c FROM Candidate c WHERE c.candStatus = :status", Candidate.class);
		query.setParameter("status", "NA");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateCandidateStatus(String cand_status, String newValue) {
		cann.setCandStatus(newValue); // Modify the desired column value
		entityManager.merge(cann); // Save the changes to the database
	}

	@Override
	@Transactional
	public void insertEofrInto(Eofr eofr) {
		entityManager.persist(eofr);
	}

	@Override
	public Long getLatestEofrIdFromDatabase() {
		TypedQuery<Long> query = entityManager.createQuery("SELECT MAX(e.eofr_id) FROM Eofr e", Long.class);
		return query.getSingleResult();
	}

	@Override
	public List<String> getAllDocuments() {
		TypedQuery<String> query = entityManager.createQuery("SELECT e.idty_title FROM Inductiondocuments e",
				String.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateEmploymentOfferDocuments(Eofr employmentOfferModel, OfferModel of) {

		System.out.println("in here");
		eofrId = employmentOfferModel.getEofr_cand_id();
		List<String> documentsToBring = of.getDocuments();

		System.out.println(documentsToBring);
		List<Inductiondocuments> inductionDocuments = getInductionDocuments();

		System.out.println(inductionDocuments);

		for (String document : documentsToBring) {
			int docIndex = getMaxDocIndexFromDatabase() + 1;

			int idtyId = findIdtyIdByTitle(inductionDocuments, document);
			empoffdocscomposite comp = new empoffdocscomposite();
			comp.setEofrId(eofrId);
			comp.setEofdDocIndex(docIndex);
			empoffdocuments documentModel = new empoffdocuments(comp, idtyId);

			System.out.println(documentModel);
			entityManager.persist(documentModel);
		}
	}

	private List<Inductiondocuments> getInductionDocuments() {
		TypedQuery<Inductiondocuments> query = entityManager.createQuery("SELECT d FROM Inductiondocuments d",
				Inductiondocuments.class);
		return query.getResultList();
	}

	@Transactional
	private void saveEmploymentOfferDocument(empoffdocuments document) {
		entityManager.persist(document);
	}

	private int findIdtyIdByTitle(List<Inductiondocuments> inductionDocuments, String title) {
		for (Inductiondocuments document : inductionDocuments) {
			if (document.getIdtyTitle().equalsIgnoreCase(title)) {
				return document.getIdtyId();
			}
		}
		return 0;
	}

	@Override
	public HRDepartment getHrById(int hR_id) {
		String queryString = "SELECT hr FROM HRDepartment hr WHERE hr.employeeId = :id";
		TypedQuery<HRDepartment> query = entityManager.createQuery(queryString, HRDepartment.class);
		query.setParameter("id", hR_id);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null; // Return null if no HR department is found for the given ID
		}
	}

	@Override
	public List<Candidate> findAllProvidedCandidates() {
		TypedQuery<Candidate> query = entityManager
				.createQuery("SELECT c FROM Candidate c WHERE c.candStatus = :status", Candidate.class);
		query.setParameter("status", "AC");
		return query.getResultList();
	}

	public Integer getMaxDocIndexFromDatabase() {
		TypedQuery<Integer> query = entityManager.createQuery(
				"SELECT MAX(e.empoff.eofdDocIndex) FROM empoffdocuments e WHERE e.empoff.eofrId = :eofdId",
				Integer.class);
		query.setParameter("eofdId", eofrId);
		System.out.println(eofrId);
		Integer flag = query.getSingleResult();
		if (flag == null)
			return 0;
		else
			return flag;

	}

}
