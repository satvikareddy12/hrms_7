package DAO;

import java.util.List;

import models.Employee;
import models.EmployeeParameter;

public interface EmployeeDAO {
	List<Employee> getAllEmployees();

	Employee getEmployeeById(int id);

	void insertEmployee(Employee employee);

	void updateEmployeeStatus(int id, String newStatus);

	void updateEmployee(Employee employee);

	List<EmployeeParameter> getEmployeeParametersById(Integer employeeId);
}
