package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import models.Employee;

@Component
public class EmpDAO {

	@PersistenceContext
	private EntityManager em;

	public void persist(Employee emp) {
		em.persist(emp);
	}

	public List<Employee> findAll() {
		return em.createQuery("SELECT e FROM Employee e").getResultList();
	}

	@Transactional
	public Employee findUpdatableEmployee(int emp_id) {
		return em.find(Employee.class, emp_id);
	}

	@Transactional
	public Employee getDetailsByEmail(String emailId) {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.emplOffemail = :emailId",
				Employee.class);
		query.setParameter("emailId", emailId);
		System.out.println(emailId);
		return query.getSingleResult();
	}

}