package DAO;

import java.util.List;

import models.Employee;
import models.EmployeeParameter;

public interface EmployeeDAO {
	List<Object[]> getAllEmployees();

	Employee getEmployeeById(int id);

	public void insertEmployee(Employee employee);

	public void updateEmployeeStatus(int id, String newStatus);

	public void updateEmployee(Employee employee);

	public List<EmployeeParameter> getEmployeeParametersById(Integer employeeId);

}
