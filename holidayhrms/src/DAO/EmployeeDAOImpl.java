package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import models.Employee;
import models.EmployeeParameter;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<Object[]> getAllEmployees() {
		String query = "SELECT e.emplId, e.emplFirstname, e.emplLastname, e.emplSurname FROM Employee e";
		return entityManager.createQuery(query, Object[].class).getResultList();
	}

	@Override
	@Transactional
	public Employee getEmployeeById(int id) {
		return entityManager.find(Employee.class, id);
	}

	@Override
	@Transactional
	public void insertEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	@Override
	@Transactional
	public void updateEmployeeStatus(int id, String newStatus) {
		Employee employee = entityManager.find(Employee.class, id);
		employee.setEmpl_status(newStatus);
		entityManager.merge(employee);
	}

	@Override
	@Transactional
	public void updateEmployee(Employee employee) {
		entityManager.merge(employee);
	}

	@Override
	@Transactional
	public List<EmployeeParameter> getEmployeeParametersById(Integer employeeId) {
		TypedQuery<EmployeeParameter> parameterQuery = entityManager.createQuery(
				"SELECT p FROM EmployeeParameter p WHERE p.employeeId = :employeeId", EmployeeParameter.class);
		parameterQuery.setParameter("employeeId", employeeId);
		List<EmployeeParameter> parameters = parameterQuery.getResultList();

		return parameters;
	}

}
