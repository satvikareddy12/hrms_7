package DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import models.EmployeePayslip;

public class PayRollDAOImpl implements PayRollDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void insertEmployeePayslip(EmployeePayslip payslip) {
		entityManager.persist(payslip);
	}

	@Override
	@Transactional
	public EmployeePayslip getEmployeePayslipsByEmployeeId(int employeeId) {
		String jpql = "SELECT p FROM EmployeePayslip p WHERE p.employeeId = :employeeId";
		TypedQuery<EmployeePayslip> query = entityManager.createQuery(jpql, EmployeePayslip.class);
		query.setParameter("employeeId", employeeId);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public EmployeePayslip getEmployeePayslipsByEmployeeIdAndMonthYear(int employeeId, String monthYear) {
		String jpql = "SELECT p FROM EmployeePayslip p WHERE p.employeeId = :employeeId AND p.monthYear = :monthYear";
		TypedQuery<EmployeePayslip> query = entityManager.createQuery(jpql, EmployeePayslip.class);
		query.setParameter("employeeId", employeeId);
		query.setParameter("monthYear", monthYear);
		return query.getSingleResult();
	}

}
