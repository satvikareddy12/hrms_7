package DAO;

import java.util.List;

import models.Employee;
import models.EmployeeParameter;

public interface EmployeeDAO {

	// To get all employees list
	List<Employee> getAllEmployees();

	// To get employee details by id
	Employee getEmployeeById(int id);

	// To insert the employee details into database
	void insertEmployee(Employee employee);

	void updateEmployeeStatus(int id, String newStatus);

	void updateEmployee(Employee employee);

	List<EmployeeParameter> getEmployeeParametersById(Integer employeeId);

	List<Employee> getEmployeesByHRAndManager(int employeeId);

	Employee getEmployee(int employeeId);
}
