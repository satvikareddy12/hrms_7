package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import models.Candidate;
import models.Employee;
import models.Eofr;
import models.Inductiondocuments;
import models.OfferModel;
import models.empoffdocscomposite;
import models.empoffdocuments;

@Repository
public class CandidateDAOImpl implements CandidateDAO {

	@PersistenceContext
	private EntityManager entityManager;

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

	Candidate cann;

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
		int eofrId = employmentOfferModel.getEofr_cand_id();
		List<String> documentsToBring = of.getDocuments();

		System.out.println(documentsToBring);
		List<Inductiondocuments> inductionDocuments = getInductionDocuments();

		System.out.println(inductionDocuments);

		int docIndex = 1;
		for (String document : documentsToBring) {
			int idtyId = findIdtyIdByTitle(inductionDocuments, document);
			empoffdocscomposite comp = new empoffdocscomposite();
			comp.setEofrId(eofrId);
			comp.setEofdDocIndex(docIndex);
			empoffdocuments documentModel = new empoffdocuments(comp, idtyId);
			System.out.println(documentModel);
			saveEmploymentOfferDocument(documentModel);
			docIndex++;
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
		return 0; // Return an appropriate default value if the document title is not found
	}

	@Override
	public Employee getHrById(int hR_id) {

		return entityManager.find(Employee.class, hR_id);

	}
}
