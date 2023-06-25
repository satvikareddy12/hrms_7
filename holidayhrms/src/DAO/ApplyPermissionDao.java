package DAO;

import java.sql.Date;
import java.util.List;

import models.ApplyPermissions;
import models.Employee;

public interface ApplyPermissionDao {
	void persist(ApplyPermissions apppermission);

	public int getNextPermissionIndex(int employeeId);

	public ApplyPermissions getPermissionByIdAndIndex(int employeeId, int index);

	public List<Employee> getEmployeesByHRAndManager(int employeeId);

	public long getEmployeeAndPermissionRequestDataCountPerMonth(int id, int month, int year);

	Long getEmployeeAndPermissionRequestDataCountPerDay(int id, Date current);

	ApplyPermissions getEmployeeAndPermissionRequestData(int id, Date current);

}
