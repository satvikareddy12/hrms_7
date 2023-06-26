package DAO_Interfaces;

import java.util.List;

import models.EmployeeAttendance;

public interface EmployeeAttendanceDAO {
	void save(EmployeeAttendance employeeAttendance);

	int getNextAttendanceRequestIndex(int employeeId);

	List<Object[]> getYesterdayPunchInAndPunchOut(int employeeId);

	List<Object[]> getPunchInAndPunchOutDataForYearAndMonthAndEmployee(int employeeId, int selectedYear,
			int selectedMonth);
}
