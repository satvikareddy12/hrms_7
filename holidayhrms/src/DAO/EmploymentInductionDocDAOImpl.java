
package DAO;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import models.EmploymentInductionDocument;

@Component
public class EmploymentInductionDocDAOImpl implements EmploymentInductionDocumentDAO {

	private EntityManagerFactory entityManagerFactory;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void addEmploymentInductionDocument(EmploymentInductionDocument document) {
		entityManager.persist(document);
	}

	@Transactional
	public EmploymentInductionDocument getEmploymentInductionDocument(int documentIndex) {
		return entityManager.find(EmploymentInductionDocument.class, documentIndex);
	}

	@Override
	public ArrayList<EmploymentInductionDocument> getAllDocuments() {
		String queryString = "SELECT e FROM EmploymentInductionDocument e";
		Query query = entityManager.createQuery(queryString);

		ArrayList<EmploymentInductionDocument> al = new ArrayList<>();
		EmploymentInductionDocument eid = new EmploymentInductionDocument();
		// eid.setDocumentIndex(1);
		// eid.setEmplid(1);
		// eid.setEmplidty(11);
		// eid.setIndcProcessedAusrId(111);
		// eid.setVerified("Yes");
		// al.add(eid);

		return al; // (ArrayList<EmploymentInductionDocument>) query.getResultList();
	}

}
