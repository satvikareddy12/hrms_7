package DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
