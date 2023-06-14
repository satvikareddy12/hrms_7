package DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import models.ApplyPermissions;

@Component
@Transactional
public class ApplyPermissionDaoImpl {

	@PersistenceContext
	@Qualifier("sessionFactory")
	private EntityManager em;

	public void persist(ApplyPermissions apppermission) {
		em.persist(apppermission);
	}

	public int getNextPermissionIndex(int employeeId) {
		String queryString = "SELECT COALESCE(MAX(pr.id.ep_index), 0) + CASE WHEN COUNT(pr) > 0 THEN 1 ELSE 0 END "
				+ "FROM ApplyPermissions pr WHERE pr.id.empl_id = :empl_id";
		Query query = em.createQuery(queryString);
		query.setParameter("empl_id", employeeId);
		return (Integer) query.getSingleResult();
	}
}
