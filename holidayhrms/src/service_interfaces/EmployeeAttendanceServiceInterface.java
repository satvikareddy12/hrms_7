package service_interfaces;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import models.AttendanceEvent;
import models.EmployeeAttendance;
import models.EmployeeRequestResult;

public interface EmployeeAttendanceServiceInterface {
	void insertEmployeeAttendance(EmployeeAttendance attendance);

	List<AttendanceEvent> getYesterdayPunchData(int employeeId);

	EmployeeRequestResult calculateAttendance(List<Object[]> punchData);

	LocalDateTime convertToDateTime(Cell cell);

	List<Integer> getYears(Date joinDate);

	List<Long> getAvgPunchInAndOut(int id);

}
