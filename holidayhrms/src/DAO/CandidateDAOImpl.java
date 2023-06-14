package DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import models.Candidate;

@Repository
public class CandidateDAOImpl implements CandidateDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Candidate> findAllIssuedCandidates() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Candidate> cq = cb.createQuery(Candidate.class);
		Root<Candidate> root = cq.from(Candidate.class);
		cq.select(root);
		cq.where(cb.equal(root.get("cand_status"), "NA"));
		Query<Candidate> query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public Candidate findCandidateById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}