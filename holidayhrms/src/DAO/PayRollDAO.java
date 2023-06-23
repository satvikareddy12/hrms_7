package DAO;

import models.EmployeePayslip;

public interface PayRollDAO {

	void insertEmployeePayslip(EmployeePayslip payslip);

	public EmployeePayslip getEmployeePayslipsByEmployeeId(int employeeId);

}
